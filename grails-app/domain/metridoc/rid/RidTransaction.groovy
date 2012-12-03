package metridoc.rid

class RidTransaction {

    static belongsTo = [departmentalAffilication: RidDepartmentalAffiliation,
                      courseSponsor: RidCourseSponsor,
                      productConnected: RidProductConnected,
                      consutlationMode: RidConsutlationMode,
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

    // description
    String librarian
    String patronEmail

    static constraints = {
        // statement of work
        customerQuestion(blank: true, nullable: true, maxSize: 500)
        interactTimes(nullable: false, max: 50)
        followUpContact(blank: true, nullable: true, maxSize: 50)
        prepTime(nullable: false)
        eventLength(nullable: false)
        notes(blank: true, nullable: true, maxSize: 200)
        // roles
        facultySponsor(blank: true, nullable: true, maxSize: 300)
        courseName(blank: true, nullable: true, maxSize: 300)
        courseNumber(blank: true, nullable: true, maxSize: 300)
        //customer(nullable: true)
        //entityAffiliation(nullable: true)
        //departmentalAffilication(nullable: true)
        //courseSponsor(nullable: true)
        // description
        librarian(blank: true, nullable: true, maxSize: 40)
        patronEmail(blank: true, nullable: true, email: true, maxSize: 40)
        //serviceProvided(nullable: true)
        //consutlationMode(nullable: true)
        //productConnected(nullable: true)
    }

}
