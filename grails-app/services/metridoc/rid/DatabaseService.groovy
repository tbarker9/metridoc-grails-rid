package metridoc.rid

class DatabaseService {

    def serviceMethod(Map params, RidTransaction ridTransactionInstance) {
        String otherUser = params.otherUser
        //println(otherUser)
        if (otherUser!=null && !otherUser.isEmpty()) {
            if (RidUser.findAllByName(otherUser).size()==0) {
                def c = new RidUser(name: otherUser, inForm: 0)
                c.save()
                if(c.hasErrors()) println c.errors
            }
            if (RidUser.findAllByName(otherUser).size() > 0)
                ridTransactionInstance.user = RidUser.findByName(otherUser)
        }

        String otherUserAffiliation = params.otherUserAffiliation
        if (otherUserAffiliation!=null && !otherUserAffiliation.isEmpty()) {
            if (RidUserAffiliation.findAllByName(otherUserAffiliation).size() == 0) {
                def e = new RidUserAffiliation(name: otherUserAffiliation, inForm: 0)
                e.save()
                if(e.hasErrors()) println e.errors
            }
            if (RidUserAffiliation.findAllByName(otherUserAffiliation).size() > 0)
                ridTransactionInstance.userAffiliation = RidUserAffiliation.findByName(otherUserAffiliation)
        }

        String otherCourseSponsor = params.otherCourseSponsor
        if (otherCourseSponsor!=null && !otherCourseSponsor.isEmpty()) {
            if (RidCourseSponsor.findAllByName(otherCourseSponsor).size() == 0) {
                def c = new RidCourseSponsor(name: otherCourseSponsor, inForm: 0)
                c.save()
                if(c.hasErrors()) println c.errors
            }
            if (RidCourseSponsor.findAllByName(otherCourseSponsor).size() > 0)
                ridTransactionInstance.courseSponsor = RidCourseSponsor.findByName(otherCourseSponsor)
        }

        String otherService = params.otherService
        if (otherService!=null && !otherService.isEmpty()) {
            if (RidServiceProvided.findAllByName(otherService).size() == 0) {
                def s = new RidServiceProvided(name: otherService, inForm: 0)
                s.save()
                if(s.hasErrors()) println s.errors
            }
            if (RidServiceProvided.findAllByName(otherService).size() > 0)
                ridTransactionInstance.serviceProvided = RidServiceProvided.findByName(otherService)
        }
    }
}
