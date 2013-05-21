<%@ page import="metridoc.rid.RidServiceProvided" %>
<g:set var="entityName" value="${message(code: 'ridServiceProvided.label', default: 'RidServiceProvided')}"/>

<md:report>
    <r:external dir="css" file="pagination.css" plugin="metridoc-rid"/>
    <r:external dir="css" file="table.css" plugin="metridoc-rid"/>
    <!--[if !IE]><!-->
    <r:external dir="css" file="floating_tables_for_admin_4.css" plugin="metridoc-rid"/>
    <!--<![endif]-->

    <div class="md-application-content">
        <g:render template="/ridTransactionAdmin/tabs" plugin="metridoc-rid"/>
        <g:render template="/ridTransactionAdmin/modal" plugin="metridocRid"
                  model="[title: entityName + ' Create/Edit']"/>

        <div id="list-ridServiceProvided" class="content scaffold-list" role="main">
            <h1>
                <g:message code="default.list.label" args="[entityName]"/>
                <a data-tooltip="Creating" href="create?dummy=${org.apache.commons.lang.math.RandomUtils.nextInt()}"
                   data-target="#myModal" data-toggle="modal">
                    <i title="Create Service Provided" class="icon-plus-sign-alt"></i>
                </a>
            </h1>

            <g:hasErrors bean="${ridServiceProvidedError}">
                <div class="errors">
                    <g:renderErrors bean="${ridServiceProvidedError}" as="list"/>
                </div>
            </g:hasErrors>

            <table class="table table-striped table-hover">
                <thead>
                <tr>

                    <g:sortableColumn property="name"
                                      title="${message(code: 'ridServiceProvided.name.label', default: 'Name')}"/>

                    <g:sortableColumn property="inForm"
                                      title="${message(code: 'ridServiceProvided.inForm.label', default: 'In Form')}"/>

                    <g:sortableColumn property="ridLibraryUnit"
                                      title="${message(code: 'ridServiceProvided.ridLibraryUnit.label', default: 'Library Unit')}"/>

                    <th>Number of RidTransaction</th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${ridServiceProvidedInstanceList}" status="i" var="ridServiceProvidedInstance">
                    <tr>

                        <td>
                            <a data-toggle="modal"
                               href="edit/${ridServiceProvidedInstance.id}?dummy=${org.apache.commons.lang.math.RandomUtils.nextInt()}"
                               data-target="#myModal">
                                ${fieldValue(bean: ridServiceProvidedInstance, field: "name")}
                            </a>
                        </td>

                        <% def choices = ['NO', 'YES, and no indication needed', 'YES, and indication required'] %>
                        <td>${choices.get(ridServiceProvidedInstance?.inForm)}</td>

                        <td>${fieldValue(bean: ridServiceProvidedInstance, field: "ridLibraryUnit")}</td>

                        <td>${ridServiceProvidedInstance?.ridTransaction?.size()}</td>
                    </tr>
                </g:each>
                </tbody>
            </table>
            <g:if test="${ridServiceProvidedInstanceTotal > 10}">
                <div class="pagination">
                    <g:paginate total="${ridServiceProvidedInstanceTotal}"/>
                </div>
            </g:if>
        </div>
    </div>
</md:report>
