package metridoc.rid



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(RidEntityAffiliation)
class RidEntityAffiliationTests {

    void testBootStrap() {
        List<String> eAffiliation = Arrays.asList("SAS", "SEAS", "Wharton", "GSE", "Vet", "Nursing", "Med",
                "Dental", "SP2", "Design", "UPHS", "CHOP", "Annenberg", "Law", "Penn Other (please indicate)",
                "Outside Entity (please indicate)")
        for (String i in eAffiliation) {
            def e = new RidEntityAffiliation(name: i, inForm: 1)
            e.save()
            if(e.hasErrors()) println e.errors
        }

        def lst = RidEntityAffiliation.list()
        assert lst.size() > 0
        for (RidEntityAffiliation i in lst)
            assert  i.inForm == 1
    }
}
