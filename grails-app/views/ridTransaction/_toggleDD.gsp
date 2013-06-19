<div class="btn-group pull-right">

    <g:if test="${session.transType == "consultation"}">
        <a class="btn" data-toggle="dropdown">Consultation
            <span class="caret"></span></a>
    </g:if>
    <g:else>
        <a class="btn" data-toggle="dropdown">Instructional
            <span class="caret"></span></a>
    </g:else>


    <ul class="dropdown-menu">

        <g:if test="${session.transType == "consultation"}">
            <g:render
                    template="toggleLabelDD"
                    plugin="metridocRid"
                    model="[controllerName: controllerName,
                            actionName: actionName,
                            linkController: 'ridTransaction',
                            linkAction: 'instructional',
                            linkText: 'Instructional']"/>
        </g:if>
        <g:else>
            <g:render
                    template="toggleLabelDD"
                    plugin="metridocRid"
                    model="[controllerName: controllerName,
                            actionName: actionName,
                            linkController: 'ridTransaction',
                            linkAction: 'consultation',
                            linkText: 'Consultation']"/>

        </g:else>
        <li class="divider"></li>
        <shiro:hasRole name="ROLE_ADMIN">
            <g:if test="${controllerName == "ridTransaction"}">
                <g:render
                        template="adminToggleLabel"
                        plugin="metridocRid"
                        model="[controllerName: controllerName,
                                actionName: actionName,
                                linkController: 'ridTransaction',
                                linkAction: 'switchMode',
                                linkText: 'Administration']"/>
            </g:if>

            <g:else>
                <g:render
                        template="adminToggleLabel"
                        plugin="metridocRid"
                        model="[controllerName: controllerName,
                                actionName: actionName,
                                linkController: 'ridAdminTransaction',
                                linkAction: 'switchMode',
                                linkText: 'Transactions']"/>
            </g:else>
        </shiro:hasRole>

    </ul>

</div>