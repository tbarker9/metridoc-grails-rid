package metridoc.rid

class RidTransaction {

    static belongsTo = [departmentalAffilication: RidDepartmentalAffiliation,
                      courseSponsor: RidCourseSponsor,
                      productConnected: RidProductConnected,
                      modeOfConsutlation: RidModeOfConsutlation,
                      customer: RidCustomer,
                      serviceProvided: RidServiceProvided,
                      entityAffiliation: RidEntityAffiliation]

    // statement of work
    String customerQuestion
    Integer interactTimes = 0
    String followUpContact
    Integer prepTime = 0
    Integer eventLength = 0
    String notes

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
    Date dateOfConsultation = new Date()

    static constraints = {
        // STATEMENT OF WORK
        customerQuestion(blank: true, nullable: true, maxSize: 500)
        interactTimes(nullable: false, max: 50)
        followUpContact(blank: true, nullable: true, maxSize: 50)
        prepTime(nullable: false)
        eventLength(nullable: false)
        notes(blank: true, nullable: true, maxSize: 200)
        // ROLES
        facultySponsor(blank: true, nullable: true, maxSize: 300)
        courseName(blank: true, nullable: true, maxSize: 300)
        courseNumber(blank: true, nullable: true, maxSize: 300)
        //customer(nullable: true)
        otherCustomer(blank: true, nullable: true, maxSize: 50)
        //entityAffiliation(nullable: true)
        otherEntityAffiliation(blank: true, nullable: true, maxSize: 50)
        //departmentalAffilication(nullable: true)
        //courseSponsor(nullable: true)
        otherCourseSponsor(blank: true, nullable: true, maxSize: 50)
        // DESCRIPTION
        librarian(blank: true, nullable: true, maxSize: 40)
        patronEmail(blank: true, nullable: true, email: true, maxSize: 40)
        //serviceProvided(nullable: true)
        otherService(blank: true, nullable: true, maxSize: 100)
        //modeOfConsutlation(nullable: true)
        //productConnected(nullable: true)
    }

}
