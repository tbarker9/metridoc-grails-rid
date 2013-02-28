package metridoc.rid

import org.springframework.dao.DataIntegrityViolationException

class RidServiceProvidedController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ridServiceProvidedInstanceList: RidServiceProvided.list(params), ridServiceProvidedInstanceTotal: RidServiceProvided.count()]
    }

    def create() {
        [ridServiceProvidedInstance: new RidServiceProvided(params)]
    }

    def save() {
        def ridServiceProvidedInstance = new RidServiceProvided(params)
        if (!ridServiceProvidedInstance.save(flush: true)) {
            //render(view: "create", model: [ridServiceProvidedInstance: ridServiceProvidedInstance])
            chain(action: "list", model: [ridServiceProvidedError: ridServiceProvidedInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'ridServiceProvided.label', default: 'RidServiceProvided'), ridServiceProvidedInstance.id])
        //redirect(action: "show", id: ridServiceProvidedInstance.id)
        redirect(action: "list")
    }

//    def show(Long id) {
//        def ridServiceProvidedInstance = RidServiceProvided.get(id)
//        if (!ridServiceProvidedInstance) {
//            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridServiceProvided.label', default: 'RidServiceProvided'), id])
//            redirect(action: "list")
//            return
//        }
//
//        [ridServiceProvidedInstance: ridServiceProvidedInstance]
//    }

    def edit(Long id) {
        def ridServiceProvidedInstance = RidServiceProvided.get(id)
        if (!ridServiceProvidedInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridServiceProvided.label', default: 'RidServiceProvided'), id])
            redirect(action: "list")
            return
        }

        [ridServiceProvidedInstance: ridServiceProvidedInstance]
    }

    def update(Long id, Long version) {
        def ridServiceProvidedInstance = RidServiceProvided.get(id)
        if (!ridServiceProvidedInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridServiceProvided.label', default: 'RidServiceProvided'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (ridServiceProvidedInstance.version > version) {
                ridServiceProvidedInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'ridServiceProvided.label', default: 'RidServiceProvided')] as Object[],
                        "Another user has updated this RidServiceProvided while you were editing")
                //render(view: "edit", model: [ridServiceProvidedInstance: ridServiceProvidedInstance])
                chain(action: "list", model: [ridServiceProvidedError: ridServiceProvidedInstance])
                return
            }
        }

        ridServiceProvidedInstance.properties = params

        if (!ridServiceProvidedInstance.save(flush: true)) {
            //render(view: "edit", model: [ridServiceProvidedInstance: ridServiceProvidedInstance])
            chain(action: "list", model: [ridServiceProvidedError: ridServiceProvidedInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'ridServiceProvided.label', default: 'RidServiceProvided'), ridServiceProvidedInstance.id])
        //redirect(action: "show", id: ridServiceProvidedInstance.id)
        redirect(action: "list")
    }

    def delete(Long id) {
        def ridServiceProvidedInstance = RidServiceProvided.get(id)
        if (!ridServiceProvidedInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridServiceProvided.label', default: 'RidServiceProvided'), id])
            redirect(action: "list")
            return
        }

        try {
            ridServiceProvidedInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ridServiceProvided.label', default: 'RidServiceProvided'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ridServiceProvided.label', default: 'RidServiceProvided'), id])
            //redirect(action: "show", id: id)
            redirect(action: "list")
        }
    }
}
