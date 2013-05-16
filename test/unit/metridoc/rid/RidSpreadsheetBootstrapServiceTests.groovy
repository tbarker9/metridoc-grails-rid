package metridoc.rid

import grails.test.mixin.TestFor
import org.springframework.core.io.ClassPathResource


@TestFor(RidSpreadsheetBootstrapService)
class RidSpreadsheetBootstrapServiceTests {

    void testSomething() {
        //assert SystemUtils.USER_HOME
        assert new ClassPathResource("spreadsheet/Transaction_List.xlsx").exists()
        assert new ClassPathResource("spreadsheet").exists()
    }
}
