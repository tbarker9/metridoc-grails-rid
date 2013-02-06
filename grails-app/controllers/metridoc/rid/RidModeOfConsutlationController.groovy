package metridoc.rid

import org.springframework.dao.DataIntegrityViolationException

class RidModeOfConsutlationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ridModeOfConsutlationInstanceList: RidModeOfConsutlation.list(params), ridModeOfConsutlationInstanceTotal: RidModeOfConsutlation.count()]
    }

    def create() {
        [ridModeOfConsutlationInstance: new RidModeOfConsutlation(params)]
    }

    def save() {
        def ridModeOfConsutlationInstance = new RidModeOfConsutlation(params)
        if (!ridModeOfConsutlationInstance.save(flush: true)) {
            render(view: "create", model: [ridModeOfConsutlationInstance: ridModeOfConsutlationInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'ridModeOfConsutlation.label', default: 'RidModeOfConsutlation'), ridModeOfConsutlationInstance.id])
        //redirect(action: "show", id: ridModeOfConsutlationInstance.id)
        redirect(action: "list")
    }

    def show(Long id) {
        def ridModeOfConsutlationInstance = RidModeOfConsutlation.get(id)
        if (!ridModeOfConsutlationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridModeOfConsutlation.label', default: 'RidModeOfConsutlation'), id])
            redirect(action: "list")
            return
        }

        [ridModeOfConsutlationInstance: ridModeOfConsutlationInstance]
    }

    def edit(Long id) {
        def ridModeOfConsutlationInstance = RidModeOfConsutlation.get(id)
        if (!ridModeOfConsutlationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridModeOfConsutlation.label', default: 'RidModeOfConsutlation'), id])
            redirect(action: "list")
            return
        }

        [ridModeOfConsutlationInstance: ridModeOfConsutlationInstance]
    }

    def update(Long id, Long version) {
        def ridModeOfConsutlationInstance = RidModeOfConsutlation.get(id)
        if (!ridModeOfConsutlationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridModeOfConsutlation.label', default: 'RidModeOfConsutlation'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (ridModeOfConsutlationInstance.version > version) {
                ridModeOfConsutlationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'ridModeOfConsutlation.label', default: 'RidModeOfConsutlation')] as Object[],
                        "Another user has updated this RidModeOfConsutlation while you were editing")
                render(view: "edit", model: [ridModeOfConsutlationInstance: ridModeOfConsutlationInstance])
                return
            }
        }

        ridModeOfConsutlationInstance.properties = params

        if (!ridModeOfConsutlationInstance.save(flush: true)) {
            render(view: "edit", model: [ridModeOfConsutlationInstance: ridModeOfConsutlationInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'ridModeOfConsutlation.label', default: 'RidModeOfConsutlation'), ridModeOfConsutlationInstance.id])
        //redirect(action: "show", id: ridModeOfConsutlationInstance.id)
        redirect(action: "list")
    }

    def delete(Long id) {
        def ridModeOfConsutlationInstance = RidModeOfConsutlation.get(id)
        if (!ridModeOfConsutlationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridModeOfConsutlation.label', default: 'RidModeOfConsutlation'), id])
            redirect(action: "list")
            return
        }

        try {
            ridModeOfConsutlationInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ridModeOfConsutlation.label', default: 'RidModeOfConsutlation'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ridModeOfConsutlation.label', default: 'RidModeOfConsutlation'), id])
            //redirect(action: "show", id: id)
            redirect(action: "list")
        }
    }
}
