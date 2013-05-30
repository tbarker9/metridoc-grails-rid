<%@ page import="metridoc.rid.RidUserGoal" %>
<g:set var="entityName" value="${message(code: 'ridUserGoal.label', default: 'RidUserGoal')}"/>

<md:report>
    <r:external dir="css" file="pagination.css" plugin="metridoc-rid"/>
    <r:external dir="css" file="table.css" plugin="metridoc-rid"/>
    <!--[if !IE]><!-->
    <r:external dir="css" file="floating_tables_for_admin_4.css" plugin="metridoc-rid"/>
    <!--<![endif]-->

    <div class="md-application-content">
        <g:render template="/ridTransactionAdmin/toggle" plugin="metridoc-rid"/>
        <g:render template="/ridTransactionAdmin/tabs" plugin="metridoc-rid"/>
        <g:render template="/ridTransactionAdmin/modal" plugin="metridocRid"
                  model="[title: entityName + ' Create/Edit']"/>

        <div id="list-ridUserGoal" class="content scaffold-list" role="main">
            <h1>
                <g:message code="default.list.label" args="[entityName]"/>
                <a data-tooltip="Creating" href="create?dummy=${org.apache.commons.lang.math.RandomUtils.nextInt()}"
                   data-target="#myModal" data-toggle="modal">
                    <i title="Create Product Connected" class="icon-plus-sign-alt"></i>
                </a>
            </h1>

            <g:hasErrors bean="${ridUserGoalError}">
                <div class="errors">
                    <g:renderErrors bean="${ridUserGoalError}" as="list"/>
                </div>
            </g:hasErrors>

            <table class="table table-striped table-hover">
                <thead>
                <tr>

                    <g:sortableColumn property="name"
                                      title="${message(code: 'ridUserGoal.name.label', default: 'Name')}"/>

                    <g:sortableColumn property="inForm"
                                      title="${message(code: 'ridUserGoal.inForm.label', default: 'In Form')}"/>

                    <g:sortableColumn property="ridLibraryUnit"
                                      title="${message(code: 'ridUserGoal.ridLibraryUnit.label', default: 'Library Unit')}"/>

                    <th>Number of RidTransaction</th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${ridUserGoalInstanceList}" status="i" var="ridUserGoalInstance">
                    <tr>

                        <td>
                            <a data-toggle="modal"
                               href="edit/${ridUserGoalInstance.id}?dummy=${org.apache.commons.lang.math.RandomUtils.nextInt()}"
                               data-target="#myModal">
                                ${fieldValue(bean: ridUserGoalInstance, field: "name")}
                            </a>
                        </td>

                        <% def choices = ['NO', 'YES, and no indication needed', 'YES, and indication required'] %>
                        <td>${choices.get(ridUserGoalInstance?.inForm)}</td>

                        <td>${fieldValue(bean: ridUserGoalInstance, field: "ridLibraryUnit")}</td>

                        <td>${ridUserGoalInstance?.ridTransaction?.size()}</td>
                    </tr>
                </g:each>
                </tbody>
            </table>
            <g:if test="${ridUserGoalInstanceTotal > 10}">
                <div class="pagination">
                    <g:paginate total="${ridUserGoalInstanceTotal}"/>
                </div>
            </g:if>
        </div>
    </div>
</md:report>
