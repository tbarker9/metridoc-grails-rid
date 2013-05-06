package metridoc.rid

import org.springframework.dao.DataIntegrityViolationException

class RidLibraryUnitController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ridLibraryUnitInstanceList: RidLibraryUnit.list(params), ridLibraryUnitInstanceTotal: RidLibraryUnit.count()]
    }

    def create() {
        [ridLibraryUnitInstance: new RidLibraryUnit(params)]
    }

    def save() {
        withForm {
            def ridLibraryUnitInstance = new RidLibraryUnit(params)
            if (!ridLibraryUnitInstance.save(flush: true)) {
                chain(view: "list", model: [ridLibraryUnitInstanceError: ridLibraryUnitInstance])
                return
            }
            // automatically create default values
            new RidUserGoal(name: "", inForm: 1, ridLibraryUnit: ridLibraryUnitInstance).save(validate: false)
            new RidServiceProvided(name: "", inForm: 1, ridLibraryUnit: ridLibraryUnitInstance).save(validate: false)
            new RidModeOfConsultation(name: "", inForm: 1, ridLibraryUnit: ridLibraryUnitInstance).save(validate: false)

            flash.message = message(code: 'default.created.message', args: [message(code: 'ridLibraryUnit.label', default: 'RidLibraryUnit'), ridLibraryUnitInstance.id])
            redirect(action: "list")
        }.invalidToken {
            flash.alerts << "Don't click the create button more than one time to make dulplicated submission!"
            redirect(action: "list")
        }
    }

    def edit(Long id) {
        def ridLibraryUnitInstance = RidLibraryUnit.get(id)
        if (!ridLibraryUnitInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridLibraryUnit.label', default: 'RidLibraryUnit'), id])
            redirect(action: "list")
            return
        }

        [ridLibraryUnitInstance: ridLibraryUnitInstance]
    }

    def update(Long id, Long version) {
        withForm{
            def ridLibraryUnitInstance = RidLibraryUnit.get(id)
            if (!ridLibraryUnitInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridLibraryUnit.label', default: 'RidLibraryUnit'), id])
                redirect(action: "list")
                return
            }

            if (version != null) {
                if (ridLibraryUnitInstance.version > version) {
                    ridLibraryUnitInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                            [message(code: 'ridLibraryUnit.label', default: 'RidLibraryUnit')] as Object[],
                            "Another rank has updated this RidLibraryUnit while you were editing")
                    render(view: "list", model: [ridLibraryUnitInstanceError: ridLibraryUnitInstance])
                    return
                }
            }

            ridLibraryUnitInstance.properties = params

            if (!ridLibraryUnitInstance.save(flush: true)) {
                chain(view: "list", model: [ridLibraryUnitInstanceError: ridLibraryUnitInstance])
                return
            }

            flash.message = message(code: 'default.updated.message', args: [message(code: 'ridLibraryUnit.label', default: 'RidLibraryUnit'), ridLibraryUnitInstance.id])
            redirect(action: "list")
        }.invalidToken {
            flash.alerts << "Don't click the update button more than one time to make dulplicated submission!"
            redirect(action: "list")
        }
    }

}
