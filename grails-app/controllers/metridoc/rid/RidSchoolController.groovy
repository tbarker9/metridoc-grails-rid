package metridoc.rid

class RidSchoolController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        session.setAttribute("prev", new String("RidSchool"))
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        session.setAttribute("prev", new String("RidSchool"))
        params.max = Math.min(max ?: 10, 100)
        [ridSchoolInstanceList: RidSchool.list(params), ridSchoolInstanceTotal: RidSchool.count()]
    }

    def create() {
        [ridSchoolInstance: new RidSchool(params)]
    }

    def save() {
        withForm {
            def ridSchoolInstance = new RidSchool(params)
            if (!ridSchoolInstance.save(flush: true)) {
                chain(action: "list", model: [ridSchoolError: ridSchoolInstance])
                return
            }

            flash.message = message(code: 'default.created.message', args: [message(code: 'ridSchool.label', default: 'RidSchool'), ridSchoolInstance.id])
            redirect(action: "list")
        }.invalidToken {
            flash.alerts << "Don't click the create button more than one time to make duplicated submission!"
            redirect(action: "list")
        }
    }

    def edit(Long id) {
        def ridSchoolInstance = RidSchool.get(id)
        if (!ridSchoolInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridSchool.label', default: 'RidSchool'), id])
            redirect(action: "list")
            return
        }

        [ridSchoolInstance: ridSchoolInstance]
    }

    def update(Long id, Long version) {
        withForm {
            def ridSchoolInstance = RidSchool.get(id)
            if (!ridSchoolInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridSchool.label', default: 'RidSchool'), id])
                redirect(action: "list")
                return
            }

            if (version != null) {
                if (ridSchoolInstance.version > version) {
                    ridSchoolInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                            [message(code: 'ridSchool.label', default: 'RidSchool')] as Object[],
                            "Another rank has updated this RidSchool while you were editing")
                    chain(action: "list", model: [ridSchoolError: ridSchoolInstance])
                    return
                }
            }

            ridSchoolInstance.properties = params

            if (!ridSchoolInstance.save(flush: true)) {
                chain(action: "list", model: [ridSchoolError: ridSchoolInstance])
                return
            }

            flash.message = message(code: 'default.updated.message', args: [message(code: 'ridSchool.label', default: 'RidSchool'), ridSchoolInstance.id])
            redirect(action: "list")
        }.invalidToken {
            flash.alerts << "Don't click the update button more than one time to make duplicated submission!"
            redirect(action: "list")
        }
    }

}
