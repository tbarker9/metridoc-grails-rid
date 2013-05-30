<ul class="nav nav-pills">
    <r:external dir="css" file="ridtrans.css" plugin="metridoc-rid"/>

    <g:render
            template="/ridTransactionAdmin/toggleLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridTransactionAdmin',
                    linkAction: 'consultation',
                    linkText: 'Consultation']"/>
    <g:render
            template="/ridTransactionAdmin/toggleLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridTransactionAdmin',
                    linkAction: 'instructional',
                    linkText: 'Instructional']"/>

</ul>