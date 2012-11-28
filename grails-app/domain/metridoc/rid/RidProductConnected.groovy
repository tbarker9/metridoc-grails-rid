package metridoc.rid

class RidProductConnected {

    static belongsTo = [ridTransaction:RidTransaction]

    String name
    String toString(){
        return "${name}"
    }

    static constraints = {
        name(blank: true, nullable: true, maxSize: 40)
        ridTransaction(nullable: true)
    }
}
