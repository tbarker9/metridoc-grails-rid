import metridoc.rid.RidDepartmentalAffiliation
import grails.util.GrailsUtil
import metridoc.rid.RidTransaction
import metridoc.rid.RidCourseSponsor
import metridoc.rid.RidProductConnected
import metridoc.rid.RidCourseSponsor
import metridoc.rid.RidProductConnected
import metridoc.rid.RidDepartmentalAffiliation
import metridoc.rid.RidConsutlationMode
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
                List<String> dAffiliation = Arrays.asList("Biology", "Cinema Studies", "History", "Philosophy", "...");
                for (String i in dAffiliation) {
                    def da = new RidDepartmentalAffiliation(name: i)
                    da.save()
                    if(da.hasErrors()) println da.errors
                }
                // for course sponsor
                List<String> cSponsor = Arrays.asList("SAS", "SEAS", "Wharton", "GSE", "Vet", "Nursing", "Med",
                                                      "Dental", "SP2", "Design", "Annenberg", "Law", "Coursera",
                                                      "Independent Research", "Outside Entity(please indicate)");
                for (String i in cSponsor) {
                    def c = new RidCourseSponsor(name: i)
                    c.save()
                    if(c.hasErrors()) println c.errors
                }
                // for product connected
                List<String> pConnected = Arrays.asList("Senior Thesis", "Master Thesis", "Dissertation",
                                                        "Independent Research")
                for (String i in pConnected) {
                    def p = new RidProductConnected(name: i)
                    p.save()
                    if(p.hasErrors()) println p.errors
                }
                // for consutlation mode
                List<String> cMode = Arrays.asList("Email", "Phone", "Chat", "In person(in library)",
                        "In person(outside library)", "Conferencing software")
                for (String i in cMode) {
                    def p = new RidConsutlationMode(mode: i)
                    p.save()
                    if(p.hasErrors()) println p.errors
                }
                break
            case "production": break
        }
    }
    def destroy = {}
}