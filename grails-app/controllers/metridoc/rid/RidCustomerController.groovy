package metridoc.rid

import org.springframework.dao.DataIntegrityViolationException

class RidCustomerController {

    static homePage = [title: "RidCustomer", description: "balabala"]
    def scaffold = true
    /*
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ridCustomerInstanceList: RidCustomer.list(params), ridCustomerInstanceTotal: RidCustomer.count()]
    }

    def create() {
        [ridCustomerInstance: new RidCustomer(params)]
    }

    def save() {
        def ridCustomerInstance = new RidCustomer(params)
        if (!ridCustomerInstance.save(flush: true)) {
            render(view: "create", model: [ridCustomerInstance: ridCustomerInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'ridCustomer.label', default: 'RidCustomer'), ridCustomerInstance.id])
        redirect(action: "show", id: ridCustomerInstance.id)
    }

    def show(Long id) {
        def ridCustomerInstance = RidCustomer.get(id)
        if (!ridCustomerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridCustomer.label', default: 'RidCustomer'), id])
            redirect(action: "list")
            return
        }

        [ridCustomerInstance: ridCustomerInstance]
    }

    def edit(Long id) {
        def ridCustomerInstance = RidCustomer.get(id)
        if (!ridCustomerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridCustomer.label', default: 'RidCustomer'), id])
            redirect(action: "list")
            return
        }

        [ridCustomerInstance: ridCustomerInstance]
    }

    def update(Long id, Long version) {
        def ridCustomerInstance = RidCustomer.get(id)
        if (!ridCustomerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridCustomer.label', default: 'RidCustomer'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (ridCustomerInstance.version > version) {
                ridCustomerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'ridCustomer.label', default: 'RidCustomer')] as Object[],
                        "Another user has updated this RidCustomer while you were editing")
                render(view: "edit", model: [ridCustomerInstance: ridCustomerInstance])
                return
            }
        }

        ridCustomerInstance.properties = params

        if (!ridCustomerInstance.save(flush: true)) {
            render(view: "edit", model: [ridCustomerInstance: ridCustomerInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'ridCustomer.label', default: 'RidCustomer'), ridCustomerInstance.id])
        redirect(action: "show", id: ridCustomerInstance.id)
    }

    def delete(Long id) {
        def ridCustomerInstance = RidCustomer.get(id)
        if (!ridCustomerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridCustomer.label', default: 'RidCustomer'), id])
            redirect(action: "list")
            return
        }

        try {
            ridCustomerInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ridCustomer.label', default: 'RidCustomer'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ridCustomer.label', default: 'RidCustomer'), id])
            redirect(action: "show", id: id)
        }
    }
    */
}
