package metridoc.rid



import grails.test.mixin.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(RidModeOfConsutlation)
class RidModeOfConsutlationTests {

    void testBootStrap() {
        List<String> cMode = Arrays.asList("Email", "Phone", "Chat", "In person(in library)",
                "In person(outside library)", "Conferencing software")
        for (String i in cMode) {
            def c = new RidModeOfConsutlation(mode: i)
            c.save()
            if(c.hasErrors()) println c.errors
        }
        assert RidModeOfConsutlation.list().size() > 0
    }
}
