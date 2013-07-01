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
<br>

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

<li>
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

<g:if env="development">
    <g:if test="${actionName == "stats" || actionName == "statSearch" || actionName == "statGraph" || actionName == "statQuery"}">
        <li class="dropdown active">
    </g:if>
    <g:else>
        <li class="dropdown green">
    </g:else>
    <a class="dropdown-toggle green" data-toggle="dropdown">
        <i class="icon-signal"></i>
        Statistics

        <span class="caret"></span></a>

    <ul class="dropdown-menu">

    <g:render
            template="/ridTransaction/tabLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridTransaction',
                    linkAction: 'stats',
                    linkText: 'Statistics Overview',
                    icon: 'icon-reorder']"/>

    <g:render
            template="/ridTransaction/tabLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridTransaction',
                    linkAction: 'statSearch',
                    linkText: 'Filtered Statistics',
                    icon: 'icon-search']"/>

    <g:render
            template="/ridTransaction/tabLabel"
            plugin="metridocRid"
            model="[controllerName: controllerName,
                    actionName: actionName,
                    linkController: 'ridTransaction',
                    linkAction: 'statGraph',
                    linkText: 'Graphed Statistics',
                    icon: 'icon-bar-chart']"/>
    </li>
    </ul>
</g:if>
</ul>
</ul>


