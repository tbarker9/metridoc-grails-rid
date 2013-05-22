<ul class="nav nav-pills">

    <g:render
            template="toggleLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridTransaction',
                    linkAction: 'consultation',
                    linkText: 'Consultation']"/>
    <g:render
            template="toggleLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridTransaction',
                    linkAction: 'instructional',
                    linkText: 'Instructional']"/>

</ul>