<ul class="nav nav-tabs">
    <g:render
            template="/ridTransaction/tabLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridTransaction',
                    linkAction: 'create',
                    linkText: 'Create Transaction',
                    icon: 'icon-plus-sign-alt']"/>
    <g:render
            template="/ridTransaction/tabLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridTransaction',
                    linkAction: 'search',
                    linkText: 'Search Transaction',
                    icon: 'icon-search']"/>
    <g:render
            template="/ridTransaction/tabLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridTransaction',
                    linkAction: 'spreadsheetUpload',
                    linkText: 'Upload Spreadsheet',
                    icon: 'icon-cloud-upload']"/>

    <g:render
            template="toggleLabelRight"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridTransaction',
                    linkAction: 'consultation',
                    linkText: 'Consultation']"/>
    <g:render
            template="toggleLabelRight"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridTransaction',
                    linkAction: 'instructional',
                    linkText: 'Instructional']"/>

</ul>


