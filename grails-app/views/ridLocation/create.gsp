<%@ page import="metridoc.rid.RidLocation" %>
<g:set var="entityName" value="${message(code: 'ridLocation.label', default: 'RidLocation')}"/>

<div id="create-ridLocation" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>

    <g:form class="form-horizontal" action="save" useToken="true">
        <div style="margin-top: 2em">
            <tmpl:form/>
        </div>
        <fieldset class="buttons">
            <g:submitButton name="create" class="btn btn-danger" style="float: right"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:form>
</div>
