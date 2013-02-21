<%@ page import="metridoc.rid.RidModeOfConsultation" %>
<g:set var="entityName" value="${message(code: 'ridModeOfConsultation.label', default: 'RidModeOfConsultation')}" />

<md:report>
    <!--[if !IE]><!-->
    <r:external dir="css" file="floating_tables_for_admin_3.css" plugin="metridoc-rid"/>
    <!--<![endif]-->

    <div class="md-application-content">
        <g:render template="/ridTransactionAdmin/tabs" plugin="metridoc-rid"/>
        <g:render template="/ridTransactionAdmin/modal" plugin="metridocRid" model="[title: entityName]"/>

            <div id="list-ridModeOfConsultation" class="content scaffold-list" role="main">
                <h1>
                    <g:message code="default.list.label" args="[entityName]" />
                    <a data-tooltip="Creating" href="create" data-target="#myModal" data-toggle="modal">
                        <i title="Create Mode of Consultation" class="icon-plus-sign-alt"></i>
                    </a>
                </h1>

                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        
                        <g:sortableColumn property="name" title="${message(code: 'ridModeOfConsultation.name.label', default: 'Name')}" />

                        <g:sortableColumn property="ridReportType" title="${message(code: 'ridModeOfConsultation.ridReportType.label', default: 'Report Type')}" />

                        <th>Number of RidTransaction</th>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${ridModeOfConsultationInstanceList}" status="i" var="ridModeOfConsultationInstance">
                        <tr>
                            
                            <td>
                                <a data-toggle="modal" href="edit/${ridModeOfConsultationInstance.id}" data-target="#myModal">
                                    ${fieldValue(bean: ridModeOfConsultationInstance, field: "name")}
                                </a>
                            </td>

                            <td>${fieldValue(bean: ridModeOfConsultationInstance, field: "ridReportType")}</td>

                            <td>${ridModeOfConsultationInstance?.ridTransaction?.size()}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
                <g:if test="${ridModeOfConsultationInstanceTotal > 10}">
                    <div class="pagination">
                        <g:paginate total="${ridModeOfConsultationInstanceTotal}" />
                    </div>
                </g:if>
            </div>
    </div>
</md:report>
