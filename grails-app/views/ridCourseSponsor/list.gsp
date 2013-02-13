<%@ page import="metridoc.rid.RidCourseSponsor" %>
<g:set var="entityName" value="${message(code: 'ridCourseSponsor.label', default: 'RidCourseSponsor')}" />

<md:report>
    <!--[if !IE]><!-->
    <r:external dir="css" file="floating_tables_for_admin.css" plugin="metridoc-rid"/>
    <!--<![endif]-->

    <div style="font-size: 12px">
        <g:render template="/ridTransactionAdmin/tabs" plugin="metridoc-rid"/>
    </div>

    <div class="md-application-content">
        <g:render template="/ridCourseSponsor/modal" plugin="metridocRid" model="[title: entityName]"/>

        <div id="list-ridCourseSponsor" class="content scaffold-list" role="main">

                <h1>
                    <g:message code="default.list.label" args="[entityName]" />

                    <a data-tooltip="Creating" href="create" data-target="#myModal" data-toggle="modal">
                        <i title="Create Course Sponsor" class="icon-plus-sign-alt"></i>
                    </a>
                </h1>

                %{--<g:if test="${flash.message}">--}%
                    %{--<div class="message" role="status">${flash.message}</div>--}%
                %{--</g:if>--}%

                <table class="table table-striped table-hover">
                    <thead>
                    <tr>

                        <g:sortableColumn property="name" width="35%" title="${message(code: 'ridCourseSponsor.name.label', default: 'Name')}" />

                        <g:sortableColumn property="inForm" width="35%" title="${message(code: 'ridCourseSponsor.inForm.label', default: 'In Form')}" />

                        <th>Number of RidTransaction</th>
                    </tr>
                    </thead>

                    <tbody>
                    <g:each in="${ridCourseSponsorInstanceList}" status="i" var="ridCourseSponsorInstance">
                        <tr>
                            <td>
                                <a data-toggle="modal" href="edit/${ridCourseSponsorInstance.id}" data-target="#myModal">
                                    ${fieldValue(bean: ridCourseSponsorInstance, field: "name")}
                                </a>
                            </td>

                            <% def choices = ['NO', 'YES, and no indication needed', 'YES, and indication required'] %>
                            <td>${choices.get(ridCourseSponsorInstance?.inForm)}</td>

                            <td>${ridCourseSponsorInstance?.ridTransaction?.size()}</td>
                            
                        </tr>
                    </g:each>
                    </tbody>
                </table>

                <g:if test="${ridCourseSponsorInstanceTotal > 10}">
                    <div class="pagination">
                        <g:paginate total="${ridCourseSponsorInstanceTotal}" />
                    </div>
                </g:if>
            </div>
    </div>
</md:report>
