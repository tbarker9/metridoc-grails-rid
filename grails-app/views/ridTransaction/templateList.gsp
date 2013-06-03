<g:if test="${session.transType == "consultation"}">
    <%@ page import="metridoc.rid.RidConsTransaction" %>
</g:if>
<g:else>
    <%@ page import="metridoc.rid.RidInsTransaction" %>
</g:else>
<g:if test="${session.transType == "consultation"}">
    <g:set var="entityName" value="${message(code: 'ridTransaction.label', default: 'RidConsTransaction')}"/>
</g:if>
<g:else>
    <g:set var="entityName" value="${message(code: 'ridTransaction.label', default: 'RidInsTransaction')}"/>
</g:else>

<div id="list-ridTransaction" class="content scaffold-list" role="main">
    <r:external dir="css" file="table.css" plugin="metridoc-rid"/>
    <!--[if !IE]><!-->
    %{--<r:external dir="css" file="floating_tables_for_admin_2.css" />--}%
    <!--<![endif]-->

    <h1 style="padding-bottom: 10px">Choose a template from the list</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table class="table table-striped table-hover">
        <thead>
        <tr>

            <g:sortableColumn property="rank" title="${message(code: 'ridTransaction.rank.label', default: 'Rank')}"/>
            <g:sortableColumn property="department"
                              title="${message(code: 'ridTransaction.department.label', default: 'Department')}"/>
            <g:sortableColumn property="ridLibraryUnit"
                              title="${message(code: 'ridTransaction.ridLibraryUnit.label', default: 'Library Unit')}"/>
        </tr>
        </thead>
        <tbody>
        <g:each in="${ridTransactionInstanceList}" status="i" var="ridTransactionInstance">
            <tr onclick="window.location = 'create?tmp=${ridTransactionInstance.id}'" style="cursor: pointer;">
                <td>${fieldValue(bean: ridTransactionInstance, field: "rank")}</td>
                <td>${fieldValue(bean: ridTransactionInstance, field: "department")}</td>
                <td>${fieldValue(bean: ridTransactionInstance, field: "ridLibraryUnit")}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>

