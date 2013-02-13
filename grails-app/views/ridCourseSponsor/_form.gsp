<%@ page import="metridoc.rid.RidCourseSponsor" %>

%{--<div class="control-group fieldcontain ${hasErrors(bean: ridCourseSponsorInstance, field: 'name', 'error')}">--}%
<div class="control-group fieldcontain">
	<label class="control-label" for="name">
		<g:message code="ridCourseSponsor.name.label" default="Name" />
	</label>
    <div class="controls">
        <g:textField class="userInput" name="name" value="${ridCourseSponsorInstance?.name}"/>
    </div>
</div>

%{--<div class="control-group fieldcontain ${hasErrors(bean: ridCourseSponsorInstance, field: 'inForm', 'error')}required">--}%
<div class="control-group fieldcontain required">
	<label class="control-label" for="inForm">
		<g:message code="ridCourseSponsor.inForm.label" default="In Form" />
		<span class="required-indicator">*</span>
	</label>
    <div class="controls">
        <% def choices = ['NO', 'YES, and no indication needed', 'YES, and indication required'] %>
        %{--<g:if test="${ridCourseSponsorInstance?.constraints?.inForm?.inList}">--}%
            <g:select name="inForm" from="${choices}" value="${ridCourseSponsorInstance?.inForm}" keys="${ridCourseSponsorInstance.constraints.inForm.inList}" />
	        %{--<g:select name="inForm" from="${ridCourseSponsorInstance.constraints.inForm.inList}" required="" value="${fieldValue(bean: ridCourseSponsorInstance, field: 'inForm')}" valueMessagePrefix="ridCourseSponsor.inForm"/>--}%
        %{--</g:if>--}%
        %{--<g:else>--}%
            %{--<g:select name="inForm" from="${RidCourseSponsor.constraints.inForm.inList}" required=""/>--}%
        %{--</g:else>--}%
    </div>
</div>

%{--<div class="control-group fieldcontain ${hasErrors(bean: ridCourseSponsorInstance, field: 'ridTransaction', 'error')}">--}%
	%{--<label class="control-label" for="ridTransaction">--}%
		%{--<g:message code="ridCourseSponsor.ridTransaction.label" default="Rid Transaction" />--}%

	%{--</label>--}%
    %{--<div class="controls">--}%

%{--<ul class="one-to-many">--}%
%{--<g:each in="${ridCourseSponsorInstance?.ridTransaction?}" var="r">--}%
    %{--<li><g:link controller="ridTransaction" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>--}%
%{--</g:each>--}%
%{--<li class="add">--}%
%{--<g:link controller="ridTransaction" action="create" params="['ridCourseSponsor.id': ridCourseSponsorInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'ridTransaction.label', default: 'RidTransaction')])}</g:link>--}%
%{--</li>--}%
%{--</ul>--}%

    %{--</div>--}%
%{--</div>--}%

