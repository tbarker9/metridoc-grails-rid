package metridoc.rid

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import org.codehaus.groovy.grails.web.servlet.mvc.SynchronizerTokensHolder

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(RidDepartmentalAffiliationController)
@Mock(RidDepartmentalAffiliation)
class RidDepartmentalAffiliationControllerTests {

    void testInvalidSave() {
        def token = SynchronizerTokensHolder.store(session)
        controller.params[SynchronizerTokensHolder.TOKEN_KEY] = token.generateToken("/ridDepartmentalAffiliation/list")
        controller.params[SynchronizerTokensHolder.TOKEN_URI] = "/ridDepartmentalAffiliation/list"

        controller.params.inForm = 1
        controller.save()

        assert response.redirectedUrl == '/ridDepartmentalAffiliation/list'
        assert flash.message == null
        assert RidDepartmentalAffiliation.count() == 0
    }

    void testValidSave() {
        def token = SynchronizerTokensHolder.store(session)
        controller.params[SynchronizerTokensHolder.TOKEN_KEY] = token.generateToken("/ridDepartmentalAffiliation/list")
        controller.params[SynchronizerTokensHolder.TOKEN_URI] = "/ridDepartmentalAffiliation/list"

        controller.flash.alerts = []
        controller.params.name = "test"
        controller.params.inForm = 1
        controller.save()

        assert response.redirectedUrl == '/ridDepartmentalAffiliation/list'
        assert flash.message != null
        assert flash.alerts.size() == 0
        assert RidDepartmentalAffiliation.count() == 1

        // Cannot submit repeatedly
        response.reset()
        controller.save()
        assert response.redirectedUrl == '/ridDepartmentalAffiliation/list'
        assert flash.alerts.get(0) == "Don't click the create button more than one time to make dulplicated submission!"
    }
}
