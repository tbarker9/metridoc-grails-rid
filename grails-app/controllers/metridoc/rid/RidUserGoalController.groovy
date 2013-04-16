package metridoc.rid

import org.springframework.dao.DataIntegrityViolationException

class RidUserGoalController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def instances = RidUserGoal.where {name != ""}
        [ridUserGoalInstanceList: instances.list(params), ridUserGoalInstanceTotal: instances.count()]
    }

    def create() {
        [ridUserGoalInstance: new RidUserGoal(params)]
    }

    def save() {
        withForm {
            def ridUserGoalInstance = new RidUserGoal(params)
            if (!ridUserGoalInstance.save(flush: true)) {
                chain(action: "list", model: [ridUserGoalError: ridUserGoalInstance])
                return
            }

            flash.message = message(code: 'default.created.message', args: [message(code: 'ridUserGoal.label', default: 'RidUserGoal'), ridUserGoalInstance.id])
            redirect(action: "list")
        }.invalidToken {
            flash.alerts << "Don't click the create button more than one time to make dulplicated submission!"
            redirect(action: "list")
        }
    }

    def edit(Long id) {
        def ridUserGoalInstance = RidUserGoal.get(id)
        if (!ridUserGoalInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridUserGoal.label', default: 'RidUserGoal'), id])
            redirect(action: "list")
            return
        }

        [ridUserGoalInstance: ridUserGoalInstance]
    }

    def update(Long id, Long version) {
        withForm {
            def ridUserGoalInstance = RidUserGoal.get(id)
            if (!ridUserGoalInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridUserGoal.label', default: 'RidUserGoal'), id])
                redirect(action: "list")
                return
            }

            if (version != null) {
                if (ridUserGoalInstance.version > version) {
                    ridUserGoalInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                            [message(code: 'ridUserGoal.label', default: 'RidUserGoal')] as Object[],
                            "Another rank has updated this RidUserGoal while you were editing")
                    chain(action: "list", model: [ridUserGoalError: ridUserGoalInstance])
                    return
                }
            }

            ridUserGoalInstance.properties = params

            if (!ridUserGoalInstance.save(flush: true)) {
                chain(action: "list", model: [ridUserGoalError: ridUserGoalInstance])
                return
            }

            flash.message = message(code: 'default.updated.message', args: [message(code: 'ridUserGoal.label', default: 'RidUserGoal'), ridUserGoalInstance.id])
            redirect(action: "list")
        }.invalidToken {
            flash.alerts << "Don't click the update button more than one time to make dulplicated submission!"
            redirect(action: "list")
        }
    }

    def delete(Long id) {
        def ridUserGoalInstance = RidUserGoal.get(id)
        if (!ridUserGoalInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridUserGoal.label', default: 'RidUserGoal'), id])
            redirect(action: "list")
            return
        }

        try {
            ridUserGoalInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ridUserGoal.label', default: 'RidUserGoal'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ridUserGoal.label', default: 'RidUserGoal'), id])
            redirect(action: "list")
        }
    }
}
