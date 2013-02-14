import grails.util.Environment
import metridoc.rid.RidDepartmentalAffiliation
import grails.util.GrailsUtil
import metridoc.rid.RidTransaction
import metridoc.rid.RidCourseSponsor
import metridoc.rid.RidProductConnected
import metridoc.rid.RidCourseSponsor
import metridoc.rid.RidProductConnected
import metridoc.rid.RidDepartmentalAffiliation
import metridoc.rid.RidModeOfConsultation
import metridoc.rid.RidCustomer
import metridoc.rid.RidServiceProvided
import metridoc.rid.RidEntityAffiliation
import org.apache.commons.lang.RandomStringUtils
import metridoc.rid.RidGroupType
import metridoc.rid.RidModeOfConsultation

/**
 * Created with IntelliJ IDEA.
 * User: xiaofant
 * Date: 11/20/12
 * Time: 10:37 AM
 * To change this template use File | Settings | File Templates.
 */
class MetridocRidBootStrap {
    def init = { servletContext ->
        switch (Environment.current) {
            case Environment.DEVELOPMENT:
                if (!RidTransaction.first()) {
                    println "Creating test data for RID database"
                    RidTransaction.withNewTransaction{
                        // for group type
                        List<String> gType = Arrays.asList("type1", "type2", "type3", "type4", "type5")
                        for (String i in gType) {
                            if (!RidGroupType.findByName(i)) {
                                def gt = new RidGroupType(name: i)
                                gt.save()
                                if (gt.hasErrors()) println gt.errors
                            }
                        }
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
                                "PHYS", "PPE", "PPMT", "PRES", "PROV", "PSCI", "PSYC", "PTHB", "PUBH", "PYCH", "RADI", "REAL",
                                "REGR", "RELS", "ROML", "ROMP", "RSCI", "RSMD", "SARS", "SAST", "SEAS", "SENG", "SGER", "SLAV",
                                "SOCI", "SS", "SSYS", "STAT", "STUH", "SWRK", "SYS", "SYSE", "SYST", "TCOM", "THAR", "UDES",
                                "UPRF", "URBS", "USA", "VANB", "VCSN", "VCSP", "VET", "VISR", "VLST", "VMED", "VPTH", "VSUR",
                                "WCIT", "WEM", "WH", "WHCP", "WHG", "WSTD", "WRIT"
                        )
                        for (String i in dAffiliation) {
                            if (!RidDepartmentalAffiliation.findByName(i)) {
                                def da = new RidDepartmentalAffiliation(name: i)
                                da.save()
                                if (da.hasErrors()) println da.errors
                            }
                        }
                        // for entity affiliation
                        List<String> eAffiliation = Arrays.asList("SAS", "SEAS", "Wharton", "GSE", "Vet", "Nursing", "Med",
                                "Dental", "SP2", "Design", "UPHS", "CHOP", "Annenberg", "Law")
                        for (String i in eAffiliation) {
                            if (!RidEntityAffiliation.findByName(i)) {
                                def e = new RidEntityAffiliation(name: i, inForm: 1)
                                e.save()
                                if (e.hasErrors()) println e.errors
                            }
                        }
                        def outsideEntityPleaseIndicate = "Outside Entity (please indicate)"
                        def pennOtherPleaseIndicate = "Penn Other (please indicate)"
                        if (!RidEntityAffiliation.findByName(pennOtherPleaseIndicate)) {
                            new RidEntityAffiliation(name: pennOtherPleaseIndicate, inForm: 2).save()
                        }

                        if (!RidEntityAffiliation.findByName(outsideEntityPleaseIndicate)) {
                            new RidEntityAffiliation(name: outsideEntityPleaseIndicate, inForm: 2).save()
                        }

                        // for course sponsor
                        List<String> cSponsor = Arrays.asList("SAS", "SEAS", "Wharton", "GSE", "Vet", "Nursing", "Med",
                                "Dental", "SP2", "Design", "Annenberg", "Law", "Coursera",
                                "Independent Research")
                        for (String i in cSponsor) {
                            if (!RidCourseSponsor.findByName(i)) {
                                def c = new RidCourseSponsor(name: i, inForm: 1)
                                c.save()
                                if (c.hasErrors()) println c.errors
                            }
                        }

                        if (!RidCourseSponsor.findByName(outsideEntityPleaseIndicate)) {
                            new RidCourseSponsor(name: outsideEntityPleaseIndicate, inForm: 2).save()
                        }
                        // for customer
                        List<String> customers = Arrays.asList("Undergrad student", "Grad student", "PhD/PostDoc",
                                "Clinical: intern, resident, fellow", "Clinical: other", "Faculty", "Alumni", "Stuff")
                        for (String i in customers) {
                            if (!RidCustomer.findByName(i)) {
                                def c = new RidCustomer(name: i, inForm: 1)
                                c.save()
                                if (c.hasErrors()) println c.errors
                            }
                        }
                        def otherPleaseIndicate = "Other (please indicate)"
                        if (RidCustomer.findByName(otherPleaseIndicate)) {
                            new RidCustomer(name: otherPleaseIndicate, inForm: 2).save()
                        }
                        //new RidCustomer(name: "***Shouldn't be shown***", inForm: 0).save()
                        // for product connected
                        List<String> pConnected = Arrays.asList("Senior Thesis", "Master Thesis", "Dissertation",
                                "Independent Research")
                        int id = 0
                        for (String i in pConnected) {
                            if (!RidProductConnected.findByName(i)) {
                                def p = new RidProductConnected(name: i, ridGroupType: RidGroupType.get(id++ % 3 + 1))
                                p.save()
                                if (p.hasErrors()) println p.errors
                            }
                        }
                        // for mode of consutlation
                        id = 0
                        List<String> cMode = Arrays.asList("Email", "Phone", "Chat", "Conferencing software",
                                "In person(in library)", "In person(outside library)")
                        for (String i in cMode) {
                            if (!RidModeOfConsultation.findByName(i)) {
                                def c = new RidModeOfConsultation(name: i, ridGroupType: RidGroupType.get(id++ % 3 + 1))
                                c.save()
                                if (c.hasErrors()) println c.errors
                            }
                        }
                        // for service provided
                        id = 0
                        List<String> sProvided = Arrays.asList("Course design", "Research assistance",
                                "Acquisition/Collections", "Copyright assistance for author", "Copyright assistance for user",
                                "Coorect an operational or service breakdown (incl. tech support)",
                                "Instructional support (apart from course design)", "Clinic", "Tour",
                                "Clinical decision making", "Tech/Software instrcution", "Search instruction",
                                "Literature search", "Mobile technology", "Bibliometrics or citation metrics",
                                "Consumer health", "Admin/policy questions", "Citation management instruction",
                                "Scholarly Commons/Repository Services", "Creating faculty profiles/selected works/VIVO")
                        for (String i in sProvided) {
                            if (!RidServiceProvided.findByName(i)) {
                                def s = new RidServiceProvided(name: i, inForm: 1, ridGroupType: RidGroupType.get(id++ % 3 + 1))
                                s.save()
                                if (s.hasErrors()) println s.errors
                            }
                        }
                        //TODO: how to hundle this for RidGroupType ??
                        new RidServiceProvided(name: "Other (please indicate)", inForm: 2).save()

                        // for ridTransaction (only for demo)
                        for (int i = 0; i < 500; i++) {
                            def t = new RidTransaction(staffPennkey: "012345667",
                                    customerQuestion: RandomStringUtils.randomAlphanumeric(i % 500 + 1), interactTimes: i % 50, prepTime: i % 4,
                                    eventLength: i, followUpContact: "Sample Contact", notes: "Sample Notes",
                                    facultySponsor: "Sample Sponsor", courseName: "Sample Course Name", courseNumber: "LIB001",
                                    //otherCustomer: "others", otherEntityAffiliation: "others", otherCourseSponsor: "others",
                                    //otherService: "others",
                                    librarian: "librarian", patronEmail: "sample@gmail.com",
                                    departmentalAffilication: RidDepartmentalAffiliation.get(1),
                                    courseSponsor: RidCourseSponsor.get(1),
                                    productConnected: RidProductConnected.get(1),
                                    modeOfConsultation: RidModeOfConsultation.get(1),
                                    customer: RidCustomer.get(1),
                                    serviceProvided: RidServiceProvided.get(1),
                                    entityAffiliation: RidEntityAffiliation.get(1),
                                    ridGroupType: RidGroupType.get(1)
                            )

                            try {
                                if (!t.save()) {
                                    if (t.hasErrors()) println t.errors
                                }
                            } catch (Exception e) {
                                log.error("an error occurred during bootstrap that will crash the entire application")
                                throw e
                            }
                        }
                    }
                }
                break

            case Environment.PRODUCTION: break
        }
    }
    def destroy = {}
}