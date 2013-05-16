/**
 * Created with IntelliJ IDEA.
 * User: xiaofant
 * Date: 11/20/12
 * Time: 10:37 AM
 * To change this template use File | Settings | File Templates.
 */
class MetridocRidBootStrap {

    def ridBootStrapService
    def ridSpreadsheetBootstrapService
    def init = { servletContext ->
        ridBootStrapService.bootStrapContents()
        ridSpreadsheetBootstrapService.downloadSpreadsheets()
    }

    def destroy = {}
}