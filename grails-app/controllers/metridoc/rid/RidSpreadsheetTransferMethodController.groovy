package metridoc.rid

abstract class RidSpreadsheetTransferMethodController {
    def download() {
        def file = new File(ridSpreadsheetBootStrapService.DEFAULT_SPREADSHEET_DIRECTORY + "/" + params.sname)
        if (!file.exists()) {
            flash.message = "File not found"
        }
        try {
            response.setContentType('application/vnd.openxmlformats-officedocument.spreadsheetml.sheet')
            response.setHeader("Content-disposition", "filename=${file.name}")
            response.outputStream << file.newInputStream() // Performing a binary stream copy
        } catch (Exception e) {
            flash.alerts << e.message
        }
    }
}
