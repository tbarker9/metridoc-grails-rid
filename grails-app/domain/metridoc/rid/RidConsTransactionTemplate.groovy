package metridoc.rid

class RidConsTransactionTemplate extends RidConsTransactionBase {

    static belongsTo = [department: RidDepartment,
            courseSponsor: RidCourseSponsor,
            userGoal: RidUserGoal,
            modeOfConsultation: RidModeOfConsultation,
            rank: RidRank,
            serviceProvided: RidServiceProvided,
            school: RidSchool,
            ridLibraryUnit: RidLibraryUnit]

    static transients = ['otherRank', 'otherUser', 'otherUserGoal', 'otherModeOfConsultation', 'otherSchool',
            'otherCourseSponsor', 'otherService']

    //Records the owner/creator of this template
    //Leaves it blank if this is NOT a template
    String templateOwner = ""

}
