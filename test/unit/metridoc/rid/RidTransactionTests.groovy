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
        assert !ridTransaction.validate()

        def ridTransaction2 = new RidTransaction(interactTimes: 60)
        assert !ridTransaction2.validate()
        assert "max" == ridTransaction2.errors["interactTimes"]

        def contact = new String("1234567890123456789012345678901234567890123456789012")
        def ridTransaction3 = new RidTransaction(followUpContact: contact)
        assert !ridTransaction3.validate()
        assert "maxSize" == ridTransaction3.errors["followUpContact"]
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
        assert 0 == RidTransaction.list().size()
    }
}
