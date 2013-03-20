<%@ page import="metridoc.rid.RidCourseSponsor" %>
<g:set var="entityName" value="${message(code: 'ridCourseSponsor.label', default: 'RidCourseSponsor')}"/>

%{--<md:report>--}%
%{--<div class="md-application-content">--}%
%{--<g:render template="tabs" plugin="metridocRid"/>--}%

<div id="create-ridCourseSponsor" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>

%{--<g:hasErrors bean="${ridCourseSponsorInstance}">--}%
%{--<ul class="errors" role="alert">--}%
%{--<g:eachError bean="${ridCourseSponsorInstance}" var="error">--}%
%{--<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>>--}%
%{--<g:message error="${error}"/>--}%
%{--</li>--}%
%{--</g:eachError>--}%
%{--</ul>--}%
%{--</g:hasErrors>--}%

    <g:form class="form-horizontal" action="save" useToken="true">
    %{--<fieldset class="form">--}%
        <div style="margin-top: 2em">
            <g:render template="form" plugin="metridoc-rid"/>
        </div>
    %{--</fieldset>--}%
        <fieldset class="buttons">
            <g:submitButton style="float: right" name="create" class="btn btn-danger"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:form>
</div>

%{--</div>--}%
%{--</md:report>--}%
