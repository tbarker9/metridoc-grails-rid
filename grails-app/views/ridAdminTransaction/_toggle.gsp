<g:if test="${session.display == "dropdown"}">
    <g:render template="/ridAdminTransaction/toggleDD" plugin="metridoc-rid"/>
    <br>

</g:if>
<g:elseif test="${session.display == "tabs"}">
</g:elseif>
<g:else>
    <ul class="nav nav-pills">

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

        <g:render template="/ridAdminTransaction/adminToggle" plugin="metridoc-rid"/>

    </ul>
</g:else>