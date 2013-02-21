package metridoc.rid

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
import java.text.SimpleDateFormat

class RidTransactionController {

    def databaseService
    //def stemmer

    static homePage = [title: "Reference Instruction Database",
            description: "Adds/Updates/Reviews Reference Instruction Transactions"]

    def scaffold = true

    def ajaxChooseType = {
        def products = RidUserGoal.findAllByRidReportType(RidReportType.get(params.typeId))
        def consultations = RidModeOfConsultation.findAllByRidReportType(RidReportType.get(params.typeId))
        def services = RidServiceProvided.findAllByRidReportType(RidReportType.get(params.typeId))
        def response = ['userGoal': products,
                'modeOfConsultation': consultations,
                'serviceProvided': services]
        render response as JSON
    }

//
//   static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
//
//    def index() {
//        redirect(action: "list", params: params)
//    }
//
//    def list(Integer max) {
//        params.max = Math.min(max ?: 10, 100)
//        [ridTransactionInstanceList: RidTransaction.list(params), ridTransactionInstanceTotal: RidTransaction.count()]
//    }
//
//    def create() {
//        [ridTransactionInstance: new RidTransaction(params)]
//    }

    def save() {
        params.dateOfConsultation = new SimpleDateFormat("MM/dd/yyyy").parse(params.dateOfConsultation);
        def ridTransactionInstance = new RidTransaction(params)
        databaseService.serviceMethod(params, ridTransactionInstance)
        if (!ridTransactionInstance.save(flush: true)) {
            render(view: "create", model: [ridTransactionInstance: ridTransactionInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'ridTransaction.label', default: 'RidTransaction'), ridTransactionInstance.id])
        redirect(action: "show", id: ridTransactionInstance.id)
    }

    def update(Long id, Long version) {
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
                        "Another user has updated this RidTransaction while you were editing")
                render(view: "edit", model: [ridTransactionInstance: ridTransactionInstance])
                return
            }
        }

        params.dateOfConsultation = new SimpleDateFormat("MM/dd/yyyy").parse(params.dateOfConsultation);
        ridTransactionInstance.properties = params
        databaseService.serviceMethod(params, ridTransactionInstance)
        if (!ridTransactionInstance.save(flush: true)) {
            render(view: "edit", model: [ridTransactionInstance: ridTransactionInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'ridTransaction.label', default: 'RidTransaction'), ridTransactionInstance.id])
        redirect(action: "show", id: ridTransactionInstance.id)
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

        def query = RidTransaction.where {
            userQuestion != null
        }

        try {
            Date start = new SimpleDateFormat("MM/dd/yyyy").parse(params.dateOfConsultation_start)
            Date end =  new SimpleDateFormat("MM/dd/yyyy").parse(params.dateOfConsultation_end)
            query = query.where {
                dateOfConsultation >= start && dateOfConsultation < end.next()
            }
        } catch(Exception e) {
//            Date start = Date.parse("E MMM dd H:m:s z yyyy", params.dateOfConsultation_start)
//            Date end = Date.parse("E MMM dd H:m:s z yyyy", params.dateOfConsultation_end)
//            query = query.where {
//                dateOfConsultation >= start && dateOfConsultation < end.next()
//            }
        }

        String [] staffPennkey_splits = params.staffPennkey.split(" ");
        for (String s in staffPennkey_splits) {
            if (!s.trim().isEmpty()) {
                // s = stemmer.doStemming(s)
                query = query.where {
                    staffPennkey ==~ ~s.trim()
                }
            }
        }

        String [] userQuestion_splits = params.userQuestion.split(" ");
        for (String s in userQuestion_splits) {
            if (!s.trim().isEmpty()) {
                query = query.where {
                    //userQuestion ==~ ~"^.+ba\$"
                    //userQuestion ==~ ~"^k.*"
                    userQuestion ==~ ~s.trim()
                }
            }
        }

        String [] notes_splits = params.notes.split(" ");
        for (String s in notes_splits) {
            if (!s.trim().isEmpty()) {
                query = query.where {
                    notes ==~ ~s.trim()
                }
            }
        }

        def ridTransactionList = query.list(params)
        render(view: "list",
            model: [ridTransactionInstanceList: ridTransactionList, ridTransactionInstanceTotal: query.count()])
        return
    }

}