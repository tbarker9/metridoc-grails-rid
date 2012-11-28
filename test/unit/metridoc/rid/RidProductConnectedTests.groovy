package metridoc.rid



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(RidProductConnected)
class RidProductConnectedTests {

    @Test
    void testBootStrap() {
        List<String> pConnected = Arrays.asList("Senior Thesis", "Master Thesis", "Dissertation",
                "Independent Research");
        for (String i in pConnected) {
            def p = new RidProductConnected(name: i)
            p.save()
            if(p.hasErrors()) println p.errors
        }

        assert RidProductConnected.list().size() > 0
    }
}
