<%@ page import="metridoc.rid.RidDepartment" %>
<g:set var="entityName"
       value="${message(code: 'ridDepartment.label', default: 'RidDepartment')}"/>

<md:report>
    <r:external dir="css" file="pagination.css" plugin="metridoc-rid"/>
    <r:external dir="css" file="table.css" plugin="metridoc-rid"/>
    <!--[if !IE]><!-->
    <r:external dir="css" file="floating_tables_for_admin_5.css" plugin="metridoc-rid"/>
    <!--<![endif]-->

    <div class="md-application-content">
        <g:render template="/ridTransactionAdmin/tabs" plugin="metridoc-rid"/>
        <g:render template="/ridTransactionAdmin/modal" plugin="metridocRid"
                  model="[title: entityName + ' Create/Edit']"/>

        <div id="list-ridDepartment" class="content scaffold-list" role="main">
            <h1>
                <g:message code="default.list.label" args="[entityName]"/>
                <a data-tooltip="Creating" href="create?dummy=${org.apache.commons.lang.math.RandomUtils.nextInt()}"
                   data-target="#myModal" data-toggle="modal">
                    <i title="Create Departmental Affiliation" class="icon-plus-sign-alt"></i>
                </a>
            </h1>

            <g:hasErrors bean="${ridDepartmentError}">
                <div class="errors">
                    <g:renderErrors bean="${ridDepartmentError}" as="list"/>
                </div>
            </g:hasErrors>

            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <g:sortableColumn property="name"
                                      title="${message(code: 'ridDepartment.name.label', default: 'Name')}"/>
                    <g:sortableColumn property="fullName"
                                      title="${message(code: 'ridDepartment.fullName.label', default: 'Full Name')}"/>
                    <th>Number of RidTransaction</th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${ridDepartmentInstanceList}" var="ridDepartmentInstance">
                    <tr>
                        <td>
                            <a data-toggle="modal"
                               href="edit/${ridDepartmentInstance.id}?dummy=${org.apache.commons.lang.math.RandomUtils.nextInt()}"
                               data-target="#myModal">
                                ${fieldValue(bean: ridDepartmentInstance, field: "name")}
                            </a>
                        </td>
                        <td>${fieldValue(bean: ridDepartmentInstance, field: "fullName")}</td>
                        <td>${ridDepartmentInstance?.ridTransaction?.size()}</td>
                    </tr>
                </g:each>
                </tbody>
            </table>
            <g:if test="${ridDepartmentInstanceTotal > 10}">
                <div class="pagination">
                    <g:paginate total="${ridDepartmentInstanceTotal}"/>
                </div>
            </g:if>
        </div>
    </div>
</md:report>
