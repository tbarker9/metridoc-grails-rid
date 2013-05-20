package metridoc.rid

import grails.test.mixin.TestFor
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.springframework.core.io.ClassPathResource


@TestFor(RidManageLibraryUnitSpreadsheetsService)
class RidManageLibraryUnitSpreadsheetsServiceTests {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder()

    @Test
    void "SpreadsheetService method downloadSpreadsheets should transfer spreadsheets into local hidden directory metridoc under subdirectory files"() {
        assert new ClassPathResource("spreadsheet/Transaction_List.xlsx").exists()

        File dest = temporaryFolder.newFolder("dest")
        assert dest.exists()
        service.unitSpreadsheetDir = dest
        service.transferSpreadsheets()
        assert new File(temporaryFolder.getRoot().canonicalPath + "/dest/Transaction_list.xlsx").exists()
    }
}
