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

        <g:render
                template="toggleLabelDD"
                plugin="metridocRid"
                model="[controllerName: controllerName,
                        actionName: actionName,
                        linkController: 'ridTransaction',
                        linkAction: 'consultation',
                        linkText: 'Consultation']"/>
        <g:render
                template="toggleLabelDD"
                plugin="metridocRid"
                model="[controllerName: controllerName,
                        actionName: actionName,
                        linkController: 'ridTransaction',
                        linkAction: 'instructional',
                        linkText: 'Instructional']"/>

    </ul>

</div>