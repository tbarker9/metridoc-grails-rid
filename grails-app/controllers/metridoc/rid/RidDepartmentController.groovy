package metridoc.rid

import org.springframework.dao.DataIntegrityViolationException

class RidDepartmentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def instances = RidDepartment.where {name != ""}
        [ridDepartmentInstanceList: instances.list(params), ridDepartmentInstanceTotal: instances.count()]
    }

    def departmentList() {
        def instances = RidDepartment.where {name != ""}.sort('name')
        [ridDepartmentInstanceList: instances.list(), ridDepartmentInstanceTotal: instances.count()]
    }

    def create() {
        [ridDepartmentInstance: new RidDepartment(params)]
    }

    def save() {
        withForm {
            def ridDepartmentInstance = new RidDepartment(params)
            if (!ridDepartmentInstance.save(flush: true)) {
                chain(action: "list", model: [ridDepartmentError: ridDepartmentInstance])
                return
            }

            flash.message = message(code: 'default.created.message', args: [message(code: 'ridDepartment.label', default: 'RidDepartment'), ridDepartmentInstance.id])
            redirect(action: "list")
        }.invalidToken {
            flash.alerts << "Don't click the create button more than one time to make dulplicated submission!"
            redirect(action: "list")
        }
    }

    def edit(Long id) {
        def ridDepartmentInstance = RidDepartment.get(id)
        if (!ridDepartmentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridDepartment.label', default: 'RidDepartment'), id])
            redirect(action: "list")
            return
        }

        [ridDepartmentInstance: ridDepartmentInstance]
    }

    def update(Long id, Long version) {
        withForm {
            def ridDepartmentInstance = RidDepartment.get(id)
            if (!ridDepartmentInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridDepartment.label', default: 'RidDepartment'), id])
                redirect(action: "list")
                return
            }

            if (version != null) {
                if (ridDepartmentInstance.version > version) {
                    ridDepartmentInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                            [message(code: 'ridDepartment.label', default: 'RidDepartment')] as Object[],
                            "Another rank has updated this RidDepartment while you were editing")
                    chain(action: "list", model: [ridDepartmentError: ridDepartmentInstance])
                    return
                }
            }

            ridDepartmentInstance.properties = params

            if (!ridDepartmentInstance.save(flush: true)) {
                chain(action: "list", model: [ridDepartmentError: ridDepartmentInstance])
                return
            }

            flash.message = message(code: 'default.updated.message', args: [message(code: 'ridDepartment.label', default: 'RidDepartment'), ridDepartmentInstance.id])
            redirect(action: "list")
        }.invalidToken {
            flash.alerts << "Don't click the update button more than one time to make dulplicated submission!"
            redirect(action: "list")
        }
    }

}
