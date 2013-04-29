package metridoc.rid

class RidDepartment {

    static hasMany = [ridTransaction: RidTransaction]

    String name
    String fullName

    String toString() {
        return "${name}"
    }

    static constraints = {
        name(blank: false, nullable: false, unique: true)
        fullName(blank: true, nullable: true)
        ridTransaction(nullable: true)
    }
}
