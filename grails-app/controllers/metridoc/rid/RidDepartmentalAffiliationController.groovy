package metridoc.rid

import org.springframework.dao.DataIntegrityViolationException

class RidDepartmentalAffiliationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ridDepartmentalAffiliationInstanceList: RidDepartmentalAffiliation.list(params), ridDepartmentalAffiliationInstanceTotal: RidDepartmentalAffiliation.count()]
    }

    def create() {
        [ridDepartmentalAffiliationInstance: new RidDepartmentalAffiliation(params)]
    }

    def save() {
        def ridDepartmentalAffiliationInstance = new RidDepartmentalAffiliation(params)
        if (!ridDepartmentalAffiliationInstance.save(flush: true)) {
            //render(view: "create", model: [ridDepartmentalAffiliationInstance: ridDepartmentalAffiliationInstance])
            chain(action: "list", model: [ridDepartmentalAffiliationError: ridDepartmentalAffiliationInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'ridDepartmentalAffiliation.label', default: 'RidDepartmentalAffiliation'), ridDepartmentalAffiliationInstance.id])
        //redirect(action: "show", id: ridDepartmentalAffiliationInstance.id)
        redirect(action: "list")
    }

//    def show(Long id) {
//        def ridDepartmentalAffiliationInstance = RidDepartmentalAffiliation.get(id)
//        if (!ridDepartmentalAffiliationInstance) {
//            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridDepartmentalAffiliation.label', default: 'RidDepartmentalAffiliation'), id])
//            redirect(action: "list")
//            return
//        }
//
//        [ridDepartmentalAffiliationInstance: ridDepartmentalAffiliationInstance]
//    }

    def edit(Long id) {
        def ridDepartmentalAffiliationInstance = RidDepartmentalAffiliation.get(id)
        if (!ridDepartmentalAffiliationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridDepartmentalAffiliation.label', default: 'RidDepartmentalAffiliation'), id])
            redirect(action: "list")
            return
        }

        [ridDepartmentalAffiliationInstance: ridDepartmentalAffiliationInstance]
    }

    def update(Long id, Long version) {
        def ridDepartmentalAffiliationInstance = RidDepartmentalAffiliation.get(id)
        if (!ridDepartmentalAffiliationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridDepartmentalAffiliation.label', default: 'RidDepartmentalAffiliation'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (ridDepartmentalAffiliationInstance.version > version) {
                ridDepartmentalAffiliationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'ridDepartmentalAffiliation.label', default: 'RidDepartmentalAffiliation')] as Object[],
                        "Another user has updated this RidDepartmentalAffiliation while you were editing")
                render(view: "edit", model: [ridDepartmentalAffiliationInstance: ridDepartmentalAffiliationInstance])
                return
            }
        }

        ridDepartmentalAffiliationInstance.properties = params

        if (!ridDepartmentalAffiliationInstance.save(flush: true)) {
            //render(view: "edit", model: [ridDepartmentalAffiliationInstance: ridDepartmentalAffiliationInstance])
            chain(action: "list", model: [ridDepartmentalAffiliationError: ridDepartmentalAffiliationInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'ridDepartmentalAffiliation.label', default: 'RidDepartmentalAffiliation'), ridDepartmentalAffiliationInstance.id])
        //redirect(action: "show", id: ridDepartmentalAffiliationInstance.id)
        redirect(action: "list")
    }

    def delete(Long id) {
        def ridDepartmentalAffiliationInstance = RidDepartmentalAffiliation.get(id)
        if (!ridDepartmentalAffiliationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridDepartmentalAffiliation.label', default: 'RidDepartmentalAffiliation'), id])
            redirect(action: "list")
            return
        }

        try {
            ridDepartmentalAffiliationInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ridDepartmentalAffiliation.label', default: 'RidDepartmentalAffiliation'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ridDepartmentalAffiliation.label', default: 'RidDepartmentalAffiliation'), id])
            //redirect(action: "show", id: id)
            redirect(action: "list")
        }
    }
}
