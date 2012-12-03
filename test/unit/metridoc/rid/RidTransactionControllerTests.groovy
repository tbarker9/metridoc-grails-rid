package metridoc.rid



import org.junit.*
import grails.test.mixin.*

@TestFor(RidTransactionController)
@Mock(RidTransaction)
class RidTransactionControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/ridTransaction/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.ridTransactionInstanceList.size() == 0
        assert model.ridTransactionInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.ridTransactionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.ridTransactionInstance != null
        assert view == '/ridTransaction/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/ridTransaction/show/1'
        assert controller.flash.message != null
        assert RidTransaction.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/ridTransaction/list'

        populateValidParams(params)
        def ridTransaction = new RidTransaction(params)

        assert ridTransaction.save() != null

        params.id = ridTransaction.id

        def model = controller.show()

        assert model.ridTransactionInstance == ridTransaction
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/ridTransaction/list'

        populateValidParams(params)
        def ridTransaction = new RidTransaction(params)

        assert ridTransaction.save() != null

        params.id = ridTransaction.id

        def model = controller.edit()

        assert model.ridTransactionInstance == ridTransaction
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/ridTransaction/list'

        response.reset()

        populateValidParams(params)
        def ridTransaction = new RidTransaction(params)

        assert ridTransaction.save() != null

        // test invalid parameters in update
        params.id = ridTransaction.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/ridTransaction/edit"
        assert model.ridTransactionInstance != null

        ridTransaction.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/ridTransaction/show/$ridTransaction.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        ridTransaction.clearErrors()

        populateValidParams(params)
        params.id = ridTransaction.id
        params.version = -1
        controller.update()

        assert view == "/ridTransaction/edit"
        assert model.ridTransactionInstance != null
        assert model.ridTransactionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/ridTransaction/list'

        response.reset()

        populateValidParams(params)
        def ridTransaction = new RidTransaction(params)

        assert ridTransaction.save() != null
        assert RidTransaction.count() == 1

        params.id = ridTransaction.id

        controller.delete()

        assert RidTransaction.count() == 0
        assert RidTransaction.get(ridTransaction.id) == null
        assert response.redirectedUrl == '/ridTransaction/list'
    }
}
