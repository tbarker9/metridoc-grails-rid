package metridoc.rid

import org.springframework.dao.DataIntegrityViolationException

class RidEntityAffiliationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ridEntityAffiliationInstanceList: RidEntityAffiliation.list(params), ridEntityAffiliationInstanceTotal: RidEntityAffiliation.count()]
    }

    def create() {
        [ridEntityAffiliationInstance: new RidEntityAffiliation(params)]
    }

    def save() {
        def ridEntityAffiliationInstance = new RidEntityAffiliation(params)
        if (!ridEntityAffiliationInstance.save(flush: true)) {
            render(view: "create", model: [ridEntityAffiliationInstance: ridEntityAffiliationInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'ridEntityAffiliation.label', default: 'RidEntityAffiliation'), ridEntityAffiliationInstance.id])
        //redirect(action: "show", id: ridEntityAffiliationInstance.id)
        redirect(action: "list")
    }

    def show(Long id) {
        def ridEntityAffiliationInstance = RidEntityAffiliation.get(id)
        if (!ridEntityAffiliationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridEntityAffiliation.label', default: 'RidEntityAffiliation'), id])
            redirect(action: "list")
            return
        }

        [ridEntityAffiliationInstance: ridEntityAffiliationInstance]
    }

    def edit(Long id) {
        def ridEntityAffiliationInstance = RidEntityAffiliation.get(id)
        if (!ridEntityAffiliationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridEntityAffiliation.label', default: 'RidEntityAffiliation'), id])
            redirect(action: "list")
            return
        }

        [ridEntityAffiliationInstance: ridEntityAffiliationInstance]
    }

    def update(Long id, Long version) {
        def ridEntityAffiliationInstance = RidEntityAffiliation.get(id)
        if (!ridEntityAffiliationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridEntityAffiliation.label', default: 'RidEntityAffiliation'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (ridEntityAffiliationInstance.version > version) {
                ridEntityAffiliationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'ridEntityAffiliation.label', default: 'RidEntityAffiliation')] as Object[],
                        "Another user has updated this RidEntityAffiliation while you were editing")
                render(view: "edit", model: [ridEntityAffiliationInstance: ridEntityAffiliationInstance])
                return
            }
        }

        ridEntityAffiliationInstance.properties = params

        if (!ridEntityAffiliationInstance.save(flush: true)) {
            render(view: "edit", model: [ridEntityAffiliationInstance: ridEntityAffiliationInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'ridEntityAffiliation.label', default: 'RidEntityAffiliation'), ridEntityAffiliationInstance.id])
        //redirect(action: "show", id: ridEntityAffiliationInstance.id)
        redirect(action: "list")
    }

    def delete(Long id) {
        def ridEntityAffiliationInstance = RidEntityAffiliation.get(id)
        if (!ridEntityAffiliationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridEntityAffiliation.label', default: 'RidEntityAffiliation'), id])
            redirect(action: "list")
            return
        }

        try {
            ridEntityAffiliationInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ridEntityAffiliation.label', default: 'RidEntityAffiliation'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ridEntityAffiliation.label', default: 'RidEntityAffiliation'), id])
            //redirect(action: "show", id: id)
            redirect(action: "list")
        }
    }
}
