package metridoc.rid

class RidInsTransactionTemplate extends RidInsTransactionBase {

    static belongsTo = [department: RidDepartment,
            //rank: RidRank,
            school: RidSchool,
            ridLibraryUnit: RidLibraryUnit,
            location: RidLocation,
            sessionType: RidSessionType,
            audience: RidAudience,
            instructionalMaterials: RidInstructionalMaterials]

    static hasMany = [extraDepartments: RidDepartment]
    static transients = [/*'otherRank',*/ 'otherUser', 'otherSchool', 'otherLocation', 'otherSessionType',
            'otherSessionType', 'otherAudience', 'otherInstructionalMaterials']

    //Records the owner/creator of this template
    //Leaves it blank if this is NOT a template
    String templateOwner = ""

}
