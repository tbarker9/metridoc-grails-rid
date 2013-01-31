
<%@ page import="metridoc.rid.RidTransaction" %>
<g:set var="entityName" value="${message(code: 'ridTransaction.label', default: 'RidTransaction')}" />
%{--<!doctype html>--}%
%{--<html>--}%
	%{--<head>--}%
		%{--<meta name="layout" content="main">--}%
		%{--<g:set var="entityName" value="${message(code: 'ridTransaction.label', default: 'RidTransaction')}" />--}%
		%{--<title><g:message code="default.list.label" args="[entityName]" /></title>--}%
	%{--</head>--}%
	%{--<body>--}%

<md:report>
        <div class="md-application-content">
            <g:render template="/ridTransaction/tabs" plugin="metridocRid"/>

            <div id="list-ridTransaction" class="content scaffold-list" role="main">
                <h1><g:message code="default.list.label" args="[entityName]" /></h1>
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        
                        <g:sortableColumn property="customerQuestion" title="${message(code: 'ridTransaction.customerQuestion.label', default: 'Customer Question')}" />
                        
                        <g:sortableColumn property="staffPennkey" title="${message(code: 'ridTransaction.staffPennkey.label', default: 'Staff Pennkey')}" />
                        
                        <g:sortableColumn property="dateOfConsultation" title="${message(code: 'ridTransaction.dateOfConsultation.label', default: 'Date of Consultation')}" />
                        
                        <g:sortableColumn property="eventLength" title="${message(code: 'ridTransaction.eventLength.label', default: 'Event Length')}" />
                        
                        <g:sortableColumn property="notes" title="${message(code: 'ridTransaction.notes.label', default: 'Notes')}" />
                        
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${ridTransactionInstanceList}" status="i" var="ridTransactionInstance">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                            <%
                                customerQ = ridTransactionInstance.customerQuestion
                                if (customerQ!=null && customerQ.length() > 12)
                                    customerQ = customerQ.substring(0,12) + "..."
                            %>
                            <td><g:link action="show" id="${ridTransactionInstance.id}"
                                        title="${ridTransactionInstance.customerQuestion}">
                                    ${customerQ}
                                </g:link>
                            </td>
                            
                            <td>${fieldValue(bean: ridTransactionInstance, field: "staffPennkey")}</td>

                            <td><g:formatDate format="yyyy-MM-dd" date="${ridTransactionInstance?.dateOfConsultation}" /></td>
                            %{--<td>${fieldValue(bean: ridTransactionInstance, field: "dateOfConsultation")}</td>--}%
                            
                            <td>${fieldValue(bean: ridTransactionInstance, field: "eventLength")}</td>
                            
                            <td>${fieldValue(bean: ridTransactionInstance, field: "notes")}</td>
                            
                        </tr>
                    </g:each>
                    </tbody>
                </table>
                <g:if test="${ridTransactionInstanceTotal > 10}">
                    <div class="pagination">
                        <g:paginate total="${ridTransactionInstanceTotal}" params="${params}" />
                    </div>
                </g:if>
            </div>
        </div>
</md:report>
	%{--</body>--}%
%{--</html>--}%
