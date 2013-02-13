package metridoc.rid

class RidGroupType {

    static hasMany = [productConnected: RidProductConnected,
            modeOfConsultation: RidModeOfConsultation,
            serviceProvided: RidServiceProvided,
            ridTransaction: RidTransaction]

    String name

    String toString(){
        return "${name}"
    }

    static constraints = {
        name(blank: false, nullable: false, unique: true)
    }
}
