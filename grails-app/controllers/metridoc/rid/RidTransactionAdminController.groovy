package metridoc.rid

class RidTransactionAdminController {

    static homePage = [title: "RidTransactionAdmin", adminOnly: true, description: "Used to revise database"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {}
}
