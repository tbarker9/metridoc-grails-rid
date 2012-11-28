package metridoc.rid

class RidDepartmentalAffiliation {

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
