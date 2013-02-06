<%@ page import="metridoc.rid.RidCourseSponsor" %>
%{--<g:set var="entityName" value="${message(code: 'ridCourseSponsor.label', default: 'RidCourseSponsor')}" />--}%

<div id="cscreateModel" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
     <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

        <h3 id="myModalLabel"><g:message code="default.create.label" args="[entityName]" /></h3>
    </div>

    %{--<g:if test="${flash.message}">--}%
        %{--<div class="message" role="status">${flash.message}</div>--}%
    %{--</g:if>--}%
    %{--<g:hasErrors bean="${ridCourseSponsorInstance}">--}%
    %{--<ul class="errors" role="alert">--}%
        %{--<g:eachError bean="${ridCourseSponsorInstance}" var="error">--}%
            %{--<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>--}%
        %{--</g:eachError>--}%
    %{--</ul>--}%
    %{--</g:hasErrors>--}%

    <g:form class="form-horizontal" action="save">
        <div class="modal-body">
            <g:render template="form" plugin="metridoc-rid"/>
        </div>

        <div class="modal-footer">
            <g:submitButton name="create" class="btn btn-danger" value="${message(code: 'default.button.create.label', default: 'Create')}" />
        </div>
    </g:form>
</div>