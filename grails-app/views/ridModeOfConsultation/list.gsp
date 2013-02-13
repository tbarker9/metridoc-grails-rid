<%@ page import="metridoc.rid.RidModeOfConsultation" %>
<g:set var="entityName" value="${message(code: 'ridModeOfConsultation.label', default: 'RidModeOfConsultation')}" />

<md:report>
    <!--[if !IE]><!-->
    <r:external dir="css" file="floating_tables_for_admin.css" plugin="metridoc-rid"/>
    <!--<![endif]-->

    <div style="font-size: 12px">
        <g:render template="/ridTransactionAdmin/tabs" plugin="metridoc-rid"/>
    </div>

    <div class="md-application-content">
        <g:render template="/ridCourseSponsor/modal" plugin="metridocRid" model="[title: entityName]"/>

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
                        
                        <g:sortableColumn property="mode" title="${message(code: 'ridModeOfConsultation.mode.label', default: 'Mode')}" />

                        <g:sortableColumn property="ridGroupType" title="${message(code: 'ridModeOfConsultation.ridGroupType.label', default: 'Group Type')}" />

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

                            <td>${fieldValue(bean: ridModeOfConsultationInstance, field: "ridGroupType")}</td>

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
