<%@ page import="metridoc.rid.RidEntityAffiliation" %>

<div class="control-group fieldcontain">
	<label class="control-label" for="name">
		<g:message code="ridEntityAffiliation.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
    <div class="controls">
	    <g:textField class="userInput" name="name" required="" value="${ridEntityAffiliationInstance?.name}"/>
    </div>
</div>

<div class="control-group fieldcontain">
	<label class="control-label" for="inForm">
		<g:message code="ridEntityAffiliation.inForm.label" default="In Form" />
		<span class="required-indicator">*</span>
	</label>
    <div class="controls">
        <% def choices = ['NO', 'YES, and no indication needed', 'YES, and indication required'] %>
        <g:select name="inForm" from="${choices}" value="${ridEntityAffiliationInstance?.inForm}" keys="${ridEntityAffiliationInstance.constraints.inForm.inList}" />
    </div>
</div>
