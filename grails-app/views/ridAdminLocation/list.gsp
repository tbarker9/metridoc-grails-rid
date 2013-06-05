<%@ page import="metridoc.rid.RidLocation" %>
<g:set var="entityName" value="${message(code: 'ridLocation.label', default: 'RidLocation')}"/>

<md:report>
    <r:external dir="css" file="pagination.css" plugin="metridoc-rid"/>
    <r:external dir="css" file="table.css" plugin="metridoc-rid"/>
    <!--[if !IE]><!-->
    <r:external dir="css" file="floating_tables_for_admin_1.css" plugin="metridoc-rid"/>
    <!--<![endif]-->

    <div class="md-application-content">
        <g:render template="/ridAdminTransaction/toggle" plugin="metridoc-rid"/>

        <g:render template="/ridAdminTransaction/tabs" plugin="metridoc-rid"/>
        <g:render template="/ridAdminTransaction/modal" plugin="metridocRid"
                  model="[title: entityName + ' Create/Edit']"/>

        <div id="list-ridLocation" class="content scaffold-list" role="main">
            <h1>
                <g:message code="default.list.label" args="[entityName]"/>
                <a data-tooltip="Creating" href="create?dummy=${org.apache.commons.lang.math.RandomUtils.nextInt()}"
                   data-target="#myModal" data-toggle="modal">
                    <i title="Create User Affiliation" class="icon-plus-sign-alt"></i>
                </a>
            </h1>

            <g:hasErrors bean="${ridDomainClassError}">
                <div class="errors">
                    <g:renderErrors bean="${ridDomainClassError}" as="list"/>
                </div>
            </g:hasErrors>

            <table class="table table-striped table-hover">
                <thead>
                <tr>

                    <g:sortableColumn property="name"
                                      title="${message(code: 'ridLocation.name.label', default: 'Name')}"/>

                    <g:sortableColumn property="inForm"
                                      title="${message(code: 'ridLocation.inForm.label', default: 'In Form')}"/>

                    <th>Number of RidTransaction</th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${ridInstanceList}" status="i" var="ridInstance">
                    <tr>

                        <td>
                            <a data-toggle="modal"
                               href="edit/${ridInstance.id}?dummy=${org.apache.commons.lang.math.RandomUtils.nextInt()}"
                               data-target="#myModal">
                                ${fieldValue(bean: ridInstance, field: "name")}
                            </a>
                        </td>

                        <% def choices = ['NO', 'YES, and no indication needed', 'YES, and indication required'] %>
                        <td>${choices.get(ridInstance?.inForm)}</td>

                        <td>${ridInstance?.ridTransaction?.size()}</td>
                    </tr>
                </g:each>
                </tbody>
            </table>
            <g:if test="${ridInstanceTotal > 10}">
                <div class="pagination">
                    <g:paginate total="${ridInstanceTotal}"/>
                </div>
            </g:if>
        </div>
    </div>
</md:report>
