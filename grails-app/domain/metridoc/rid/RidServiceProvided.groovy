package metridoc.rid

class RidServiceProvided {

    static hasMany = [ridTransaction:RidTransaction]

    String name
    Integer inForm = 0

    String toString(){
        return "${name}"
    }

    static constraints = {
        name(blank: false, nullable: true)
        inForm(nullable: false)
        ridTransaction(nullable: true)
    }
}
