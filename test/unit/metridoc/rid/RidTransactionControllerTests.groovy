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
    def serviceMocker

    void setUp() {
        serviceMocker = mockFor(RidTransactionService, true)  // mock the service
        serviceMocker.demand.createNewInstanceMethod { params, ridTransactionInstance ->  }
        serviceMocker.demand.ajaxMethod {params -> return [book:"Great"] }
        controller.ridTransactionService = serviceMocker.createMock(); // inject it into the controller
    }

    void testAjaxReturn() {
        controller.ajaxChooseType()
        assert '{"book":"Great"}' == response.text
        assert "Great" == response.json.book
    }

    void testCreate() {
        def model = controller.create()
        assert model.ridTransactionInstance.prepTime == 0
        assert model.ridTransactionInstance.dateOfConsultation != null
        assert model.ridTransactionInstance.staffPennkey == null
        assert model.ridTransactionInstance.ridReportType == null
        assert model.ridTransactionInstance.template == Boolean.FALSE
    }

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
//        controller.ridTransactionService = [
//                createNewInstanceMethod: { params, ridTransactionInstance ->
//                }
//        ]
        controller.remember()
        assert controller.response.status == 302
        assert response.redirectedUrl.startsWith("/ridTransaction/show/")
    }

    void testValidSave() {
        def token = SynchronizerTokensHolder.store(session)
        controller.params[SynchronizerTokensHolder.TOKEN_KEY] = token.generateToken("/ridTransaction/list")
        controller.params[SynchronizerTokensHolder.TOKEN_URI] = "/ridTransaction/list"

        controller.flash.alerts = []
        mockDomain(RidCourseSponsor, [
                [name:'testCourseSponsor', inForm: 1],
                [name:'testCourseSponsor2', inForm: 0]
        ])
        assert RidCourseSponsor.count() == 2

        params.staffPennkey = 'test123'
        params.userQuestion = 'testQ'
        params.dateOfConsultation = '02/03/2012'
        params.interactTimes = 1
        params.prepTime = 2
        params.eventLength = "3"
        params.notes = 'testN'
        params.courseNumber = '123123'

        params.departmentalAffilication = new RidDepartmentalAffiliation(name: "testDA")
        params.courseSponsor = RidCourseSponsor.get(2)
        params.userGoal = new RidUserGoal(name: "testUG")
        params.modeOfConsultation = new RidModeOfConsultation(name: "testMC")
        params.user = new RidUser(name: "testU")
        params.serviceProvided = new RidServiceProvided(name: "testSP")
        params.userAffiliation = new RidUserAffiliation(name: "testUA")
        params.ridReportType = new RidReportType(name: "testRT")

        controller.save()

        assert controller.flash.alerts.size() == 0
        assert response.redirectedUrl == '/ridTransaction/show/1'
        assert RidTransaction.count() == 1
        assert RidTransaction.get(1).prepTime == 2
        assert RidTransaction.get(1).eventLength == 3
        assert RidTransaction.get(1).courseSponsor.name == 'testCourseSponsor2'
    }
}
