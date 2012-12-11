package metridoc.rid

import grails.test.mixin.TestFor
import grails.validation.ValidationException
import org.junit.Test

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(RidTransaction)
class RidTransactionTests {

    @Test
    // failed now since make some non-null value null
    void testConstraints() {
        mockForConstraintsTests(RidTransaction)

        def ridTransaction = new RidTransaction(interactTimes: 20)
        assert ridTransaction.validate()

        def ridTransaction2 = new RidTransaction(interactTimes: 60)
        assert !ridTransaction2.validate()
        assert "max" == ridTransaction2.errors["interactTimes"]

        def contact = new String("1234567890123456789012345678901234567890123456789012")
        def ridTransaction3 = new RidTransaction(followUpContact: contact)
        assert !ridTransaction3.validate()
        assert "maxSize" == ridTransaction3.errors["followUpContact"]

        def ridTransaction4 = new RidTransaction(followUpContact: contact.substring(5))
        assert ridTransaction4.validate()
    }

    @Test
    // failed now since make some non-null value null
    void testDatabase() {
        mockForConstraintsTests(RidTransaction)

        def ridTransaction = new RidTransaction(interactTimes: 20)
        def ridTransaction2 = new RidTransaction(interactTimes: 60)
        try {
            ridTransaction.save(failOnError: true)
            ridTransaction2.save(failOnError: true)
        }
        catch (ValidationException e) {
            println(e.message)
        }
        assert 1 == RidTransaction.list().size()
        assert ridTransaction == RidTransaction.get(1)

        int i = 0
        while (i++ < 16) {
            def ridT = new RidTransaction(interactTimes: i)
            ridT.save()
        }
        assert 17 == RidTransaction.list().size()

        def query = RidTransaction.where {
            interactTimes in 15..65
        }
        assert 3 == query.count()

        //RidTransaction.deleteAll(query.list())
    }
}
