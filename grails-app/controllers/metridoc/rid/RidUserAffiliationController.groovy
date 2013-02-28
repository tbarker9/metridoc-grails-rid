package metridoc.rid

import org.springframework.dao.DataIntegrityViolationException

class RidUserAffiliationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ridUserAffiliationInstanceList: RidUserAffiliation.list(params), ridUserAffiliationInstanceTotal: RidUserAffiliation.count()]
    }

    def create() {
        [ridUserAffiliationInstance: new RidUserAffiliation(params)]
    }

    def save() {
        def ridUserAffiliationInstance = new RidUserAffiliation(params)
        if (!ridUserAffiliationInstance.save(flush: true)) {
            //render(view: "create", model: [ridUserAffiliationInstance: ridUserAffiliationInstance])
            chain(action: "list", model: [ridUserAffiliationError: ridUserAffiliationInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'ridUserAffiliation.label', default: 'RidUserAffiliation'), ridUserAffiliationInstance.id])
        //redirect(action: "show", id: ridUserAffiliationInstance.id)
        redirect(action: "list")
    }

//    def show(Long id) {
//        def ridUserAffiliationInstance = RidUserAffiliation.get(id)
//        if (!ridUserAffiliationInstance) {
//            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridUserAffiliation.label', default: 'RidUserAffiliation'), id])
//            redirect(action: "list")
//            return
//        }
//
//        [ridUserAffiliationInstance: ridUserAffiliationInstance]
//    }

    def edit(Long id) {
        def ridUserAffiliationInstance = RidUserAffiliation.get(id)
        if (!ridUserAffiliationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridUserAffiliation.label', default: 'RidUserAffiliation'), id])
            redirect(action: "list")
            return
        }

        [ridUserAffiliationInstance: ridUserAffiliationInstance]
    }

    def update(Long id, Long version) {
        def ridUserAffiliationInstance = RidUserAffiliation.get(id)
        if (!ridUserAffiliationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridUserAffiliation.label', default: 'RidUserAffiliation'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (ridUserAffiliationInstance.version > version) {
                ridUserAffiliationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'ridUserAffiliation.label', default: 'RidUserAffiliation')] as Object[],
                        "Another user has updated this RidUserAffiliation while you were editing")
                //render(view: "edit", model: [ridUserAffiliationInstance: ridUserAffiliationInstance])
                chain(action: "list", model: [ridUserAffiliationError: ridUserAffiliationInstance])
                return
            }
        }

        ridUserAffiliationInstance.properties = params

        if (!ridUserAffiliationInstance.save(flush: true)) {
            //render(view: "edit", model: [ridUserAffiliationInstance: ridUserAffiliationInstance])
            chain(action: "list", model: [ridUserAffiliationError: ridUserAffiliationInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'ridUserAffiliation.label', default: 'RidUserAffiliation'), ridUserAffiliationInstance.id])
        //redirect(action: "show", id: ridUserAffiliationInstance.id)
        redirect(action: "list")
    }

    def delete(Long id) {
        def ridUserAffiliationInstance = RidUserAffiliation.get(id)
        if (!ridUserAffiliationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridUserAffiliation.label', default: 'RidUserAffiliation'), id])
            redirect(action: "list")
            return
        }

        try {
            ridUserAffiliationInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ridUserAffiliation.label', default: 'RidUserAffiliation'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ridUserAffiliation.label', default: 'RidUserAffiliation'), id])
            //redirect(action: "show", id: id)
            redirect(action: "list")
        }
    }
}
