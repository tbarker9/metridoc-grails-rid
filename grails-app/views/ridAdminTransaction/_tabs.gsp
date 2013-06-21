<g:if test="${false}"><!--Dummy test for easy switching-->

    <style type="text/css">
    .nav-tabs .active a,
    .nav-tabs .active a:hover {
        font-size: 16px;
    }

    .nav-tabs  a,
    .nav-tabs  a:hover {
        font-size: 12px;
    }

    </style>
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

        <g:render
                template="/ridTransaction/tabLabel"
                plugin="metridocRid"
                model="[controllerName: controllerName,
                        actionName: actionName,
                        linkController: 'ridAdminTransaction',
                        linkAction: 'stats',
                        linkText: 'Statistics',
                        icon: 'icon-search']"/>
    </ul>
</g:if>
<g:else>
    <ul>
        <style type="text/css">
        .green a,
        .green a:hover {
            color: green
        }

        .nav li.dropdown > .dropdown-toggle .caret {
            color: green;
            border-top-color: green;
            border-bottom-color: green;
        }

        </style>
        <ul class="nav nav-tabs green">
            <li class="dropdown">
                <a class="dropdown-toggle green" data-toggle="dropdown">
                    <g:if test="${controllerName != "ridAdminTransaction"}">
                        ${controllerName.minus("ridAdmin")}
                    </g:if>
                    <g:else>
                        Edit/Add properties
                    </g:else>
                    <span class="caret"></span></a>

                <ul class="dropdown-menu">

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
            </li>

        </ul>
        <li class="nav nav-tabs">
            <g:render
                    template="/ridTransaction/tabLabel"
                    plugin="metridocRid"
                    model="[controllerName: controllerName,
                            actionName: actionName,
                            linkController: 'ridAdminTransaction',
                            linkAction: 'stats',
                            linkText: 'Statistics',
                            icon: 'icon-search']"/>
        <li class="nav nav-tabs">

        <li class="nav nav-tabs">
            <g:render
                    template="/ridTransaction/tabLabel"
                    plugin="metridocRid"
                    model="[controllerName: controllerName,
                            actionName: actionName,
                            linkController: 'ridAdminTransaction',
                            linkAction: 'statSearch',
                            linkText: 'Filtered Statistics',
                            icon: 'icon-search']"/>
        <li class="nav nav-tabs">
    </ul>
</g:else>
