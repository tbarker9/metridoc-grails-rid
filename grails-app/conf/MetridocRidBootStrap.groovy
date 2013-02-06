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
import org.apache.commons.lang.RandomStringUtils
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
                List<String> dAffiliation = Arrays.asList(
                        "ABBREV", "AAMW", "ABIO", "ACCT", "ADMN", "ADMS", "AFAM", "AFRC", "AFST", "AMCS", "AMCV",
                        "AMES", "ANAT", "ANCH", "ANCS", "ANNS", "ANTH", "ARBR", "ARCH", "ARTH", "AS", "ASAM", "ASTR",
                        "BE", "BENF", "BIBB", "BIOC", "BIOL", "BIOM", "BIOP", "BIOT", "BLAW", "BMB", "BMP", "BPUB",
                        "BURS", "CAMB", "CBE", "CELL", "CENG", "CGS", "CHE", "CHEM", "CINE", "CIS", "CISE", "CIVE",
                        "CLAR", "CLCT", "CLSD", "CLST", "CMSC", "COGS", "COLH", "COLL", "COML", "COMM", "CONV", "CPLN",
                        "CRIM", "CSEN", "DANH", "DBCH", "DCAR", "DEMG", "DEND", "DENT", "DENV", "DMCB", "DOMD", "DORT",
                        "DOSP", "DPED", "DPRD", "DPTH", "DRAD", "DRST", "DSCI", "DYNM", "EALC", "EAS", "ECAP", "ECON",
                        "EDUC", "EE", "EENG", "EESC", "EMTM", "ENAS", "ENGL", "ENMG", "ENVS", "EPID", "ESE", "EXEN",
                        "FILM", "FINA", "FNAR", "FNCE", "FOLK", "GAFH", "GAFL", "GAS", "GCB", "GENE", "GENH", "GEOL",
                        "GEPH", "GRMN", "GSFA", "HCAD", "HCMG", "HGEN", "HIST", "HSOC", "HSPV", "HSSC", "HSTP", "HUMA",
                        "IDAT", "IMUN", "INSC", "INSP", "INSR", "INTL", "INTR", "INTS", "JIO", "JWST", "LALS", "LARP",
                        "LAUD", "LAW", "LGIC", "LGST", "LIBS", "LING", "LSMP", "LTAM", "MAPP", "MATH", "MEAM", "MED",
                        "MEDE", "MGMT", "MICB", "MICR", "MKTG", "MMP", "MOLB", "MSCI", "MSE", "MSEN", "MUSC", "NELC",
                        "NSCI", "NURS", "OBGY", "OPIM", "OPRE", "ORST", "PARA", "PATH", "PEDI", "PHIL", "PHRM", "PHSO",
                        "PHYS", "PPE", "PPMT",  "PRES", "PROV", "PSCI", "PSYC", "PTHB", "PUBH", "PYCH", "RADI", "REAL",
                        "REGR", "RELS", "ROML", "ROMP", "RSCI", "RSMD", "SARS", "SAST", "SEAS", "SENG", "SGER", "SLAV",
                        "SOCI", "SS", "SSYS", "STAT", "STUH", "SWRK", "SYS", "SYSE", "SYST", "TCOM", "THAR", "UDES",
                        "UPRF", "URBS", "USA", "VANB", "VCSN", "VCSP", "VET", "VISR", "VLST", "VMED", "VPTH", "VSUR",
                        "WCIT", "WEM", "WH", "WHCP", "WHG", "WSTD", "WRIT"
                )
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
                    def c = new RidCourseSponsor(name: i, inForm: "YES, and no indication needed")
                    c.save()
                    if(c.hasErrors()) println c.errors
                }
                new RidCourseSponsor(name: "Outside Entity (please indicate)", inForm: "YES, and indication required").save()
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
                    def c = new RidModeOfConsutlation(name: i)
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
                //RandomStringUtils.randomAlphanumeric(450)
                for (int i = 0; i < 500; i++) {
                    def t = new RidTransaction(staffPennkey: "012345667",
                            customerQuestion: RandomStringUtils.randomAlphanumeric(i%500+1), interactTimes: i%50, prepTime: i%4,
                            eventLength: i, followUpContact: "Sample Contact", notes: "Sample Notes",
                            facultySponsor: "Sample Sponsor", courseName: "Sample Course Name", courseNumber: "LIB001",
                            //otherCustomer: "others", otherEntityAffiliation: "others", otherCourseSponsor: "others",
                            //otherService: "others",
                            librarian: "librarian", patronEmail: "sample@gmail.com",
                            departmentalAffilication: RidDepartmentalAffiliation.get(1),
                            courseSponsor: RidCourseSponsor.get(1),
                            productConnected: RidProductConnected.get(1),
                            modeOfConsutlation: RidModeOfConsutlation.get(1),
                            customer: RidCustomer.get(1),
                            serviceProvided: RidServiceProvided.get(1),
                            entityAffiliation: RidEntityAffiliation.get(1)
                    )
                    t.save(failOnError: true)
                    if(t.hasErrors()) println t.errors
                }
                break

            case "production": break
        }
    }
    def destroy = {}
}