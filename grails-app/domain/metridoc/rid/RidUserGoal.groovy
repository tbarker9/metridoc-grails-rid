package metridoc.rid

class RidUserGoal {

    static hasMany = [ridTransaction: RidConsTransaction]
    static belongsTo = [ridLibraryUnit: RidLibraryUnit]

    String name
    Integer inForm = 0

    String toString() {
        return "${name}"
    }

    static constraints = {
        name(blank: false, nullable: false, validator: { val, obj ->
            def withSameNameAndType = RidUserGoal.findByNameAndRidLibraryUnitAndIdNotEqual(obj.name, obj.ridLibraryUnit, obj.id)
            return !withSameNameAndType
        })
        inForm(nullable: false, inList: [0, 1, 2])
        ridTransaction(nullable: true)
        ridLibraryUnit(nullable: true)
    }
}
