<%@ page import="metridoc.rid.RidModeOfConsultation" %>
<g:set var="entityName" value="${message(code: 'ridModeOfConsultation.label', default: 'RidModeOfConsultation')}"/>

<md:report>
    <r:external dir="css" file="pagination.css" plugin="metridoc-rid"/>
    <r:external dir="css" file="table.css" plugin="metridoc-rid"/>
    <!--[if !IE]><!-->
    <r:external dir="css" file="floating_tables_for_admin_3.css" plugin="metridoc-rid"/>
    <!--<![endif]-->

    <div class="md-application-content">
        <g:render template="/ridTransactionAdmin/tabs" plugin="metridoc-rid"/>
        <g:render template="/ridTransactionAdmin/modal" plugin="metridocRid" model="[title: entityName]"/>

        <div id="list-ridModeOfConsultation" class="content scaffold-list" role="main">
            <h1>
                <g:message code="default.list.label" args="[entityName]"/>
                <a data-tooltip="Creating" href="create?dummy=${org.apache.commons.lang.math.RandomUtils.nextInt()}" data-target="#myModal" data-toggle="modal">
                    <i title="Create Mode of Consultation" class="icon-plus-sign-alt"></i>
                </a>
            </h1>

            <g:hasErrors bean="${ridModeOfConsultationError}">
                <div class="errors">
                    <g:renderErrors bean="${ridModeOfConsultationError}" as="list"/>
                </div>
            </g:hasErrors>

            <table class="table table-striped table-hover">
                <thead>
                <tr>

                    <g:sortableColumn property="name"
                                      title="${message(code: 'ridModeOfConsultation.name.label', default: 'Name')}"/>

                    <g:sortableColumn property="inForm"
                                      title="${message(code: 'ridModeOfConsultation.inForm.label', default: 'In Form')}"/>

                    <g:sortableColumn property="ridLibraryUnit"
                                      title="${message(code: 'ridModeOfConsultation.ridLibraryUnit.label', default: 'Library Unit')}"/>

                    <th>Number of RidTransaction</th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${ridModeOfConsultationInstanceList}" status="i" var="ridModeOfConsultationInstance">
                    <tr>

                        <td>
                            <a data-toggle="modal" href="edit/${ridModeOfConsultationInstance.id}?dummy=${org.apache.commons.lang.math.RandomUtils.nextInt()}"
                               data-target="#myModal">
                                ${fieldValue(bean: ridModeOfConsultationInstance, field: "name")}
                            </a>
                        </td>

                        <% def choices = ['NO', 'YES, and no indication needed', 'YES, and indication required'] %>
                        <td>${choices.get(ridModeOfConsultationInstance?.inForm)}</td>

                        <td>${fieldValue(bean: ridModeOfConsultationInstance, field: "ridLibraryUnit")}</td>

                        <td>${ridModeOfConsultationInstance?.ridTransaction?.size()}</td>
                    </tr>
                </g:each>
                </tbody>
            </table>
            <g:if test="${ridModeOfConsultationInstanceTotal > 10}">
                <div class="pagination">
                    <g:paginate total="${ridModeOfConsultationInstanceTotal}"/>
                </div>
            </g:if>
        </div>
    </div>
</md:report>
