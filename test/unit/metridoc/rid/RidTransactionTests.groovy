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
    void testConstraints() {
        mockForConstraintsTests(RidTransaction)

        def ridTransaction = new RidTransaction(interactTimes: 20)
        assert !ridTransaction.validate()

        def ridTransaction2 = new RidTransaction(interactTimes: 60)
        assert !ridTransaction2.validate()
        assert "max" == ridTransaction2.errors["interactTimes"]
    }

    @Test
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
