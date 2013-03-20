package metridoc.rid

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.codehaus.groovy.grails.web.servlet.mvc.SynchronizerTokensHolder

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(RidTransactionController)
@Mock(RidTransaction)
class RidTransactionControllerTests {
//    def serviceMocker
//
//    void setUp() {
//        serviceMocker = mockFor(RidTransactionService)  // mock the service
//        //controller.ridTransactionService = serviceMocker.createMock(); // inject it into the controller
//    }

    void testDuplicatedSubmission() {
        controller.flash.alerts = []
        // This only works if you do the test using "extends ControllerUnitTestCase"
        // controller.request.invalidToken = true
        controller.remember()
        // for a direct calling, 302 should always be the status
        assert controller.response.status == 302
        assert response.redirectedUrl == '/ridTransaction/list'
    }

    void testValidSubmission() {
        controller.params.dateOfConsultation = "09/09/2012"
        def token = SynchronizerTokensHolder.store(session)
        controller.params[SynchronizerTokensHolder.TOKEN_KEY] = token.generateToken("/ridTransaction/remember")
        controller.params[SynchronizerTokensHolder.TOKEN_URI] = "/ridTransaction/remember"
        controller.ridTransactionService = [
                createNewInstanceMethod: { params, ridTransactionInstance ->
                }
        ]
        controller.remember()
        assert controller.response.status == 302
        assert response.redirectedUrl.startsWith("/ridTransaction/show/")
    }
}
