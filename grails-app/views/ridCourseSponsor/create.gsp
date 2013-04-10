<%@ page import="metridoc.rid.RidCourseSponsor" %>
<g:set var="entityName" value="${message(code: 'ridCourseSponsor.label', default: 'RidCourseSponsor')}"/>

<div id="create-ridCourseSponsor" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>

    <g:form class="form-horizontal" action="save" useToken="true">
        <div style="margin-top: 2em">
            <g:render template="form" plugin="metridoc-rid"/>
        </div>
        <fieldset class="buttons">
            <g:submitButton style="float: right" name="create" class="btn btn-danger"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:form>
</div>

