package metridoc.rid

class RidTransaction {

    static hasMany = [departmentalAffilication: RidDepartmentalAffiliation,
                      courseSponsor: RidCourseSponsor,
                      productConnected: RidProductConnected,
                      consutlationMode: RidConsutlationMode]

    // Statement of work
    Integer interactTimes = 0
    String followUpContact
    Integer prepTime = 0
    Integer eventLength = 0
    String notes

    // Roles
    String facultySponsor
    String courseName
    String courseNumber

    // description
    String librarian
    String patronEmail

    static constraints = {
        interactTimes nullable: false, max: 50
        followUpContact blank: true, nullable: true, maxSize: 50
        prepTime nullable: false
        eventLength nullable: false
        notes blank: true, nullable: true, maxSize: 200

        facultySponsor(blank: true, nullable: true, maxSize: 40)
        courseName(blank: true, nullable: true, maxSize: 30)
        courseNumber(blank: true, nullable: true, maxSize: 10)
        departmentalAffilication(nullable: true)
        courseSponsor(nullable: true)

        librarian(blank: true, nullable: true, maxSize: 40)
        patronEmail(blank: true, nullable: true, email: true, maxSize: 40)
        productConnected(nullable: true)
        consutlationMode(nullable: true)
    }
}
