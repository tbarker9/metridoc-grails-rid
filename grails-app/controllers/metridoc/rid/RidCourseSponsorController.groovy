package metridoc.rid

import org.springframework.dao.DataIntegrityViolationException
import org.codehaus.groovy.grails.web.servlet.mvc.SynchronizerTokensHolder

class RidCourseSponsorController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def instances = RidCourseSponsor.where {name != ""}
        [ridCourseSponsorInstanceList: instances.list(params), ridCourseSponsorInstanceTotal: instances.count()]
    }

    def create() {
        [ridCourseSponsorInstance: new RidCourseSponsor(params)]
    }

    def save() {
        withForm {
            def ridCourseSponsorInstance = new RidCourseSponsor(params)
            if (!ridCourseSponsorInstance.save(flush: true)) {
                chain(action: "list", model: [ridCourseSponsorError: ridCourseSponsorInstance])
                return
            }

            flash.message = message(code: 'default.created.message', args: [message(code: 'ridCourseSponsor.label', default: 'RidCourseSponsor'), ridCourseSponsorInstance.id])
            redirect(action: "list")
        }.invalidToken {
            flash.alerts << "Don't click the create button more than one time to make dulplicated submission!"
            redirect(action: "list")
        }
    }

//    def show(Long id) {
//        def ridCourseSponsorInstance = RidCourseSponsor.get(id)
//        if (!ridCourseSponsorInstance) {
//            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridCourseSponsor.label', default: 'RidCourseSponsor'), id])
//            redirect(action: "list")
//            return
//        }
//
//        [ridCourseSponsorInstance: ridCourseSponsorInstance]
//    }

    def edit(Long id) {
        def ridCourseSponsorInstance = RidCourseSponsor.get(id)
        if (!ridCourseSponsorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridCourseSponsor.label', default: 'RidCourseSponsor'), id])
            redirect(action: "list")
            return
        }

        [ridCourseSponsorInstance: ridCourseSponsorInstance]
    }

    def update(Long id, Long version) {
        withForm {
            def ridCourseSponsorInstance = RidCourseSponsor.get(id)
            if (!ridCourseSponsorInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridCourseSponsor.label', default: 'RidCourseSponsor'), id])
                redirect(action: "list")
                return
            }

            if (version != null) {
                if (ridCourseSponsorInstance.version > version) {
                    ridCourseSponsorInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                            [message(code: 'ridCourseSponsor.label', default: 'RidCourseSponsor')] as Object[],
                            "Another rank has updated this RidCourseSponsor while you were editing")
                    //render(view: "edit", model: [ridCourseSponsorInstance: ridCourseSponsorInstance])
                    chain(action: "list", model: [ridCourseSponsorError: ridCourseSponsorInstance])
                    return
                }
            }

            ridCourseSponsorInstance.properties = params

            if (!ridCourseSponsorInstance.save(flush: true)) {
                chain(action: "list", model: [ridCourseSponsorError: ridCourseSponsorInstance])
                return
            }

            flash.message = message(code: 'default.updated.message', args: [message(code: 'ridCourseSponsor.label', default: 'RidCourseSponsor'), ridCourseSponsorInstance.id])
            redirect(action: "list")
        }.invalidToken {
            flash.alerts << "Don't click the update button more than one time to make dulplicated submission!"
            redirect(action: "list")
        }
    }

//    def delete(Long id) {
//        def ridCourseSponsorInstance = RidCourseSponsor.get(id)
//        if (!ridCourseSponsorInstance) {
//            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridCourseSponsor.label', default: 'RidCourseSponsor'), id])
//            redirect(action: "list")
//            return
//        }
//
//        try {
//            ridCourseSponsorInstance.delete(flush: true)
//            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ridCourseSponsor.label', default: 'RidCourseSponsor'), id])
//            redirect(action: "list")
//        }
//        catch (DataIntegrityViolationException e) {
//            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ridCourseSponsor.label', default: 'RidCourseSponsor'), id])
//            redirect(action: "list")
//        }
//    }
}
