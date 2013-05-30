<ul class="nav nav-tabs">
    <g:render
            template="/ridTransaction/tabLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridLibraryUnit',
                    linkAction: 'list',
                    linkText: 'Library Unit',
            ]"/>
    <g:if test="${session.transType == "consultation"}">
        <g:render
                template="/ridTransaction/tabLabel"
                plugin="metridocRid"
                model="[controllerName: controllerName,
                        actionName: actionName,
                        linkController: 'ridCourseSponsor',
                        linkAction: 'list',
                        linkText: 'Course Sponsor',
                ]"/>
    </g:if>
    <g:render
            template="/ridTransaction/tabLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridRank',
                    linkAction: 'list',
                    linkText: 'Rank',
            ]"/>
    <g:render
            template="/ridTransaction/tabLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridDepartment',
                    linkAction: 'list',
                    linkText: 'Department',
            ]"/>
    <g:render
            template="/ridTransaction/tabLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridSchool',
                    linkAction: 'list',
                    linkText: 'School',
            ]"/>
    <g:if test="${session.transType == "consultation"}">
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
    </g:if>
</ul>