package metridoc.rid

class RidAdminDepartmentController extends RidAdminBaseController {
    Class domainClass = RidDepartment

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def instances = RidDepartment.where { name != "" }
        [ridDepartmentInstanceList: instances.list(params), ridDepartmentInstanceTotal: instances.count()]
    }

    def departmentList() {
        def instances = RidDepartment.where { name != "" }.sort('name')
        [ridDepartmentInstanceList: instances.list(), ridDepartmentInstanceTotal: instances.count()]
    }
}
