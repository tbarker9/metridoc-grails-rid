<g:if test="${linkController == controllerName && linkAction == actionName}">
    <li class="active">
        <g:link controller="${linkController}" action="${linkAction}"><i class="${icon}"></i> ${linkText}</g:link>
    </li>
</g:if>
<g:elseif test="${linkAction.equals("stats")}">
    <style type="text/css">
    .nav-tabs .stat a,
    .nav-tabs .stat a:hover {
        font-size: 15px;
    }

    </style>
    <li class="stat">
        <g:link controller="${linkController}" action="${linkAction}"><i class="${icon}"></i> ${linkText}</g:link>
    </li>

</g:elseif>
<g:else>
    <li>
        <g:link controller="${linkController}" action="${linkAction}"><i class="${icon}"></i> ${linkText}</g:link>
    </li>
</g:else>