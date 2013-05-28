package metridoc.rid

class RidInsTransaction extends RidInsTransactionBase {

    static belongsTo = [department: RidDepartment,
            rank: RidRank,
            school: RidSchool,
            ridLibraryUnit: RidLibraryUnit,
            location: RidLocation]

    static transients = ['otherUser', 'otherSchool',
            'otherLocation']

    String spreadsheetName

    static constraints = {
        // STATEMENT OF WORK
        prepTime(nullable: false, min: 0)
        eventLength(nullable: false, min: 0)
        notes(blank: true, nullable: true, maxSize: 500)
        sessionDescription(blank: true, nullable: true, maxSize: 500)
        staffPennkey(blank: false, nullable: false, maxSize: 100)
        location(blank: false, nullable: false)
        otherLocation(blank: true, nullable: true, maxSize: 50)
        // ROLES
        facultySponsor(blank: true, nullable: true, maxSize: 100)
        courseName(blank: true, nullable: true, maxSize: 100)
        courseNumber(blank: true, nullable: true, maxSize: 100)
        otherRank(blank: true, nullable: true, maxSize: 50)
        school(nullable: true)
        otherSchool(blank: true, nullable: true, maxSize: 50)
        department(nullable: true)
        userName(blank: true, nullable: true, maxSize: 50)
        coInstructor(blank: true, nullable: true, maxSize: 50)
        // DESCRIPTION
        ridLibraryUnit(nullable: false)
        attendanceTotal(blank: false, nullable: false, min: 0)
        spreadsheetName(nullable: true, blank: true)
        instructionalMaterials(blank: true, nullable: true, maxSize: 50)
    }
}
