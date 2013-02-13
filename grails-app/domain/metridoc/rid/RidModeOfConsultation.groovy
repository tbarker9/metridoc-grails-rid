package metridoc.rid

class RidModeOfConsultation {

    static hasMany = [ridTransaction:RidTransaction]
    static belongsTo = [ridGroupType: RidGroupType]

    String name
    String toString(){
        return "${name}"
    }

    static constraints = {
        name(blank: false, nullable: false, unique: true)
        ridTransaction(nullable: true)
        ridGroupType(nullable: true)
    }
}
