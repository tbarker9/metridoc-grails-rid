package metridoc.rid

class RidAdminServiceProvidedController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    static accessControl = {
        role(name: "ROLE_ADMIN")
    }

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
        def instances = RidServiceProvided.where { name != "" }
        [ridServiceProvidedInstanceList: instances.list(params), ridServiceProvidedInstanceTotal: instances.count()]
    }

    def create() {
        [ridServiceProvidedInstance: new RidServiceProvided(params)]
    }

    def save() {
        withForm {
            def ridServiceProvidedInstance = new RidServiceProvided(params)
            if (!ridServiceProvidedInstance.save(flush: true)) {
                chain(action: "list", model: [ridServiceProvidedError: ridServiceProvidedInstance])
                return
            }

            flash.message = message(code: 'default.created.message', args: [message(code: 'ridServiceProvided.label', default: 'RidServiceProvided'), ridServiceProvidedInstance.id])
            redirect(action: "list")
        }.invalidToken {
            flash.alerts << "Don't click the create button more than one time to make duplicated submission!"
            redirect(action: "list")
        }
    }

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
        withForm {
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
                            "Another rank has updated this RidServiceProvided while you were editing")
                    chain(action: "list", model: [ridServiceProvidedError: ridServiceProvidedInstance])
                    return
                }
            }

            ridServiceProvidedInstance.properties = params

            if (!ridServiceProvidedInstance.save(flush: true)) {
                chain(action: "list", model: [ridServiceProvidedError: ridServiceProvidedInstance])
                return
            }

            flash.message = message(code: 'default.updated.message', args: [message(code: 'ridServiceProvided.label', default: 'RidServiceProvided'), ridServiceProvidedInstance.id])
            redirect(action: "list")
        }.invalidToken {
            flash.alerts << "Don't click the update button more than one time to make duplicated submission!"
            redirect(action: "list")
        }
    }

}
