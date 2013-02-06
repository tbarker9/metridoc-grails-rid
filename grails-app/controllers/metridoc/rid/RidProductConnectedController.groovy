package metridoc.rid

import org.springframework.dao.DataIntegrityViolationException

class RidProductConnectedController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ridProductConnectedInstanceList: RidProductConnected.list(params), ridProductConnectedInstanceTotal: RidProductConnected.count()]
    }

    def create() {
        [ridProductConnectedInstance: new RidProductConnected(params)]
    }

    def save() {
        def ridProductConnectedInstance = new RidProductConnected(params)
        if (!ridProductConnectedInstance.save(flush: true)) {
            render(view: "create", model: [ridProductConnectedInstance: ridProductConnectedInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'ridProductConnected.label', default: 'RidProductConnected'), ridProductConnectedInstance.id])
        //redirect(action: "show", id: ridProductConnectedInstance.id)
        redirect(action: "list")
    }

    def show(Long id) {
        def ridProductConnectedInstance = RidProductConnected.get(id)
        if (!ridProductConnectedInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridProductConnected.label', default: 'RidProductConnected'), id])
            redirect(action: "list")
            return
        }

        [ridProductConnectedInstance: ridProductConnectedInstance]
    }

    def edit(Long id) {
        def ridProductConnectedInstance = RidProductConnected.get(id)
        if (!ridProductConnectedInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridProductConnected.label', default: 'RidProductConnected'), id])
            redirect(action: "list")
            return
        }

        [ridProductConnectedInstance: ridProductConnectedInstance]
    }

    def update(Long id, Long version) {
        def ridProductConnectedInstance = RidProductConnected.get(id)
        if (!ridProductConnectedInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridProductConnected.label', default: 'RidProductConnected'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (ridProductConnectedInstance.version > version) {
                ridProductConnectedInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'ridProductConnected.label', default: 'RidProductConnected')] as Object[],
                        "Another user has updated this RidProductConnected while you were editing")
                render(view: "edit", model: [ridProductConnectedInstance: ridProductConnectedInstance])
                return
            }
        }

        ridProductConnectedInstance.properties = params

        if (!ridProductConnectedInstance.save(flush: true)) {
            render(view: "edit", model: [ridProductConnectedInstance: ridProductConnectedInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'ridProductConnected.label', default: 'RidProductConnected'), ridProductConnectedInstance.id])
        //redirect(action: "show", id: ridProductConnectedInstance.id)
        redirect(action: "list")
    }

    def delete(Long id) {
        def ridProductConnectedInstance = RidProductConnected.get(id)
        if (!ridProductConnectedInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridProductConnected.label', default: 'RidProductConnected'), id])
            redirect(action: "list")
            return
        }

        try {
            ridProductConnectedInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ridProductConnected.label', default: 'RidProductConnected'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ridProductConnected.label', default: 'RidProductConnected'), id])
            //redirect(action: "show", id: id)
            redirect(action: "list")
        }
    }
}
