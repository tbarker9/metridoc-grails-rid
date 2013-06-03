<ul class="nav nav-tabs">
    <g:render
            template="/ridTransaction/tabLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridAdminLibraryUnit',
                    linkAction: 'list',
                    linkText: 'Library Unit',
            ]"/>
    <g:if test="${session.transType == "consultation"}">
        <g:render
                template="/ridTransaction/tabLabel"
                plugin="metridocRid"
                model="[controllerName: controllerName,
                        actionName: actionName,
                        linkController: 'ridAdminCourseSponsor',
                        linkAction: 'list',
                        linkText: 'Course Sponsor',
                ]"/>
    </g:if>
    <g:render
            template="/ridTransaction/tabLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridAdminRank',
                    linkAction: 'list',
                    linkText: 'Rank',
            ]"/>
    <g:render
            template="/ridTransaction/tabLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridAdminDepartment',
                    linkAction: 'list',
                    linkText: 'Department',
            ]"/>
    <g:render
            template="/ridTransaction/tabLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridAdminSchool',
                    linkAction: 'list',
                    linkText: 'School',
            ]"/>
    <g:if test="${session.transType == "consultation"}">
        <g:render
                template="/ridTransaction/tabLabel"
                plugin="metridocRid"
                model="[controllerName: controllerName,
                        actionName: actionName,
                        linkController: 'ridAdminModeOfConsultation',
                        linkAction: 'list',
                        linkText: 'Consultation Mode',
                ]"/>

        <g:render
                template="/ridTransaction/tabLabel"
                plugin="metridocRid"
                model="[controllerName: controllerName,
                        actionName: actionName,
                        linkController: 'ridAdminUserGoal',
                        linkAction: 'list',
                        linkText: 'User Goal',
                ]"/>
        <g:render
                template="/ridTransaction/tabLabel"
                plugin="metridocRid"
                model="[controllerName: controllerName,
                        actionName: actionName,
                        linkController: 'ridAdminServiceProvided',
                        linkAction: 'list',
                        linkText: 'Service Provided',
                ]"/>
    </g:if>
</ul>