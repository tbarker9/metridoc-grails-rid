<%@ page import="metridoc.rid.RidDepartmentalAffiliation" %>
<g:set var="entityName" value="${message(code: 'ridDepartmentalAffiliation.label', default: 'RidDepartmentalAffiliation')}" />

<md:report>
    <!--[if !IE]><!-->
    <r:external dir="css" file="floating_tables_for_admin.css" plugin="metridoc-rid"/>
    <!--<![endif]-->

    <div style="font-size: 12px">
        <g:render template="/ridTransactionAdmin/tabs" plugin="metridoc-rid"/>
    </div>

    <div class="md-application-content">
        <g:render template="/ridCourseSponsor/modal" plugin="metridocRid" model="[title: entityName]"/>

            <div id="list-ridDepartmentalAffiliation" class="content scaffold-list" role="main">
                <h1>
                    <g:message code="default.list.label" args="[entityName]" />
                    <a data-tooltip="Creating" href="create" data-target="#myModal" data-toggle="modal">
                        <i title="Create Departmental Affiliation" class="icon-plus-sign-alt"></i>
                    </a>
                </h1>

                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        
                        <g:sortableColumn property="name" title="${message(code: 'ridDepartmentalAffiliation.name.label', default: 'Name')}" />
                        <th>Number of RidTransaction</th>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${ridDepartmentalAffiliationInstanceList}" status="i" var="ridDepartmentalAffiliationInstance">
                        <tr>
                            
                            <td>
                                <a data-toggle="modal" href="edit/${ridDepartmentalAffiliationInstance.id}" data-target="#myModal">
                                    ${fieldValue(bean: ridDepartmentalAffiliationInstance, field: "name")}
                                </a>
                            </td>

                            <td>${ridDepartmentalAffiliationInstance?.ridTransaction?.size()}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
                <g:if test="${ridDepartmentalAffiliationInstanceTotal > 10}">
                    <div class="pagination">
                        <g:paginate total="${ridDepartmentalAffiliationInstanceTotal}" />
                    </div>
                </g:if>
            </div>
    </div>
</md:report>
