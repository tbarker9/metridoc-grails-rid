package metridoc.rid

import org.springframework.dao.DataIntegrityViolationException

class RidTransactionController {

    def databaseService

    static homePage = [title: "RidTransaction"]
    def scaffold = true
//
//    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
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
        def ridTransactionInstance = new RidTransaction(params)
        if (!ridTransactionInstance.save(flush: true)) {
            render(view: "create", model: [ridTransactionInstance: ridTransactionInstance])
            return
        }

        databaseService.serviceMethod(params)

        flash.message = message(code: 'default.created.message', args: [message(code: 'ridTransaction.label', default: 'RidTransaction'), ridTransactionInstance.id])
        redirect(action: "show", id: ridTransactionInstance.id)
    }
    /*
    def show(Long id) {
        def ridTransactionInstance = RidTransaction.get(id)
        if (!ridTransactionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridTransaction.label', default: 'RidTransaction'), id])
            redirect(action: "list")
            return
        }

        [ridTransactionInstance: ridTransactionInstance]
    }

    def edit(Long id) {
        def ridTransactionInstance = RidTransaction.get(id)
        if (!ridTransactionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridTransaction.label', default: 'RidTransaction'), id])
            redirect(action: "list")
            return
        }

        [ridTransactionInstance: ridTransactionInstance]
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

        ridTransactionInstance.properties = params

        if (!ridTransactionInstance.save(flush: true)) {
            render(view: "edit", model: [ridTransactionInstance: ridTransactionInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'ridTransaction.label', default: 'RidTransaction'), ridTransactionInstance.id])
        redirect(action: "show", id: ridTransactionInstance.id)
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
}