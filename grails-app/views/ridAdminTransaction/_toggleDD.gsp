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
                    template="/ridAdminTransaction/toggleLabelDD"
                    plugin="metridocRid"
                    model="[controllerName: controllerName,
                            actionName: actionName,
                            linkController: 'ridAdminTransaction',
                            linkAction: 'instructional',
                            linkText: 'Instructional']"/>
        </g:if>
        <g:else>
            <g:render
                    template="/ridAdminTransaction/toggleLabelDD"
                    plugin="metridocRid"
                    model="[controllerName: controllerName,
                            actionName: actionName,
                            linkController: 'ridAdminTransaction',
                            linkAction: 'consultation',
                            linkText: 'Consultation']"/>

        </g:else>

    </ul>

</div>