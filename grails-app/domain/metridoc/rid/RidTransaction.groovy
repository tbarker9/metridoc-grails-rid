package metridoc.rid

class RidTransaction {

    static belongsTo = [departmentalAffilication: RidDepartmentalAffiliation,
                      courseSponsor: RidCourseSponsor,
                      productConnected: RidProductConnected,
                      modeOfConsutlation: RidModeOfConsutlation,
                      customer: RidCustomer,
                      serviceProvided: RidServiceProvided,
                      entityAffiliation: RidEntityAffiliation]

    static transients = ['otherCustomer', 'otherEntityAffiliation', 'otherCourseSponsor', 'otherService']

    // statement of work
    String customerQuestion
    Integer interactTimes = 0
    String followUpContact
    Integer prepTime = 0
    Integer eventLength = 0
    String notes
    String staffPennkey

    // roles
    String facultySponsor
    String courseName
    String courseNumber
    String otherCustomer
    String otherEntityAffiliation
    String otherCourseSponsor

    // description
    String librarian
    String patronEmail
    String otherService
    // Calendar dateOfConsultation = Calendar.getInstance()
    Date dateOfConsultation = new Date()

    static constraints = {
        // STATEMENT OF WORK
        customerQuestion(blank: false, nullable: false, maxSize: 500)
        interactTimes(nullable: false, min: 0, max: 50)
        followUpContact(blank: true, nullable: true, maxSize: 50)
        prepTime(nullable: false, min: 0)
        eventLength(nullable: false, min: 0)
        notes(blank: true, nullable: true, maxSize: 500)
        staffPennkey(blank: false, nullable: false, maxSize: 100)
        // ROLES
        facultySponsor(blank: true, nullable: true, maxSize: 300)
        courseName(blank: true, nullable: true, maxSize: 100)
        courseNumber(blank: true, nullable: true, maxSize: 100)
        //customer(nullable: true)
        otherCustomer(blank: true, nullable: true, maxSize: 50)
        //entityAffiliation(nullable: true)
        otherEntityAffiliation(blank: true, nullable: true, maxSize: 50)
        //departmentalAffilication(nullable: true)
        //courseSponsor(nullable: true)
        otherCourseSponsor(blank: true, nullable: true, maxSize: 50)
        // DESCRIPTION
        librarian(blank: true, nullable: true, maxSize: 100)
        patronEmail(blank: true, nullable: true, email: true, maxSize: 100)
        //serviceProvided(nullable: true)
        otherService(blank: true, nullable: true, maxSize: 100)
        //modeOfConsutlation(nullable: true)
        //productConnected(nullable: true)
    }

    String toString(){
        String customerQ = new String(customerQuestion)
        if (customerQ!=null && customerQ.length() > 32)
            customerQ = customerQ.substring(0,32) + "..."
        return "ID: ${id}; customerQuestion: ${customerQ}"
    }
}
