package metridoc.rid

class RidModeOfConsultationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        if (session.getAttribute("transType") == "instructional") {
            session.setAttribute("prev", new String("RidLibraryUnit"))
            redirect(controller: RidLibraryUnit, action: "index")
        }
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        if (session.getAttribute("transType") == "instructional") {
            session.setAttribute("prev", new String("RidLibraryUnit"))
            redirect(controller: RidLibraryUnit, action: "index")
        }
        params.max = Math.min(max ?: 10, 100)
        def instances = RidModeOfConsultation.where { name != "" }
        [ridModeOfConsultationInstanceList: instances.list(params), ridModeOfConsultationInstanceTotal: instances.count()]
    }

    def create() {
        [ridModeOfConsultationInstance: new RidModeOfConsultation(params)]
    }

    def save() {
        withForm {
            def ridModeOfConsultationInstance = new RidModeOfConsultation(params)
            if (!ridModeOfConsultationInstance.save(flush: true)) {
                chain(action: "list", model: [ridModeOfConsultationError: ridModeOfConsultationInstance])
                return
            }

            flash.message = message(code: 'default.created.message', args: [message(code: 'ridModeOfConsultation.label', default: 'RidModeOfConsultation'), ridModeOfConsultationInstance.id])
            redirect(action: "list")
        }.invalidToken {
            flash.alerts << "Don't click the create button more than one time to make duplicated submission!"
            redirect(action: "list")
        }
    }

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
        withForm {
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
                            "Another rank has updated this RidModeOfConsultation while you were editing")
                    chain(action: "list", model: [ridModeOfConsultationError: ridModeOfConsultationInstance])
                    return
                }
            }

            ridModeOfConsultationInstance.properties = params

            if (!ridModeOfConsultationInstance.save(flush: true)) {
                chain(action: "list", model: [ridModeOfConsultationError: ridModeOfConsultationInstance])
                return
            }

            flash.message = message(code: 'default.updated.message', args: [message(code: 'ridModeOfConsultation.label', default: 'RidModeOfConsultation'), ridModeOfConsultationInstance.id])
            redirect(action: "list")
        }.invalidToken {
            flash.alerts << "Don't click the update button more than one time to make duplicated submission!"
            redirect(action: "list")
        }
    }

}
