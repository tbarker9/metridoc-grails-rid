<%@ page import="metridoc.rid.RidCustomer" %>
<g:set var="entityName" value="${message(code: 'ridCustomer.label', default: 'RidCustomer')}" />

<md:report>
    <!--[if !IE]><!-->
    <r:external dir="css" file="floating_tables_for_admin.css" plugin="metridoc-rid"/>
    <!--<![endif]-->

    <div style="font-size: 12px">
        <g:render template="/ridTransactionAdmin/tabs" plugin="metridoc-rid"/>
    </div>

    <div class="md-application-content">
        <g:render template="/ridCourseSponsor/modal" plugin="metridocRid" model="[title: entityName]"/>

            <div id="list-ridCustomer" class="content scaffold-list" role="main">
                <h1><g:message code="default.list.label" args="[entityName]" />

                    <a data-tooltip="Creating" href="create" data-target="#myModal" data-toggle="modal">
                        <i title="Create Customer" class="icon-plus-sign-alt"></i>
                    </a>
                </h1>

                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        
                        <g:sortableColumn property="name" title="${message(code: 'ridCustomer.name.label', default: 'Name')}" />
                        
                        <g:sortableColumn property="inForm" title="${message(code: 'ridCustomer.inForm.label', default: 'In Form')}" />

                        <th>Number of RidTransaction</th>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${ridCustomerInstanceList}" status="i" var="ridCustomerInstance">
                        <tr>
                            
                            <td>
                                <a data-toggle="modal" href="edit/${ridCustomerInstance.id}" data-target="#myModal">
                                    ${fieldValue(bean: ridCustomerInstance, field: "name")}
                                </a>
                            </td>
                            
                            <td>${fieldValue(bean: ridCustomerInstance, field: "inForm")}</td>

                            <td>${ridCustomerInstance?.ridTransaction?.size()}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
                <g:if test="${ridCustomerInstanceTotal > 10}">
                    <div class="pagination">
                        <g:paginate total="${ridCustomerInstanceTotal}" />
                    </div>
                </g:if>
            </div>
    </div>
</md:report>
