<g:if test="${session.transType == linkAction}">
    <li class="active">
        <g:link controller="${linkController}" action="${linkAction}"><i class="${icon}"></i> ${linkText}</g:link>
    </li>
</g:if>
<g:else>
    <li>
        <g:link controller="${linkController}" action="${linkAction}"><i class="${icon}"></i> ${linkText}</g:link>
    </li>
</g:else>