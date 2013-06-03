package metridoc.rid

class RidAdminLocationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    static accessControl = {
        role(name: "ROLE_ADMIN")
    }

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ridLocationInstanceList: RidLocation.list(params), ridLocationInstanceTotal: RidLocation.count()]
    }

    def create() {
        [ridLocationInstance: new RidLocation(params)]
    }

    def save() {
        withForm {
            def ridLocationInstance = new RidLocation(params)
            if (!ridLocationInstance.save(flush: true)) {
                chain(action: "list", model: [ridLocationError: ridLocationInstance])
                return
            }

            flash.message = message(code: 'default.created.message', args: [message(code: 'ridLocation.label', default: 'RidLocation'), ridLocationInstance.id])
            redirect(action: "list")
        }.invalidToken {
            flash.alerts << "Don't click the create button more than one time to make duplicated submission!"
            redirect(action: "list")
        }
    }

    def edit(Long id) {
        def ridLocationInstance = RidLocation.get(id)
        if (!ridLocationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridLocation.label', default: 'RidLocation'), id])
            redirect(action: "list")
            return
        }

        [ridLocationInstance: ridLocationInstance]
    }

    def update(Long id, Long version) {
        withForm {
            def ridLocationInstance = RidLocation.get(id)
            if (!ridLocationInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridLocation.label', default: 'RidLocation'), id])
                redirect(action: "list")
                return
            }

            if (version != null) {
                if (ridLocationInstance.version > version) {
                    ridLocationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                            [message(code: 'ridLocation.label', default: 'RidLocation')] as Object[],
                            "Another rank has updated this RidLocation while you were editing")
                    chain(action: "list", model: [ridLocationError: ridLocationInstance])
                    return
                }
            }

            ridLocationInstance.properties = params

            if (!ridLocationInstance.save(flush: true)) {
                chain(action: "list", model: [ridLocationError: ridLocationInstance])
                return
            }

            flash.message = message(code: 'default.updated.message', args: [message(code: 'ridLocation.label', default: 'RidLocation'), ridLocationInstance.id])
            redirect(action: "list")
        }.invalidToken {
            flash.alerts << "Don't click the update button more than one time to make duplicated submission!"
            redirect(action: "list")
        }
    }

}
