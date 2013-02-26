<%@ page import="metridoc.rid.RidUserAffiliation" %>

<div class="control-group fieldcontain required">
	<label class="control-label" for="name">
		<g:message code="ridUserAffiliation.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
    <div class="controls">
	    <g:textField class="userInput" name="name" required="" value="${ridUserAffiliationInstance?.name}"/>
    </div>
</div>

<div class="control-group fieldcontain required">
	<label class="control-label" for="inForm">
		<g:message code="ridUserAffiliation.inForm.label" default="In Form" />
		<span class="required-indicator">*</span>
	</label>
    <div class="controls">
        <% def choices = ['NO', 'YES, and no indication needed', 'YES, and indication required'] %>
        <g:select name="inForm" from="${choices}" value="${ridUserAffiliationInstance?.inForm}" keys="${ridUserAffiliationInstance.constraints.inForm.inList}" />
    </div>
</div>
