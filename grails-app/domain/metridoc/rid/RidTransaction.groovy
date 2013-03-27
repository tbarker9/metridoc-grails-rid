package metridoc.rid

class RidTransaction {

    static belongsTo = [departmentalAffilication: RidDepartmentalAffiliation,
            courseSponsor: RidCourseSponsor,
            userGoal: RidUserGoal,
            modeOfConsultation: RidModeOfConsultation,
            user: RidUser,
            serviceProvided: RidServiceProvided,
            userAffiliation: RidUserAffiliation,
            ridReportType: RidReportType]

    static transients = ['otherUser', 'otherUserGoal', 'otherModeOfConsultation', 'otherUserAffiliation',
            'otherCourseSponsor', 'otherService']

    String templateOwner = ""
    String spreadsheetName

    // statement of work
    String userQuestion
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
    String otherUser
    String otherUserGoal
    String otherModeOfConsultation
    String otherUserAffiliation
    String otherCourseSponsor

    // description
    String librarian
    String patronEmail
    String otherService
    // Calendar dateOfConsultation = Calendar.getInstance()
    Date dateOfConsultation = new Date()

    static constraints = {
        // STATEMENT OF WORK
        userQuestion(blank: false, nullable: false, maxSize: 500)
        interactTimes(nullable: false, min: 0, max: 50)
        followUpContact(blank: true, nullable: true, maxSize: 50)
        prepTime(nullable: false, min: 0, max: 50)
        eventLength(nullable: false, min: 0, max: 50)
        notes(blank: true, nullable: true, maxSize: 500)
        staffPennkey(blank: false, nullable: false, maxSize: 100)
        // ROLES
        facultySponsor(blank: true, nullable: true, maxSize: 100)
        courseName(blank: true, nullable: true, maxSize: 100)
        courseNumber(blank: true, nullable: true, maxSize: 100)
        userGoal(nullable: true)
        otherUser(blank: true, nullable: true, maxSize: 50)
        otherUserGoal(blank: true, nullable: true, maxSize: 50)
        otherModeOfConsultation(blank: true, nullable: true, maxSize: 50)
        //userAffiliation(nullable: true)
        otherUserAffiliation(blank: true, nullable: true, maxSize: 50)
        //departmentalAffilication(nullable: true)
        //courseSponsor(nullable: true)
        otherCourseSponsor(blank: true, nullable: true, maxSize: 50)
        // DESCRIPTION
        librarian(blank: true, nullable: true, maxSize: 100)
        patronEmail(blank: true, nullable: true, email: true, maxSize: 100)
        //serviceProvided(nullable: true)
        otherService(blank: true, nullable: true, maxSize: 100)
        //modeOfConsultation(nullable: true)
        //userGoal(nullable: true)
        //ridReportType(nullable: true)
        templateOwner(blank: true)
        spreadsheetName(nullable: true, blank: true)
    }

    String toString() {
        String userQ = new String(userQuestion)
        if (userQ != null && userQ.length() > 32)
            userQ = userQ.substring(0, 32) + "..."
        return "ID: ${id}; userQuestion: ${userQ}"
    }
}
