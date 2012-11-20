package metridoc.rid

class RidTransaction {

    Integer interactTimes = 0
    String followUpContact
    Integer prepTime = 0
    Integer eventLength = 0
    String notes

    static constraints = {
        interactTimes nullable: false, max: 50
        followUpContact blank: true, nullable: true, maxSize: 30
        prepTime nullable: false
        eventLength nullable: false
        notes blank: true, nullable: true, maxSize: 200
    }
}
