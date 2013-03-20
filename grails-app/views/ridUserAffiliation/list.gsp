<%@ page import="metridoc.rid.RidUserAffiliation" %>
<g:set var="entityName" value="${message(code: 'ridUserAffiliation.label', default: 'RidUserAffiliation')}"/>

<md:report>
    <!--[if !IE]><!-->
    <r:external dir="css" file="floating_tables_for_admin_1.css" plugin="metridoc-rid"/>
    <!--<![endif]-->

    <div class="md-application-content">
        <g:render template="/ridTransactionAdmin/tabs" plugin="metridoc-rid"/>
        <g:render template="/ridTransactionAdmin/modal" plugin="metridocRid" model="[title: entityName]"/>

        <div id="list-ridUserAffiliation" class="content scaffold-list" role="main">
            <h1>
                <g:message code="default.list.label" args="[entityName]"/>
                <a data-tooltip="Creating" href="create" data-target="#myModal" data-toggle="modal">
                    <i title="Create User Affiliation" class="icon-plus-sign-alt"></i>
                </a>
            </h1>

            <g:hasErrors bean="${ridUserAffiliationError}">
                <div class="errors">
                    <g:renderErrors bean="${ridUserAffiliationError}" as="list"/>
                </div>
            </g:hasErrors>

            <table class="table table-striped table-hover">
                <thead>
                <tr>

                    <g:sortableColumn property="name"
                                      title="${message(code: 'ridUserAffiliation.name.label', default: 'Name')}"/>

                    <g:sortableColumn property="inForm"
                                      title="${message(code: 'ridUserAffiliation.inForm.label', default: 'In Form')}"/>

                    <th>Number of RidTransaction</th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${ridUserAffiliationInstanceList}" status="i" var="ridUserAffiliationInstance">
                    <tr>

                        <td>
                            <a data-toggle="modal" href="edit/${ridUserAffiliationInstance.id}" data-target="#myModal">
                                ${fieldValue(bean: ridUserAffiliationInstance, field: "name")}
                            </a>
                        </td>

                        <% def choices = ['NO', 'YES, and no indication needed', 'YES, and indication required'] %>
                        <td>${choices.get(ridUserAffiliationInstance?.inForm)}</td>

                        <td>${ridUserAffiliationInstance?.ridTransaction?.size()}</td>
                    </tr>
                </g:each>
                </tbody>
            </table>
            <g:if test="${ridUserAffiliationInstanceTotal > 10}">
                <div class="pagination">
                    <g:paginate total="${ridUserAffiliationInstanceTotal}"/>
                </div>
            </g:if>
        </div>
    </div>
</md:report>
