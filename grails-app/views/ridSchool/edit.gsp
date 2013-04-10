<%@ page import="metridoc.rid.RidSchool" %>
<g:set var="entityName" value="${message(code: 'ridSchool.label', default: 'RidSchool')}"/>

<div id="edit-ridSchool" class="content scaffold-edit" role="main">
    <h1><g:message code="default.edit.label" args="[entityName]"/></h1>

    <g:form class="form-horizontal" method="post" useToken="true">
        <g:hiddenField name="id" value="${ridSchoolInstance?.id}"/>
        <g:hiddenField name="version" value="${ridSchoolInstance?.version}"/>
        <div style="margin-top: 2em">
            <g:render template="form" plugin="metridocRid"/>
        </div>
        <fieldset class="buttons">
        <g:actionSubmit style="float: right; margin-left: 1em" class="btn btn-success" action="update"
                        value="${message(code: 'default.button.update.label', default: 'Update')}"/>
        </fieldset>
    </g:form>
</div>

