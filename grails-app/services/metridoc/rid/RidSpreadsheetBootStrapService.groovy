package metridoc.rid

import org.codehaus.groovy.grails.io.support.ClassPathResource

class RidSpreadsheetBootStrapService {

    final def DEFAULT_SPREADSHEET_DIRECTORY = System.getProperty("user.home") + "/.metridoc/files/rid/libraryUnit"
    File unitSpreadsheetDir = new File(DEFAULT_SPREADSHEET_DIRECTORY)

    def transferSpreadsheets() {

        unitSpreadsheetDir.mkdirs()

        def resource = new ClassPathResource("spreadsheet")
        if (resource.exists()) {
            def ssDir = resource.getFile()
            ssDir.eachFile {
                if (it.isFile()) {
                    log.info "Transferring " + it.getName()
                    it.renameTo(new File(unitSpreadsheetDir, it.getName()))
                }
            }
        } else {
            log.error "Can't find classpath ${resource.path}, so can't transfer spreadsheets to the local directory .metridoc/files: "
        }
    }

}
