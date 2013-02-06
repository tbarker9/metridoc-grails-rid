package metridoc.rid

class RidCourseSponsor {

    static hasMany = [ridTransaction:RidTransaction]

    String name
    String inForm = "NO"

    String toString(){
        return "${name}"
    }

    static constraints = {
        name(blank: false, nullable: false)
//        inForm(nullable: false, inList: [0,1,2])
        inForm(blank: false, inList: ["NO","YES, and no indication needed","YES, and indication required"])
        //ridTransaction(nullable: true)
    }
}
