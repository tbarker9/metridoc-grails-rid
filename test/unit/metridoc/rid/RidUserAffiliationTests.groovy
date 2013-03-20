package metridoc.rid

import grails.test.mixin.TestFor

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(RidUserAffiliation)
class RidUserAffiliationTests {

    void testBootStrap() {
        mockForConstraintsTests(RidUserAffiliation)

        List<String> uAffiliation = Arrays.asList("SAS", "SEAS", "Wharton", "GSE", "Vet", "Nursing", "Med",
                "Dental", "SP2", "Design", "UPHS", "CHOP", "Annenberg", "Law", "Penn Other (please indicate)",
                "Outside Entity (please indicate)")
        for (String i in uAffiliation) {
            def e = new RidUserAffiliation(name: i, inForm: 1)
            e.save()
            if (e.hasErrors()) println e.errors
        }

        def lst = RidUserAffiliation.list()
        assert lst.size() > 0
        for (RidUserAffiliation i in lst)
            assert i.inForm == 1
    }
}
