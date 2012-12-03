package metridoc.rid



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(RidCustomer)
class RidCustomerTests {

    void testBootStrap() {
        List<String> customers = Arrays.asList("Undergrad student", "Grad student", "PhD/PostDoc",
                "Clinical: intern, resident, fellow", "Clinical: other", "Faculty", "Alumni", "Stuff",
                "Other (please indicate)")
        for (String i in customers) {
            def c = new RidCustomer(name: i)
            c.save()
            if(c.hasErrors()) println c.errors
        }

        assert RidCustomer.list().size() > 0
    }
}
