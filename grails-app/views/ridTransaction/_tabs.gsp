<ul class="nav nav-tabs">
    <g:render
            template="/ridTransaction/tabLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridTransaction',
                    linkAction: 'create',
                    linkText: 'Create RidTransaction',
                    icon: 'icon-plus-sign-alt']"/>
    <g:render
            template="/ridTransaction/tabLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridTransaction',
                    linkAction: 'list',
                    linkText: 'RidTransaction List',
                    icon: 'icon-list-alt']"/>
    <g:render
            template="/ridTransaction/tabLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridTransaction',
                    linkAction: 'search',
                    linkText: 'Search RidTransaction',
                    icon: 'icon-search']"/>
</ul>


