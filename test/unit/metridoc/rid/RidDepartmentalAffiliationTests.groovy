package metridoc.rid

import grails.test.mixin.TestFor
import org.junit.Test

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(RidDepartmentalAffiliation)
class RidDepartmentalAffiliationTests {

    @Test
    void testBootStrap() {
        mockForConstraintsTests(RidDepartmentalAffiliation)

        List<String> dAffiliation = Arrays.asList("Biology", "Cinema Studies", "History", "Philosophy", "...");
        for (String i in dAffiliation) {
            def da = new RidDepartmentalAffiliation(name: i)
            da.save()
            if (da.hasErrors()) println da.errors
        }

        assert RidDepartmentalAffiliation.list().size() > 0
    }
}
