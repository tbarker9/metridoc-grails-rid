package metridoc.rid

class RidConsutlationMode {
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
