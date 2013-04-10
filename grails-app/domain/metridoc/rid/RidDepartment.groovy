package metridoc.rid

class RidDepartment {

    static hasMany = [ridTransaction: RidTransaction]

    String name

    String toString() {
        return "${name}"
    }

    static constraints = {
        name(blank: false, nullable: false, unique: true)
        ridTransaction(nullable: true)
    }
}
