package metridoc.rid

import org.springframework.core.io.ClassPathResource

class RidSpreadsheetBootstrapService {


    def downloadSpreadsheets() {

        def dir_path = System.getProperty("user.home") + "/.metridoc/files/rid/libraryUnit"
        new File(dir_path).mkdirs()
        def tar_dir = new File(dir_path)

        def resource = new ClassPathResource("spreadsheet")
        if (resource.exists()) {
            def ss_dir = resource.getFile()
            ss_dir.eachFile {
                if (it.isFile()) {
                    log.info "Downloading" + it.getName()
                    it.renameTo(new File(tar_dir, it.getName()))
                }
            }
        } else {
            log.error "Spreadsheet folder not found"
        }
    }
}
