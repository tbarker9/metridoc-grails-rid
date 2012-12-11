import metridoc.rid.RidDepartmentalAffiliation
import grails.util.GrailsUtil
import metridoc.rid.RidTransaction
import metridoc.rid.RidCourseSponsor
import metridoc.rid.RidProductConnected
import metridoc.rid.RidCourseSponsor
import metridoc.rid.RidProductConnected
import metridoc.rid.RidDepartmentalAffiliation
import metridoc.rid.RidModeOfConsutlation
import metridoc.rid.RidCustomer
import metridoc.rid.RidServiceProvided
import metridoc.rid.RidEntityAffiliation
/**
 * Created with IntelliJ IDEA.
 * User: xiaofant
 * Date: 11/20/12
 * Time: 10:37 AM
 * To change this template use File | Settings | File Templates.
 */
class MetridocRidBootStrap {
    def init = { servletContext ->
        switch(GrailsUtil.environment){
            case "development":
                // for departmental affiliation
                List<String> dAffiliation = Arrays.asList("Biology", "Cinema Studies", "History", "Philosophy", "...")
                for (String i in dAffiliation) {
                    def da = new RidDepartmentalAffiliation(name: i)
                    da.save()
                    if(da.hasErrors()) println da.errors
                }
                // for entity affiliation
                List<String> eAffiliation = Arrays.asList("SAS", "SEAS", "Wharton", "GSE", "Vet", "Nursing", "Med",
                        "Dental", "SP2", "Design", "UPHS", "CHOP", "Annenberg", "Law")
                for (String i in eAffiliation) {
                    def e = new RidEntityAffiliation(name: i, inForm: 1)
                    e.save()
                    if(e.hasErrors()) println e.errors
                }
                new RidEntityAffiliation(name: "Penn Other (please indicate)", inForm: 2).save()
                new RidEntityAffiliation(name: "Outside Entity (please indicate)", inForm: 2).save()
                // for course sponsor
                List<String> cSponsor = Arrays.asList("SAS", "SEAS", "Wharton", "GSE", "Vet", "Nursing", "Med",
                        "Dental", "SP2", "Design", "Annenberg", "Law", "Coursera",
                        "Independent Research")
                for (String i in cSponsor) {
                    def c = new RidCourseSponsor(name: i, inForm: 1)
                    c.save()
                    if(c.hasErrors()) println c.errors
                }
                new RidCourseSponsor(name: "Outside Entity (please indicate)", inForm: 2).save()
                // for product connected
                List<String> pConnected = Arrays.asList("Senior Thesis", "Master Thesis", "Dissertation",
                        "Independent Research")
                for (String i in pConnected) {
                    def p = new RidProductConnected(name: i)
                    p.save()
                    if(p.hasErrors()) println p.errors
                }
                // for mode of consutlation
                List<String> cMode = Arrays.asList("Email", "Phone", "Chat", "In person(in library)",
                        "In person(outside library)", "Conferencing software")
                for (String i in cMode) {
                    def c = new RidModeOfConsutlation(mode: i)
                    c.save()
                    if(c.hasErrors()) println c.errors
                }
                // for customer
                List<String> customers = Arrays.asList("Undergrad student", "Grad student", "PhD/PostDoc",
                        "Clinical: intern, resident, fellow", "Clinical: other", "Faculty", "Alumni", "Stuff")
                for (String i in customers) {
                    def c = new RidCustomer(name: i, inForm: 1)
                    c.save()
                    if(c.hasErrors()) println c.errors
                }
                new RidCustomer(name: "Other (please indicate)", inForm: 2).save()
                //new RidCustomer(name: "***Shouldn't be shown***", inForm: 0).save()
                // for service provided
                List<String> sProvided = Arrays.asList("Course design", "Research assistance",
                        "Acquisition/Collections", "Copyright assistance for author", "Copyright assistance for user",
                        "Coorect an operational or service breakdown (incl. tech support)",
                        "Instructional support (apart from course design)", "Clinic", "Tour",
                        "Clinical decision making", "Tech/Software instrcution", "Search instruction",
                        "Literature search", "Mobile technology", "Bibliometrics or citation metrics",
                        "Consumer health", "Admin/policy questions", "Citation management instruction",
                        "Scholarly Commons/Repository Services", "Creating faculty profiles/selected works/VIVO")
                for (String i in sProvided) {
                    def s = new RidServiceProvided(name: i, inForm: 1)
                    s.save()
                    if(s.hasErrors()) println s.errors
                }
                new RidServiceProvided(name: "Other (please indicate)", inForm: 2).save()
                break
            case "production": break
        }
    }
    def destroy = {}
}