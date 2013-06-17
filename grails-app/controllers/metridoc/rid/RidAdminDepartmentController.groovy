package metridoc.rid

class RidAdminDepartmentController extends RidAdminBaseController {
    Class domainClass = RidDepartment


    def departmentList() {
        def instances = RidDepartment.where { name != "" }.sort('name')
        [ridDepartmentInstanceList: instances.list(), ridDepartmentInstanceTotal: instances.count()]
    }
}
