<%@ page import="metridoc.rid.RidModeOfConsutlation" %>
<g:set var="entityName" value="${message(code: 'ridModeOfConsutlation.label', default: 'RidModeOfConsutlation')}" />

<md:report>
    <!--[if !IE]><!-->
    <r:external dir="css" file="floating_tables_for_admin.css" plugin="metridoc-rid"/>
    <!--<![endif]-->

    <div style="font-size: 12px">
        <g:render template="/ridTransactionAdmin/tabs" plugin="metridoc-rid"/>
    </div>

    <div class="md-application-content">
        <g:render template="/ridCourseSponsor/modal" plugin="metridocRid" model="[title: entityName]"/>

            <div id="list-ridModeOfConsutlation" class="content scaffold-list" role="main">
                <h1>
                    <g:message code="default.list.label" args="[entityName]" />
                    <a data-tooltip="Creating" href="create" data-target="#myModal" data-toggle="modal">
                        <i title="Create Mode of Consutlation" class="icon-plus-sign-alt"></i>
                    </a>
                </h1>

                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        
                        <g:sortableColumn property="mode" title="${message(code: 'ridModeOfConsutlation.mode.label', default: 'Mode')}" />

                        <th>Number of RidTransaction</th>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${ridModeOfConsutlationInstanceList}" status="i" var="ridModeOfConsutlationInstance">
                        <tr>
                            
                            <td>
                                <a data-toggle="modal" href="edit/${ridModeOfConsutlationInstance.id}" data-target="#myModal">
                                    ${fieldValue(bean: ridModeOfConsutlationInstance, field: "name")}
                                </a>
                            </td>

                            <td>${ridModeOfConsutlationInstance?.ridTransaction?.size()}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
                <g:if test="${ridModeOfConsutlationInstanceTotal > 10}">
                    <div class="pagination">
                        <g:paginate total="${ridModeOfConsutlationInstanceTotal}" />
                    </div>
                </g:if>
            </div>
    </div>
</md:report>
