package metridoc.rid

import org.codehaus.groovy.grails.io.support.ClassPathResource
import org.springframework.web.multipart.MultipartFile

class RidLibraryUnitController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def spreadsheetService
    def ridManageLibraryUnitSpreadsheetsService

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ridLibraryUnitInstanceList: RidLibraryUnit.list(params), ridLibraryUnitInstanceTotal: RidLibraryUnit.count()]
    }

    def create() {
        [ridLibraryUnitInstance: new RidLibraryUnit(params)]
    }

    def save() {
        withForm {
            def ridLibraryUnitInstance = new RidLibraryUnit(params)
            if (!ridLibraryUnitInstance.save(flush: true)) {
                chain(action: "list", model: [ridLibraryUnitError: ridLibraryUnitInstance])
                return
            }
            // automatically create default values
            new RidUserGoal(name: "", inForm: 1, ridLibraryUnit: ridLibraryUnitInstance).save(validate: false)
            new RidServiceProvided(name: "", inForm: 1, ridLibraryUnit: ridLibraryUnitInstance).save(validate: false)
            new RidModeOfConsultation(name: "", inForm: 1, ridLibraryUnit: ridLibraryUnitInstance).save(validate: false)

            // check and save spreadsheet
            MultipartFile uploadedFile = request.getFile('spreadsheetUpload')
            if (uploadedFile != null && !uploadedFile.empty) {
                if (!spreadsheetService.checkFileType(uploadedFile.getContentType())) {
                    flash.alerts << "Invalid File Type. Only Excel Files are accepted!"
                    redirect(action: "list")
                    return
                }
                if (uploadedFile.originalFilename != params.name + '_Bulkload_Schematic.xlsx') {
                    flash.alerts << "Invalid File Name. Should be '" + params.name + "_Bulkload_Schematic.xlsx'"
                    redirect(action: "list")
                    return
                }
                if (!spreadsheetService.checkSpreadsheetFormat(uploadedFile)) {
                    flash.alerts << "Invalid Spreadsheet Format. Cannot Parse it."
                    redirect(action: "spreadsheetUpload")
                    return
                }
                ClassPathResource resource = new ClassPathResource('grails-app/conf/spreadsheet/' + uploadedFile.originalFilename)
                if (resource.exists()) {
                    try {
                        resource.getFile().delete()
                    } catch (Exception e) {
                        flash.alerts << e.message
                    }
                }
                try {
                    uploadedFile.transferTo(new File(resource.path))
                } catch (Exception e) {
                    flash.alerts << e.message
                }
            }

            flash.message = message(code: 'default.created.message', args: [message(code: 'ridLibraryUnit.label', default: 'RidLibraryUnit'), ridLibraryUnitInstance.id])
            redirect(action: "list")
        }.invalidToken {
            flash.alerts << "Don't click the create button more than one time to make dulplicated submission!"
            redirect(action: "list")
        }
    }

    def edit(Long id) {
        def ridLibraryUnitInstance = RidLibraryUnit.get(id)
        if (!ridLibraryUnitInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridLibraryUnit.label', default: 'RidLibraryUnit'), id])
            redirect(action: "list")
            return
        }

        [ridLibraryUnitInstance: ridLibraryUnitInstance]
    }

    def update(Long id, Long version) {
        withForm {
            def ridLibraryUnitInstance = RidLibraryUnit.get(id)
            def oldname = ridLibraryUnitInstance.name
            if (!ridLibraryUnitInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridLibraryUnit.label', default: 'RidLibraryUnit'), id])
                redirect(action: "list")
                return
            }

            if (version != null) {
                if (ridLibraryUnitInstance.version > version) {
                    ridLibraryUnitInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                            [message(code: 'ridLibraryUnit.label', default: 'RidLibraryUnit')] as Object[],
                            "Another rank has updated this RidLibraryUnit while you were editing")
                    render(view: "list", model: [ridLibraryUnitError: ridLibraryUnitInstance])
                    return
                }
            }

            ridLibraryUnitInstance.properties = params

            if (!ridLibraryUnitInstance.save(flush: true)) {
                chain(action: "list", model: [ridLibraryUnitError: ridLibraryUnitInstance])
                return
            }

            // check and update spreadsheet file
            MultipartFile uploadedFile = request.getFile('spreadsheetUpload')
            if (uploadedFile != null && !uploadedFile.empty) {
                if (!spreadsheetService.checkFileType(uploadedFile.getContentType())) {
                    flash.alerts << "Invalid File Type. Only Excel Files are accepted!"
                    redirect(action: "list")
                    return
                }
                if (uploadedFile.originalFilename != params.name + '_Bulkload_Schematic.xlsx') {
                    flash.alerts << "Invalid File Name. Should be '" + params.name + "_Bulkload_Schematic.xlsx'"
                    redirect(action: "list")
                    return
                }
                if (!spreadsheetService.checkSpreadsheetFormat(uploadedFile)) {
                    flash.alerts << "Invalid Spreadsheet Format. Cannot Parse it."
                    redirect(action: "spreadsheetUpload")
                    return
                }
                ClassPathResource r = new ClassPathResource('grails-app/conf/spreadsheet/' + uploadedFile.originalFilename)
                if (r.exists()) {
                    try {
                        r.getFile().delete()
                    } catch (Exception e) {
                        flash.alerts << e.message
                    }
                }
                try {
                    uploadedFile.transferTo(new File(r.path))
                } catch (Exception e) {
                    flash.alerts << e.message
                }
            }

            flash.message = message(code: 'default.updated.message', args: [message(code: 'ridLibraryUnit.label', default: 'RidLibraryUnit'), ridLibraryUnitInstance.id])
            redirect(action: "list")
        }.invalidToken {
            flash.alerts << "Don't click the update button more than one time to make dulplicated submission!"
            redirect(action: "list")
        }
    }

    def download() {
        ridManageLibraryUnitSpreadsheetsService.download(response, flash, params)
//        def file = new File(ridManageLibraryUnitSpreadsheetService.DEFAULT_SPREADSHEET_DIRECTORY + "/" + params.sname)
//        if (!file.exists()) {
//            flash.message = "File not found"
//        }
//        try {
//            response.setContentType('application/vnd.openxmlformats-officedocument.spreadsheetml.sheet')
//            response.setHeader("Content-disposition", "filename=${file.name}")
//            response.outputStream << file.newInputStream() // Performing a binary stream copy
//        } catch (Exception e) {
//            flash.alerts << e.message
//        }
    }

    def spreadsheetUpload() {

    }
}
