<ul class="nav nav-pills">
    <r:external dir="css" file="ridtrans.css" plugin="metridoc-rid"/>

    <g:render
            template="/ridAdminTransaction/toggleLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridAdminTransaction',
                    linkAction: 'consultation',
                    linkText: 'Consultation']"/>
    <g:render
            template="/ridAdminTransaction/toggleLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridAdminTransaction',
                    linkAction: 'instructional',
                    linkText: 'Instructional']"/>

</ul>