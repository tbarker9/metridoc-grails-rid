
<%@ page import="metridoc.rid.RidTransaction" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ridTransaction.label', default: 'RidTransaction')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
        <div class="md-application-content">
            <div class="nav" role="navigation">
                <ul>
                    <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                    <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                </ul>
            </div>
            <div id="list-ridTransaction" class="content scaffold-list" role="main">
                <h1><g:message code="default.list.label" args="[entityName]" /></h1>
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>
                <table>
                    <thead>
                    <tr>
                        
                        <g:sortableColumn property="customerQuestion" title="${message(code: 'ridTransaction.customerQuestion.label', default: 'Customer Question')}" />
                        
                        <g:sortableColumn property="interactTimes" title="${message(code: 'ridTransaction.interactTimes.label', default: 'Interact Times')}" />
                        
                        <g:sortableColumn property="followUpContact" title="${message(code: 'ridTransaction.followUpContact.label', default: 'Follow Up Contact')}" />
                        
                        <g:sortableColumn property="prepTime" title="${message(code: 'ridTransaction.prepTime.label', default: 'Prep Time')}" />
                        
                        <g:sortableColumn property="eventLength" title="${message(code: 'ridTransaction.eventLength.label', default: 'Event Length')}" />
                        
                        <g:sortableColumn property="notes" title="${message(code: 'ridTransaction.notes.label', default: 'Notes')}" />
                        
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${ridTransactionInstanceList}" status="i" var="ridTransactionInstance">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                            
                            <td><g:link action="show" id="${ridTransactionInstance.id}">${fieldValue(bean: ridTransactionInstance, field: "customerQuestion")}</g:link></td>
                            
                            <td>${fieldValue(bean: ridTransactionInstance, field: "interactTimes")}</td>
                            
                            <td>${fieldValue(bean: ridTransactionInstance, field: "followUpContact")}</td>
                            
                            <td>${fieldValue(bean: ridTransactionInstance, field: "prepTime")}</td>
                            
                            <td>${fieldValue(bean: ridTransactionInstance, field: "eventLength")}</td>
                            
                            <td>${fieldValue(bean: ridTransactionInstance, field: "notes")}</td>
                            
                        </tr>
                    </g:each>
                    </tbody>
                </table>
                <g:if test="${ridTransactionInstanceTotal > 10}">
                    <div class="pagination">
                        <g:paginate total="${ridTransactionInstanceTotal}" />
                    </div>
                </g:if>
            </div>
        </div>
	</body>
</html>
