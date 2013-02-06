<%@ page import="metridoc.rid.RidEntityAffiliation" %>
<g:set var="entityName" value="${message(code: 'ridEntityAffiliation.label', default: 'RidEntityAffiliation')}" />

<md:report>
    <!--[if !IE]><!-->
    <r:external dir="css" file="floating_tables_for_admin.css" plugin="metridoc-rid"/>
    <!--<![endif]-->

    <div style="font-size: 12px">
        <g:render template="/ridTransactionAdmin/tabs" plugin="metridoc-rid"/>
    </div>

    <div class="md-application-content">
        <g:render template="/ridCourseSponsor/modal" plugin="metridocRid" model="[title: entityName]"/>

            <div id="list-ridEntityAffiliation" class="content scaffold-list" role="main">
                <h1>
                    <g:message code="default.list.label" args="[entityName]" />
                    <a data-tooltip="Creating" href="create" data-target="#myModal" data-toggle="modal">
                        <i title="Create Entity Affiliation" class="icon-plus-sign-alt"></i>
                    </a>
                </h1>

                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        
                        <g:sortableColumn property="name" title="${message(code: 'ridEntityAffiliation.name.label', default: 'Name')}" />
                        
                        <g:sortableColumn property="inForm" title="${message(code: 'ridEntityAffiliation.inForm.label', default: 'In Form')}" />

                        <th>Number of RidTransaction</th>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${ridEntityAffiliationInstanceList}" status="i" var="ridEntityAffiliationInstance">
                        <tr>
                            
                            <td>
                                <a data-toggle="modal" href="edit/${ridEntityAffiliationInstance.id}" data-target="#myModal">
                                    ${fieldValue(bean: ridEntityAffiliationInstance, field: "name")}
                                </a>
                            </td>
                            
                            <td>${fieldValue(bean: ridEntityAffiliationInstance, field: "inForm")}</td>

                            <td>${ridEntityAffiliationInstance?.ridTransaction?.size()}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
                <g:if test="${ridEntityAffiliationInstanceTotal > 10}">
                    <div class="pagination">
                        <g:paginate total="${ridEntityAffiliationInstanceTotal}" />
                    </div>
                </g:if>
            </div>
    </div>
</md:report>
