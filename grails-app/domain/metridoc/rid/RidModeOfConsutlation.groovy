package metridoc.rid

class RidModeOfConsutlation {
    static hasMany = [ridTransaction:RidTransaction]

    String name
    String toString(){
        return "${name}"
    }

    static constraints = {
        name(blank: false, nullable: true)
        ridTransaction(nullable: true)
    }
}
