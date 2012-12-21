package metridoc.rid

class DatabaseService {

    def serviceMethod(Map params, RidTransaction ridTransactionInstance) {
        String otherCustomer = params.otherCustomer
        //println(otherCustomer)
        if (otherCustomer!=null && !otherCustomer.isEmpty()) {
            if (RidCustomer.findAllByName(otherCustomer).size()==0) {
                def c = new RidCustomer(name: otherCustomer, inForm: 0)
                c.save()
                if(c.hasErrors()) println c.errors
            }
            if (RidCustomer.findAllByName(otherCustomer).size() > 0)
                ridTransactionInstance.customer = RidCustomer.findByName(otherCustomer)
        }

        String otherEntityAffiliation = params.otherEntityAffiliation
        if (otherEntityAffiliation!=null && !otherEntityAffiliation.isEmpty()) {
            if (RidEntityAffiliation.findAllByName(otherEntityAffiliation).size() == 0) {
                def e = new RidEntityAffiliation(name: otherEntityAffiliation, inForm: 0)
                e.save()
                if(e.hasErrors()) println e.errors
            }
            if (RidEntityAffiliation.findAllByName(otherEntityAffiliation).size() > 0)
                ridTransactionInstance.entityAffiliation = RidEntityAffiliation.findByName(otherEntityAffiliation)
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
