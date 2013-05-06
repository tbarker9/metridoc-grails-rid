package metridoc.rid

class RidTransactionAdminController {

    static homePage = [title: "RID Transaction Administration", adminOnly: true,
            description: "Updates Lookup Tables for RID Transaction Database"]

    def index() {
        redirect(controller: "RidLibraryUnit", action: "index")
    }

    def list(Integer max) {
        redirect(controller: "RidLibraryUnit", action: "index")
    }
}
