package metridoc.rid

class RidAdminTransactionController {

    static homePage = [title: "RID Transaction Administration", adminOnly: true,
            description: "Updates Lookup Tables for RID Transaction Database"]

    static accessControl = { role(name: "ROLE_ADMIN") }

    def ridStatisticsService

    def index() {
        session.setAttribute("transType", new String("consultation"))//Sets default mode to consultation
        session.setAttribute("prev", new String("RidAdminLibraryUnit"))
        session.setAttribute("display", new String("dropdown"))
        redirect(controller: "RidAdminLibraryUnit", action: "index")
    }

    def list(Integer max) {
        session.setAttribute("transType", new String("consultation"))//Sets default mode to consultation
        redirect(controller: "RidAdminLibraryUnit", action: "index")
    }

    def consultation() {
        session.setAttribute("transType", new String("consultation"))
        if (session.getAttribute("prev").equals("stats")) {
            redirect(controller: "RidAdminTransaction", action: "stats")
        } else {
            redirect(controller: session.getAttribute("prev"), action: "index")
        }
    }

    def instructional() {
        session.setAttribute("transType", new String("instructional"))

        if (session.getAttribute("prev").equals("RidAdminCourseSponsor") ||
                session.getAttribute("prev").equals("RidAdminModeOfConsultation") ||
                session.getAttribute("prev").equals("RidAdminServiceProvided") ||
                session.getAttribute("prev").equals("RidAdminUserGoal")) {
            redirect(controller: "RidAdminLibraryUnit", action: "index")
        } else if (session.getAttribute("prev").equals("stats")) {
            redirect(controller: "RidAdminTransaction", action: "stats")
        } else {
            redirect(controller: session.getAttribute("prev"), action: "index")
        }
    }

    def stats() {
        def queryResult = ridStatisticsService.getStats(params, session.getAttribute("transType"))
        render(view: "/ridAdminTransaction/stats",
                model: [statResults: queryResult])
        session.setAttribute("prev", "stats")
        return
    }

    def statSearch() {
        session.setAttribute("prev", new String("statSearch"))
    }

    def statQuery(Integer max) {
        def queryResult = ridStatisticsService.searchStats(params, session.getAttribute("transType"))

        render(view: "searchStatResults",
                model: [statResults: queryResult])
        session.setAttribute("prev", new String("statSearch"))
        return
    }

    def switchMode() {
        redirect(controller: "RidTransaction", action: "index")
    }
}
