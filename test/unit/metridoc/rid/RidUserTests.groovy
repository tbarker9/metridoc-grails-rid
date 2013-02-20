package metridoc.rid



import grails.test.mixin.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(RidUser)
class RidUserTests {

    void testBootStrap() {
        List<String> user = Arrays.asList("Undergrad student", "Grad student", "PhD/PostDoc",
                "Clinical: intern, resident, fellow", "Clinical: other", "Faculty", "Alumni", "Stuff",
                "Other (please indicate)")
        for (String i in user) {
            def c = new RidUser(name: i)
            c.save()
            if(c.hasErrors()) println c.errors
        }

        assert RidUser.list().size() > 0
    }
}
