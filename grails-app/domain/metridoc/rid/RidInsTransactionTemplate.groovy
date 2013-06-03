package metridoc.rid

class RidInsTransactionTemplate extends RidInsTransactionBase {

    static belongsTo = [department: RidDepartment,
            rank: RidRank,
            school: RidSchool,
            ridLibraryUnit: RidLibraryUnit,
            location: RidLocation]

    static transients = ['otherUser', 'otherSchool', 'otherLocation']

    //Records the owner/creator of this template
    //Leaves it blank if this is NOT a template
    String templateOwner = ""

}
