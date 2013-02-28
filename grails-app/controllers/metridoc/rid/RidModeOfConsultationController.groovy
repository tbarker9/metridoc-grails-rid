package metridoc.rid

import org.springframework.dao.DataIntegrityViolationException

class RidModeOfConsultationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ridModeOfConsultationInstanceList: RidModeOfConsultation.list(params), ridModeOfConsultationInstanceTotal: RidModeOfConsultation.count()]
    }

    def create() {
        [ridModeOfConsultationInstance: new RidModeOfConsultation(params)]
    }

    def save() {
        def ridModeOfConsultationInstance = new RidModeOfConsultation(params)
        if (!ridModeOfConsultationInstance.save(flush: true)) {
            //render(view: "create", model: [ridModeOfConsultationInstance: ridModeOfConsultationInstance])
            chain(action: "list", model: [ridModeOfConsultationError: ridModeOfConsultationInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'ridModeOfConsultation.label', default: 'RidModeOfConsultation'), ridModeOfConsultationInstance.id])
        //redirect(action: "show", id: ridModeOfConsultationInstance.id)
        redirect(action: "list")
    }

//    def show(Long id) {
//        def ridModeOfConsultationInstance = RidModeOfConsultation.get(id)
//        if (!ridModeOfConsultationInstance) {
//            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridModeOfConsultation.label', default: 'RidModeOfConsultation'), id])
//            redirect(action: "list")
//            return
//        }
//
//        [ridModeOfConsultationInstance: ridModeOfConsultationInstance]
//    }

    def edit(Long id) {
        def ridModeOfConsultationInstance = RidModeOfConsultation.get(id)
        if (!ridModeOfConsultationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridModeOfConsultation.label', default: 'RidModeOfConsultation'), id])
            redirect(action: "list")
            return
        }

        [ridModeOfConsultationInstance: ridModeOfConsultationInstance]
    }

    def update(Long id, Long version) {
        def ridModeOfConsultationInstance = RidModeOfConsultation.get(id)
        if (!ridModeOfConsultationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridModeOfConsultation.label', default: 'RidModeOfConsultation'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (ridModeOfConsultationInstance.version > version) {
                ridModeOfConsultationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'ridModeOfConsultation.label', default: 'RidModeOfConsultation')] as Object[],
                        "Another user has updated this RidModeOfConsultation while you were editing")
                //render(view: "edit", model: [ridModeOfConsultationInstance: ridModeOfConsultationInstance])
                chain(action: "list", model: [ridModeOfConsultationError: ridModeOfConsultationInstance])
                return
            }
        }

        ridModeOfConsultationInstance.properties = params

        if (!ridModeOfConsultationInstance.save(flush: true)) {
            //render(view: "edit", model: [ridModeOfConsultationInstance: ridModeOfConsultationInstance])
            chain(action: "list", model: [ridModeOfConsultationError: ridModeOfConsultationInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'ridModeOfConsultation.label', default: 'RidModeOfConsultation'), ridModeOfConsultationInstance.id])
        //redirect(action: "show", id: ridModeOfConsultationInstance.id)
        redirect(action: "list")
    }

    def delete(Long id) {
        def ridModeOfConsultationInstance = RidModeOfConsultation.get(id)
        if (!ridModeOfConsultationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridModeOfConsultation.label', default: 'RidModeOfConsultation'), id])
            redirect(action: "list")
            return
        }

        try {
            ridModeOfConsultationInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ridModeOfConsultation.label', default: 'RidModeOfConsultation'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ridModeOfConsultation.label', default: 'RidModeOfConsultation'), id])
            //redirect(action: "show", id: id)
            redirect(action: "list")
        }
    }
}
