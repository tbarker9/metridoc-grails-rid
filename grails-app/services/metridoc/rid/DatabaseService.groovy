package metridoc.rid

class DatabaseService {

    def serviceMethod(Map params) {
        String otherCustomer = params.otherCustomer
        //println(otherCustomer)
        if (otherCustomer!=null && !otherCustomer.isEmpty()) {
            def c = new RidCustomer(name: otherCustomer, inForm: 0)
            c.save()
            if(c.hasErrors()) println c.errors
        }

        String otherEntityAffiliation = params.otherEntityAffiliation
        if (otherEntityAffiliation!=null && !otherEntityAffiliation.isEmpty()) {
            def e = new RidEntityAffiliation(name: otherEntityAffiliation, inForm: 0)
            e.save()
            if(e.hasErrors()) println e.errors
        }

        String otherCourseSponsor = params.otherCourseSponsor
        if (otherCourseSponsor!=null && !otherCourseSponsor.isEmpty()) {
            def c = new RidCourseSponsor(name: otherCourseSponsor, inForm: 0)
            c.save()
            if(c.hasErrors()) println c.errors
        }

        String otherService = params.otherService
        if (otherService!=null && !otherService.isEmpty()) {
            def s = new RidServiceProvided(name: otherService, inForm: 0)
            s.save()
            if(s.hasErrors()) println s.errors
        }
    }
}
