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
        def userGoals = RidUserGoal.findAllByRidReportTypeAndInForm(RidReportType.get(params.typeId), 1)
        def consultations = RidModeOfConsultation.findAllByRidReportTypeAndInForm(RidReportType.get(params.typeId), 1)
        def services = RidServiceProvided.findAllByRidReportTypeAndInForm(RidReportType.get(params.typeId), 1)
        userGoals.addAll(RidUserGoal.findAllByRidReportTypeAndInForm(RidReportType.get(params.typeId), 2))
        consultations.addAll(RidModeOfConsultation.findAllByRidReportTypeAndInForm(RidReportType.get(params.typeId), 2))
        services.addAll(RidServiceProvided.findAllByRidReportTypeAndInForm(RidReportType.get(params.typeId), 2))
        if(!params.goalID.isEmpty()) {
            def goal = RidUserGoal.findByRidReportTypeAndId(RidReportType.get(params.typeId), params.goalID)
            if (goal!=null && !userGoals.contains(goal))
                userGoals.add(0, goal)
        }
        if(!params.modeID.isEmpty()) {
            def mode = RidModeOfConsultation.findByRidReportTypeAndId(RidReportType.get(params.typeId), params.modeID)
            if (mode!=null && !consultations.contains(mode))
                consultations.add(0, mode)
        }
        if(!params.serviceID.isEmpty()) {
            def service = RidServiceProvided.findByRidReportTypeAndId(RidReportType.get(params.typeId), params.serviceID)
            if (service!=null && !userGoals.contains(service))
                services.add(0, service)
        }
        def response = ['userGoal': userGoals,
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
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def query = RidTransaction.where {
            template == Boolean.FALSE
        }
        [ridTransactionInstanceList: query.list(params), ridTransactionInstanceTotal: query.count()]
    }

    def templateList() {
        def query = RidTransaction.where {
            template == Boolean.TRUE
        }
        [ridTransactionInstanceList: query.list()]
    }

    def create() {
        try {
            def ridTransactionInstance = new RidTransaction(params)
            if (params.tmp != null && RidTransaction.get(Long.valueOf(params.tmp))) {
                ridTransactionInstance.properties = RidTransaction.get(Long.valueOf(params.tmp)).properties
                ridTransactionInstance.template = Boolean.FALSE
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
        params.dateOfConsultation = new SimpleDateFormat("MM/dd/yyyy").parse(params.dateOfConsultation);
        def ridTransactionInstance = new RidTransaction(params)
        ridTransactionInstance.template = Boolean.FALSE
        databaseService.serviceMethod(params, ridTransactionInstance)
        if (!ridTransactionInstance.save(flush: true)) {
            render(view: "create", model: [ridTransactionInstance: ridTransactionInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'ridTransaction.label', default: 'RidTransaction'), ridTransactionInstance.id])
        redirect(action: "show", id: ridTransactionInstance.id)
    }

    def remember() {
        params.dateOfConsultation = new SimpleDateFormat("MM/dd/yyyy").parse(params.dateOfConsultation);
        def ridTransactionInstance = new RidTransaction(params)
        ridTransactionInstance.template = Boolean.TRUE
        databaseService.serviceMethod(params, ridTransactionInstance)
        if (!ridTransactionInstance.save(validate: false, flush: true)) {
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
            template == Boolean.FALSE
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

        def TypeList = params.list('ridReportTypeSearch')
        if (TypeList.size() > 0 && !TypeList.contains("0")) {
            List<Long> tList = new LinkedList<Long>()
            for (String id in TypeList)
                tList.add(Long.valueOf(id))
            query = query.where {
                ridReportType in RidReportType.findAllByIdInList(tList)
            }
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