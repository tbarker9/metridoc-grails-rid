package metridoc.rid

abstract class RidInsTransactionBase {

    // statement of work
    Integer prepTime = 0
    Integer eventLength = 1
    Integer attendanceTotal
    String notes
    String staffPennkey
    String sessionDescription
    String otherLocation
    String instructionalMaterials

    // roles
    String userName
    String facultySponsor
    String courseName
    String courseNumber
    String otherRank
    String otherSchool
    String coInstructor

    // Calendar dateOfInstruction = Calendar.getInstance()
    Date dateOfInstruction = new Date()

}
