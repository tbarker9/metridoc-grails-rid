<%@ page import="metridoc.rid.RidUserGoal" %>
<g:set var="entityName" value="${message(code: 'ridUserGoal.label', default: 'RidUserGoal')}" />

<md:report>
    <!--[if !IE]><!-->
    <r:external dir="css" file="floating_tables_for_admin.css" plugin="metridoc-rid"/>
    <!--<![endif]-->

    <div style="font-size: 12px">
        <g:render template="/ridTransactionAdmin/tabs" plugin="metridoc-rid"/>
    </div>

    <div class="md-application-content">
        <g:render template="/ridCourseSponsor/modal" plugin="metridocRid" model="[title: entityName]"/>

            <div id="list-ridUserGoal" class="content scaffold-list" role="main">
                <h1>
                    <g:message code="default.list.label" args="[entityName]" />
                    <a data-tooltip="Creating" href="create" data-target="#myModal" data-toggle="modal">
                        <i title="Create Product Connected" class="icon-plus-sign-alt"></i>
                    </a>
                </h1>

                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        
                        <g:sortableColumn property="name" title="${message(code: 'ridUserGoal.name.label', default: 'Name')}" />

                        <g:sortableColumn property="ridReportType" title="${message(code: 'ridUserGoal.ridReportType.label', default: 'Report Type')}" />

                        <th>Number of RidTransaction</th>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${ridUserGoalInstanceList}" status="i" var="ridUserGoalInstance">
                        <tr>
                            
                            <td>
                                <a data-toggle="modal" href="edit/${ridUserGoalInstance.id}" data-target="#myModal">
                                    ${fieldValue(bean: ridUserGoalInstance, field: "name")}
                                </a>
                            </td>

                            <td>${fieldValue(bean: ridUserGoalInstance, field: "ridReportType")}</td>

                            <td>${ridUserGoalInstance?.ridTransaction?.size()}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
                <g:if test="${ridUserGoalInstanceTotal > 10}">
                    <div class="pagination">
                        <g:paginate total="${ridUserGoalInstanceTotal}" />
                    </div>
                </g:if>
            </div>
    </div>
</md:report>
