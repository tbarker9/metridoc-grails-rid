package metridoc.rid

class RidRankController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ridRankInstanceList: RidRank.list(params), ridRankInstanceTotal: RidRank.count()]
    }

    def create() {
        [ridRankInstance: new RidRank(params)]
    }

    def save() {
        withForm {
            def ridRankInstance = new RidRank(params)
            if (!ridRankInstance.save(flush: true)) {
                chain(action: "list", model: [ridRankError: ridRankInstance])
                return
            }

            flash.message = message(code: 'default.created.message', args: [message(code: 'ridRank.label', default: 'RidRank'), ridRankInstance.id])
            redirect(action: "list")
        }.invalidToken {
            flash.alerts << "Don't click the create button more than one time to make duplicated submission!"
            redirect(action: "list")
        }
    }

    def edit(Long id) {
        def ridRankInstance = RidRank.get(id)
        if (!ridRankInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridRank.label', default: 'RidRank'), id])
            redirect(action: "list")
            return
        }

        [ridRankInstance: ridRankInstance]
    }

    def update(Long id, Long version) {
        withForm {
            def ridRankInstance = RidRank.get(id)
            if (!ridRankInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridRank.label', default: 'RidRank'), id])
                redirect(action: "list")
                return
            }

            if (version != null) {
                if (ridRankInstance.version > version) {
                    ridRankInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                            [message(code: 'ridRank.label', default: 'RidRank')] as Object[],
                            "Another rank has updated this RidRank while you were editing")
                    chain(action: "list", model: [ridRankError: ridRankInstance])
                    return
                }
            }

            ridRankInstance.properties = params

            if (!ridRankInstance.save(flush: true)) {
                chain(action: "list", model: [ridRankError: ridRankInstance])
                return
            }

            flash.message = message(code: 'default.updated.message', args: [message(code: 'ridRank.label', default: 'RidRank'), ridRankInstance.id])
            redirect(action: "list")
        }.invalidToken {
            flash.alerts << "Don't click the update button more than one time to make duplicated submission!"
            redirect(action: "list")
        }
    }
}
