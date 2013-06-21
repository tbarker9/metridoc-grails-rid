<style type="text/css">
.nav-tabs .active a,
.nav-tabs .active a:hover {
    font-size: 15px;
}

.nav-tabs  a,
.nav-tabs  a:hover {
    font-size: 13px;
}

</style>

<g:if test="${session.display == "tabs"}">
    <tmpl:tabsWithToggle/>

</g:if>
<g:else>
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

    </ul>
</g:else>

