package metridoc.rid



import org.junit.*
import grails.test.mixin.*

@TestFor(RidCustomerController)
@Mock(RidCustomer)
class RidCustomerControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/ridCustomer/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.ridCustomerInstanceList.size() == 0
        assert model.ridCustomerInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.ridCustomerInstance != null
    }

    void testSave() {
        controller.save()

        assert model.ridCustomerInstance != null
        assert view == '/ridCustomer/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/ridCustomer/show/1'
        assert controller.flash.message != null
        assert RidCustomer.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/ridCustomer/list'

        populateValidParams(params)
        def ridCustomer = new RidCustomer(params)

        assert ridCustomer.save() != null

        params.id = ridCustomer.id

        def model = controller.show()

        assert model.ridCustomerInstance == ridCustomer
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/ridCustomer/list'

        populateValidParams(params)
        def ridCustomer = new RidCustomer(params)

        assert ridCustomer.save() != null

        params.id = ridCustomer.id

        def model = controller.edit()

        assert model.ridCustomerInstance == ridCustomer
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/ridCustomer/list'

        response.reset()

        populateValidParams(params)
        def ridCustomer = new RidCustomer(params)

        assert ridCustomer.save() != null

        // test invalid parameters in update
        params.id = ridCustomer.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/ridCustomer/edit"
        assert model.ridCustomerInstance != null

        ridCustomer.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/ridCustomer/show/$ridCustomer.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        ridCustomer.clearErrors()

        populateValidParams(params)
        params.id = ridCustomer.id
        params.version = -1
        controller.update()

        assert view == "/ridCustomer/edit"
        assert model.ridCustomerInstance != null
        assert model.ridCustomerInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/ridCustomer/list'

        response.reset()

        populateValidParams(params)
        def ridCustomer = new RidCustomer(params)

        assert ridCustomer.save() != null
        assert RidCustomer.count() == 1

        params.id = ridCustomer.id

        controller.delete()

        assert RidCustomer.count() == 0
        assert RidCustomer.get(ridCustomer.id) == null
        assert response.redirectedUrl == '/ridCustomer/list'
    }
}
