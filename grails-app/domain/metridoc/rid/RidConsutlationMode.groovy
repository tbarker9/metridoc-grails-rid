package metridoc.rid

class RidConsutlationMode {
    static belongsTo = [ridTransaction:RidTransaction]

    String mode
    String toString(){
        return "${mode}"
    }

    static constraints = {
        mode(blank: true, nullable: true, maxSize: 40)
        ridTransaction(nullable: true)
    }
}
