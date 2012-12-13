package metridoc.rid

class RidTransactionAdminController {

    static homePage = [title: "RID Transaction Administration", adminOnly: true,
            description: "Updates Lookup Tables for RID Transaction Database"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {}
}
