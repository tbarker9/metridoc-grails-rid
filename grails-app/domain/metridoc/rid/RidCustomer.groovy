package metridoc.rid

class RidCustomer {

    static hasMany = [ridTransaction:RidTransaction]

    String name
    Integer inForm = 0
    Date myDate = new Date()

    String toString(){
        return "${name}"
    }

    static constraints = {
        name(blank: false, nullable: true)
        inForm(nullable: false)
       // ridTransaction(nullable: true)
    }
}
