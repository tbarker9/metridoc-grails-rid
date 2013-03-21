<%@ page import="metridoc.rid.RidUser" %>
<g:set var="entityName" value="${message(code: 'ridUser.label', default: 'RidUser')}"/>

<md:report>
    <r:external dir="css" file="pagination.css" plugin="metridoc-rid"/>
    <r:external dir="css" file="table.css" plugin="metridoc-rid"/>
    <!--[if !IE]><!-->
    <r:external dir="css" file="floating_tables_for_admin_1.css" plugin="metridoc-rid"/>
    <!--<![endif]-->

    <div class="md-application-content">
        <g:render template="/ridTransactionAdmin/tabs" plugin="metridoc-rid"/>
        <g:render template="/ridTransactionAdmin/modal" plugin="metridocRid" model="[title: entityName]"/>

        <div id="list-ridUser" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]"/>

                <a data-tooltip="Creating" href="create" data-target="#myModal" data-toggle="modal">
                    <i title="Create User" class="icon-plus-sign-alt"></i>
                </a>
            </h1>

            <g:hasErrors bean="${ridUserError}">
                <div class="errors">
                    <g:renderErrors bean="${ridUserError}" as="list"/>
                </div>
            </g:hasErrors>

            <table class="table table-striped table-hover">
                <thead>
                <tr>

                    <g:sortableColumn property="name" title="${message(code: 'ridUser.name.label', default: 'Name')}"/>

                    <g:sortableColumn property="inForm"
                                      title="${message(code: 'ridUser.inForm.label', default: 'In Form')}"/>

                    <th>Number of RidTransaction</th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${ridUserInstanceList}" status="i" var="ridUserInstance">
                    <tr>

                        <td>
                            <a data-toggle="modal" href="edit/${ridUserInstance.id}" data-target="#myModal">
                                ${fieldValue(bean: ridUserInstance, field: "name")}
                            </a>
                        </td>

                        <% def choices = ['NO', 'YES, and no indication needed', 'YES, and indication required'] %>
                        <td>${choices.get(ridUserInstance?.inForm)}</td>

                        <td>${ridUserInstance?.ridTransaction?.size()}</td>
                    </tr>
                </g:each>
                </tbody>
            </table>
            <g:if test="${ridUserInstanceTotal > 10}">
                <div class="pagination">
                    <g:paginate total="${ridUserInstanceTotal}"/>
                </div>
            </g:if>
        </div>
    </div>
</md:report>
