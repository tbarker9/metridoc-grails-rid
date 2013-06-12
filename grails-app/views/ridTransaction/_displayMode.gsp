<ul class="nav nav-pills">
    <g:if test="${session.display == "dropdown"}">
        <g:render
                template="displayLabel"
                plugin="metridocRid"
                model="[controllerName: controllerName,
                        actionName: actionName,
                        linkController: 'ridTransaction',
                        linkAction: 'pills',
                        linkText: '',
                        icon: 'icon-circle']"/>
        <g:render
                template="displayLabel"
                plugin="metridocRid"
                model="[controllerName: controllerName,
                        actionName: actionName,
                        linkController: 'ridTransaction',
                        linkAction: 'tabs',
                        linkText: '',
                        icon: 'icon-folder-close-alt']"/>
    </g:if>
    <g:elseif test="${session.display == "pills"}">
        <g:render
                template="displayLabel"
                plugin="metridocRid"
                model="[controllerName: controllerName,
                        actionName: actionName,
                        linkController: 'ridTransaction',
                        linkAction: 'dropdown',
                        linkText: '',
                        icon: 'icon-chevron-down']"/>
        <g:render
                template="displayLabel"
                plugin="metridocRid"
                model="[controllerName: controllerName,
                        actionName: actionName,
                        linkController: 'ridTransaction',
                        linkAction: 'tabs',
                        linkText: '',
                        icon: 'icon-folder-close-alt']"/>

    </g:elseif>

    <g:else>
        <g:render
                template="displayLabel"
                plugin="metridocRid"
                model="[controllerName: controllerName,
                        actionName: actionName,
                        linkController: 'ridTransaction',
                        linkAction: 'dropdown',
                        linkText: '',
                        icon: 'icon-chevron-down']"/>
        <g:render
                template="displayLabel"
                plugin="metridocRid"
                model="[controllerName: controllerName,
                        actionName: actionName,
                        linkController: 'ridTransaction',
                        linkAction: 'pills',
                        linkText: '',
                        icon: 'icon-circle']"/>

    </g:else>
</ul>
