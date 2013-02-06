<ul class="nav nav-tabs">
    <g:render
            template="/ridTransaction/tabLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridCourseSponsor',
                    linkAction: 'list',
                    linkText: 'Course Sponsor',
                    ]"/>
    <g:render
            template="/ridTransaction/tabLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridCustomer',
                    linkAction: 'list',
                    linkText: 'Customer',
            ]"/>
    <g:render
            template="/ridTransaction/tabLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridDepartmentalAffiliation',
                    linkAction: 'list',
                    linkText: 'Department Affiliation',
            ]"/>
    <g:render
            template="/ridTransaction/tabLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridEntityAffiliation',
                    linkAction: 'list',
                    linkText: 'Entity Affiliation',
            ]"/>
    <g:render
            template="/ridTransaction/tabLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridModeOfConsutlation',
                    linkAction: 'list',
                    linkText: 'Consutlation Mode',
            ]"/>
    <g:render
            template="/ridTransaction/tabLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridProductConnected',
                    linkAction: 'list',
                    linkText: 'Product Connected',
            ]"/>
    <g:render
            template="/ridTransaction/tabLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridServiceProvided',
                    linkAction: 'list',
                    linkText: 'Service Provided',
            ]"/>
</ul>