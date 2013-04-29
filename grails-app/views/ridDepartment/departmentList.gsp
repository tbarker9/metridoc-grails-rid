<%@ page import="metridoc.rid.RidDepartment" %>
<g:set var="entityName"
       value="${message(code: 'ridDepartment.label', default: 'RidDepartment')}"/>

%{--<md:report>--}%
    <r:external dir="css" file="pagination.css" plugin="metridoc-rid"/>
    <r:external dir="css" file="table.css" plugin="metridoc-rid"/>
    <!--[if !IE]><!-->
    %{--<r:external dir="css" file="floating_tables_for_admin_6.css" plugin="metridoc-rid"/>--}%
    <!--<![endif]-->

    %{--<div class="md-application-content">--}%
        <div id="list-ridDepartment" class="content scaffold-list">
            <h1><g:message code="default.list.label" args="[entityName]"/></h1>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <g:sortableColumn property="name"
                                          title="${message(code: 'ridDepartment.name.label', default: 'Name')}"/>
                        <g:sortableColumn property="fullName"
                                          title="${message(code: 'ridDepartment.fullName.label', default: 'Full Name')}"/>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${ridDepartmentInstanceList}" var="ridDepartmentInstance">
                        <tr>
                            <td>${fieldValue(bean: ridDepartmentInstance, field: "name")}</td>
                            <td>${fieldValue(bean: ridDepartmentInstance, field: "fullName")}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </div>
    %{--</div>--}%
%{--</md:report>--}%
