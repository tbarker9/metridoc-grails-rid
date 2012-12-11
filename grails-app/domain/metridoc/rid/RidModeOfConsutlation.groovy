package metridoc.rid

class RidModeOfConsutlation {
    static hasMany = [ridTransaction:RidTransaction]

    String mode
    String toString(){
        return "${mode}"
    }

    static constraints = {
        mode(blank: false, nullable: true)
        ridTransaction(nullable: true)
    }
}
