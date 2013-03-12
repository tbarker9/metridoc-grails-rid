
<%@ page import="metridoc.rid.RidTransaction" %>
<g:set var="entityName" value="${message(code: 'ridTransaction.label', default: 'RidTransaction')}" />


        <div id="list-ridTransaction" class="content scaffold-list" role="main">
            <h1 style="padding-bottom: 10px">Choose a template from the list</h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table class="table table-striped table-hover">
                <thead>
                <tr>

                    <g:sortableColumn property="user" title="${message(code: 'ridTransaction.user.label', default: 'User')}" />

                    <g:sortableColumn property="departmentalAffilication" title="${message(code: 'ridTransaction.departmentalAffilication.label', default: 'Department Affilication')}" />

                    <g:sortableColumn property="ridReportType" title="${message(code: 'ridTransaction.ridReportType.label', default: 'Report Type')}" />

                </tr>
                </thead>
                <tbody>
                <g:each in="${ridTransactionInstanceList}" status="i" var="ridTransactionInstance">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}"
                        onclick="window.location='create/${ridTransactionInstance.id}'"
                        style="cursor: pointer;">
                        <td>
                            ${fieldValue(bean: ridTransactionInstance, field: "user")}
                        </td>

                        <td>${fieldValue(bean: ridTransactionInstance, field: "departmentalAffilication")}</td>

                        <td>${fieldValue(bean: ridTransactionInstance, field: "ridReportType")}</td>

                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>

