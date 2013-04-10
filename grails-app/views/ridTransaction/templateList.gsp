<%@ page import="metridoc.rid.RidTransaction" %>
<g:set var="entityName" value="${message(code: 'ridTransaction.label', default: 'RidTransaction')}"/>


<div id="list-ridTransaction" class="content scaffold-list" role="main">
    <h1 style="padding-bottom: 10px">Choose a template from the list</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table class="table table-striped table-hover">
        <thead>
        <tr>

            <g:sortableColumn property="rank" title="${message(code: 'ridTransaction.rank.label', default: 'Rank')}"/>

            <g:sortableColumn property="department"
                              title="${message(code: 'ridTransaction.department.label', default: 'Department Affilication')}"/>

            <g:sortableColumn property="ridLibraryUnit"
                              title="${message(code: 'ridTransaction.ridLibraryUnit.label', default: 'Library Unit')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${ridTransactionInstanceList}" status="i" var="ridTransactionInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}"
                onclick="window.location = 'create?tmp=${ridTransactionInstance.id}'"
                style="cursor: pointer;">
                <td>
                    ${fieldValue(bean: ridTransactionInstance, field: "rank")}
                </td>

                <td>${fieldValue(bean: ridTransactionInstance, field: "department")}</td>

                <td>${fieldValue(bean: ridTransactionInstance, field: "ridLibraryUnit")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>
</div>

