<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="RidTransactionAdmin"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
    %{--<link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />--}%
</head>

<body>
<a href="#list-ridTransactionAdmin" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                          default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
    </ul>
</div>

<div id="list-ridTransactionAdmin" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <div id="controllerList" style="padding-left: 150px;">
        <ul>
            <g:each var="c" in="${grailsApplication.controllerClasses}">
                <g:if test="${c.shortName.contains('Admin') && !c.shortName.contains('RidTransaction')}">
                    <li class="controller" style="padding-top: 10px;">
                        <g:link controller="${c.logicalPropertyName}">
                            ${c.shortName}
                        </g:link>
                    </li>
                </g:if>
            </g:each>
        </ul>
    </div>

    <div class="pagination">
        %{--<g:paginate total="${ridTransactionAdminInstanceTotal}"/>--}%
    </div>
</div>
</body>
</html>
