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
                    linkController: 'ridUser',
                    linkAction: 'list',
                    linkText: 'User',
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
                    linkController: 'ridUserAffiliation',
                    linkAction: 'list',
                    linkText: 'User Affiliation',
            ]"/>
    <g:render
            template="/ridTransaction/tabLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridModeOfConsultation',
                    linkAction: 'list',
                    linkText: 'Consultation Mode',
            ]"/>
    <g:render
            template="/ridTransaction/tabLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridUserGoal',
                    linkAction: 'list',
                    linkText: 'User Goal',
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