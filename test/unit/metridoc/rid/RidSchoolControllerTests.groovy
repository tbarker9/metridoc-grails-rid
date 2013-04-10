package metridoc.rid

import grails.test.mixin.*

import org.codehaus.groovy.grails.web.servlet.mvc.SynchronizerTokensHolder

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(RidSchoolController)
@Mock(RidSchool)
class RidSchoolControllerTests {

    void testInvalidSave() {
        def token = SynchronizerTokensHolder.store(session)
        controller.params[SynchronizerTokensHolder.TOKEN_KEY] = token.generateToken("/ridSchool/list")
        controller.params[SynchronizerTokensHolder.TOKEN_URI] = "/ridSchool/list"

        controller.params.inForm = 1
        controller.save()

        assert response.redirectedUrl == '/ridSchool/list'
        assert flash.message == null
        assert RidSchool.count() == 0
    }

    void testValidSave() {
        def token = SynchronizerTokensHolder.store(session)
        controller.params[SynchronizerTokensHolder.TOKEN_KEY] = token.generateToken("/ridSchool/list")
        controller.params[SynchronizerTokensHolder.TOKEN_URI] = "/ridSchool/list"

        controller.flash.alerts = []
        controller.params.name = "test"
        controller.params.inForm = 1
        controller.save()

        assert response.redirectedUrl == '/ridSchool/list'
        assert flash.message != null
        assert flash.alerts.size() == 0
        assert RidSchool.count() == 1

        // Cannot submit repeatedly
        response.reset()
        controller.save()
        assert response.redirectedUrl == '/ridSchool/list'
        assert flash.alerts.get(0) == "Don't click the create button more than one time to make dulplicated submission!"
    }
}
