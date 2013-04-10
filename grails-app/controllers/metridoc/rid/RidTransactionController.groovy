package metridoc.rid

import grails.converters.JSON
import org.springframework.web.multipart.MultipartFile

import java.text.SimpleDateFormat
import org.apache.shiro.SecurityUtils
import org.codehaus.groovy.grails.io.support.ClassPathResource

class RidTransactionController {

    static homePage = [title: "Reference Instruction Database",
            description: "Adds/Updates/Reviews Reference Instruction Transactions"]

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def ridTransactionService
    def spreadsheetUploadingService
    def scaffold = true

    def ajaxChooseType = {
        def response = ridTransactionService.ajaxMethod(params)
        render response as JSON
    }

//    def index() {
//        redirect(action: "list", params: params)
//    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def query = RidTransaction.where {
            templateOwner == "" //|| templateOwner == null
        }
        [ridTransactionInstanceList: query.list(params), ridTransactionInstanceTotal: query.count()]
    }

    def templateList() {
        if (SecurityUtils.getSubject().getPrincipal()) {
            def query = RidTransaction.where {
                templateOwner == SecurityUtils.getSubject().getPrincipal().toString()
            }
            [ridTransactionInstanceList: query.list()]
        }
        else {
            redirect(action: "create")
        }
    }

    def create() {
        try {
            def ridTransactionInstance = new RidTransaction(params)
            if (params.tmp != null && RidTransaction.get(Long.valueOf(params.tmp))) {
                ridTransactionInstance.properties = RidTransaction.get(Long.valueOf(params.tmp)).properties
                ridTransactionInstance.templateOwner = ""
            }
            [ridTransactionInstance: ridTransactionInstance]
        } catch (NumberFormatException e) {
            if (params.tmp.equals("templateList"))
                redirect(action: "templateList")
            else
                [ridTransactionInstance: new RidTransaction(params)]
        }
    }

    def save() {
        withForm {
            if (!params.dateOfConsultation.empty)
                params.dateOfConsultation = new SimpleDateFormat("MM/dd/yyyy").parse(params.dateOfConsultation);
            def ridTransactionInstance = new RidTransaction(params)
            ridTransactionInstance.templateOwner = ""
            ridTransactionService.createNewInstanceMethod(params, ridTransactionInstance)
            if (!ridTransactionInstance.save(flush: true)) {
                //flash.alerts << ridTransactionInstance.errors
                render(view: "create", model: [ridTransactionInstance: ridTransactionInstance])
                return
            }

            flash.message = message(code: 'default.created.message', args: [message(code: 'ridTransaction.label', default: 'RidTransaction'), ridTransactionInstance.id])
            redirect(action: "show", id: ridTransactionInstance.id)
        }.invalidToken {
            flash.alerts << "Don't click the create button more than one time to make dulplicated submission!"
            redirect(action: "list")
        }
    }

    def remember() {
        withForm {
            if (!params.dateOfConsultation.empty)
                params.dateOfConsultation = new SimpleDateFormat("MM/dd/yyyy").parse(params.dateOfConsultation)
            def ridTransactionInstance = new RidTransaction(params)
            ridTransactionInstance.templateOwner = SecurityUtils.getSubject().getPrincipal().toString()
            ridTransactionService.createNewInstanceMethod(params, ridTransactionInstance)
            if (!ridTransactionInstance.save(validate: false, flush: true)) {
                render(view: "create", model: [ridTransactionInstance: ridTransactionInstance])
                return
            }

            flash.message = message(code: 'default.created.message', args: [message(code: 'ridTransaction.label', default: 'RidTransaction Template'), ridTransactionInstance.id])
            redirect(action: "show", id: ridTransactionInstance.id)
        }.invalidToken {
            if (SecurityUtils.getSubject().getPrincipal())
                flash.alerts << "Don't click the remember button more than one time to make dulplicated submission!"
            redirect(action: "list")
        }
    }

    def update(Long id, Long version) {
        withForm {
            def ridTransactionInstance = RidTransaction.get(id)
            if (!ridTransactionInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridTransaction.label', default: 'RidTransaction'), id])
                redirect(action: "list")
                return
            }

            if (version != null) {
                if (ridTransactionInstance.version > version) {
                    ridTransactionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                            [message(code: 'ridTransaction.label', default: 'RidTransaction')] as Object[],
                            "Another rank has updated this RidTransaction while you were editing")
                    render(view: "edit", model: [ridTransactionInstance: ridTransactionInstance])
                    return
                }
            }

            if (!params.dateOfConsultation.empty)
                params.dateOfConsultation = new SimpleDateFormat("MM/dd/yyyy").parse(params.dateOfConsultation);
            ridTransactionInstance.properties = params
            ridTransactionService.createNewInstanceMethod(params, ridTransactionInstance)
            if (!ridTransactionInstance.save(flush: true)) {
                render(view: "edit", model: [ridTransactionInstance: ridTransactionInstance])
                return
            }

            flash.message = message(code: 'default.updated.message', args: [message(code: 'ridTransaction.label', default: 'RidTransaction'), ridTransactionInstance.id])
            redirect(action: "show", id: ridTransactionInstance.id)
        }.invalidToken {
            flash.alerts << "Don't click the update button more than one time to make dulplicated submission!"
            redirect(action: "list")
        }
    }

    /*
    def edit(Long id) {
        def ridTransactionInstance = RidTransaction.get(id)
        if (!ridTransactionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridTransaction.label', default: 'RidTransaction'), id])
            redirect(action: "list")
            return
        }

        [ridTransactionInstance: ridTransactionInstance]
    }

    def show(Long id) {
        def ridTransactionInstance = RidTransaction.get(id)
        if (!ridTransactionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridTransaction.label', default: 'RidTransaction'), id])
            redirect(action: "list")
            return
        }

        [ridTransactionInstance: ridTransactionInstance]
    }

    def delete(Long id) {
        def ridTransactionInstance = RidTransaction.get(id)
        if (!ridTransactionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridTransaction.label', default: 'RidTransaction'), id])
            redirect(action: "list")
            return
        }

        try {
            ridTransactionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ridTransaction.label', default: 'RidTransaction'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ridTransaction.label', default: 'RidTransaction'), id])
            redirect(action: "show", id: id)
        }
    }
    */

    def search() {}

    def query(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def queryResult = ridTransactionService.queryMethod(params)

        render(view: "list",
                model: [ridTransactionInstanceList: queryResult.list(params), ridTransactionInstanceTotal: queryResult.count()])
        return
    }

    def spreadsheetUpload() {}

    def download() {
//        def file = new File('web-app/spreadsheet/' + params.ridLibraryUnit.name + '_Bulkload_Schematic.xlsx')
        ClassPathResource resource = new ClassPathResource('spreadsheet/' + params.ridLibraryUnit.name + '_Bulkload_Schematic.xlsx')
        def file = resource.getFile()
        if (file.exists()) {
            try {
                response.setContentType('application/vnd.openxmlformats-officedocument.spreadsheetml.sheet')
                response.setHeader("Content-disposition", "filename=${file.name}")
                response.outputStream << file.newInputStream() // Performing a binary stream copy
            } catch (Exception e) {
                flash.alerts << e.message
                redirect(action: "spreadsheetUpload")
            }
        }
        else {
            flash.alerts << 'Cannot find file: ' + params.ridLibraryUnit.name + '_Bulkload_Schematic.xlsx'
            redirect(action: "spreadsheetUpload")
        }
    }

    def upload() {
        withForm {
            MultipartFile uploadedFile = request.getFile("spreadsheetUpload");
            if (uploadedFile == null || uploadedFile.empty) {
                flash.alerts << "No file was provided"
                redirect(action: "spreadsheetUpload")
                return
            }

            if (!spreadsheetUploadingService.checkFileType(uploadedFile.getContentType())) {
                flash.alerts << "Invalid File Type. Only Excel Files are accepted!"
                redirect(action: "spreadsheetUpload")
                return
            }

            if (!spreadsheetUploadingService.checkSpreadsheetFormat(uploadedFile)) {
                flash.alerts << "Invalid Spreadsheet Format. Cannot Parse it."
                redirect(action: "spreadsheetUpload")
                return
            }

            if (RidTransaction.findBySpreadsheetName(uploadedFile.originalFilename)) {
                flash.alerts << "This spreadsheet has been uploaded before. Change the file name, for example!"
                redirect(action: "spreadsheetUpload")
                return
            }

            List<List<String>> allInstances = spreadsheetUploadingService.getInstancesFromSpreadsheet(uploadedFile, flash)
            if (!allInstances.size()) {
                redirect(action: "spreadsheetUpload")
                return
            }

            if (spreadsheetUploadingService.saveToDatabase(allInstances, uploadedFile.originalFilename, flash)) {
                flash.infos << "Spreadsheet successfully uploaded. " +
                        String.valueOf(allInstances.size()) + " instances uploaded."
                redirect(action: "list")
            }
            else {
                redirect(action: "spreadsheetUpload")
                return
            }
        }.invalidToken {
            flash.alerts << "Don't click the uploading button more than one time to make dulplicated submission!"
            redirect(action: "spreadsheetUpload")
        }
    }
}