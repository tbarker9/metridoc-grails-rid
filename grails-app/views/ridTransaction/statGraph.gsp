<%@ page import="org.apache.shiro.SecurityUtils; metridoc.rid.RidConsTransaction; metridoc.rid.RidInsTransaction" %>

<g:if test="${session.transType == "consultation"}">
    <g:set var="entityName" value="${message(code: 'ridTransaction.label', default: 'RidConsTransaction')}"/>
</g:if>
<g:else>
    <g:set var="entityName" value="${message(code: 'ridTransaction.label', default: 'RidInsTransaction')}"/>
</g:else>
<md:report>
    <r:external dir="js" file="RidStatistics.js" plugin="metridoc-rid"/>
    <r:external dir="css" file="pagination.css" plugin="metridoc-rid"/>
    <r:external dir="css" file="table.css" plugin="metridoc-rid"/>
    <r:external dir="d3.v3" file="d3.v3.js" plugin="metridoc-rid"/>

    <!--[if !IE]><!-->
    <r:external dir="css" file="floating_tables_for_admin_5.css" plugin="metridoc-rid"/>
    <!--<![endif]-->


    <div class="md-application-content">

    <tmpl:toggle/>
    <tmpl:tabs/>
    <g:if test="${session.transType == "consultation"}">

        Not yet Implemented
    </g:if>
    <g:else>Not Yet Implemented</g:else>
</md:report>

