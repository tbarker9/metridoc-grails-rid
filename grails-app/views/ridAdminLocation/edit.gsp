<%@ page import="metridoc.rid.RidLocation" %>
<g:set var="entityName" value="${message(code: 'ridLocation.label', default: 'RidLocation')}"/>

<div id="edit-ridLocation" class="content scaffold-edit" role="main">
    <h1><g:message code="default.edit.label" args="[entityName]"/></h1>

    <g:form class="form-horizontal" method="post" useToken="true">
        <g:hiddenField name="id" value="${ridLocationInstance?.id}"/>
        <g:hiddenField name="version" value="${ridLocationInstance?.version}"/>
        <div style="margin-top: 2em">
            <tmpl:form/>
        </div>
        <fieldset class="buttons">
            <g:actionSubmit style="float: right; margin-left: 1em" class="btn btn-success" action="update"
                            value="${message(code: 'default.button.update.label', default: 'Update')}"/>
        </fieldset>
    </g:form>
</div>

