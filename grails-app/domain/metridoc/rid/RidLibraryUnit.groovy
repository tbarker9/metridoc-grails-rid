package metridoc.rid

class RidLibraryUnit {

    static hasMany = [userGoal: RidUserGoal,
            modeOfConsultation: RidModeOfConsultation,
            serviceProvided: RidServiceProvided,
            ridTransaction: RidTransaction]

    String name

    String toString() {
        return "${name}"
    }

    static constraints = {
        name(blank: false, nullable: false, unique: true)
    }
}
