package metridoc.rid

class RidCourseSponsor {

    static hasMany = [ridTransaction:RidTransaction]

    String name
    Integer inForm = 0

    String toString(){
        return "${name}"
    }

    static constraints = {
        name(blank: false, nullable: true)
        inForm(nullable: false, inList: [0,1,2])
        ridTransaction(nullable: true)
    }
}