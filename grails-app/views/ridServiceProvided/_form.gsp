<%@ page import="metridoc.rid.RidServiceProvided" %>

<div class="control-group fieldcontain">
	<label class="control-label" for="name">
		<g:message code="ridServiceProvided.name.label" default="Name" />
		
	</label>
    <div class="controls">
	    <g:textField class="userInput" name="name" value="${ridServiceProvidedInstance?.name}"/>
    </div>
</div>

<div class="control-group fieldcontain">
	<label class="control-label" for="inForm">
		<g:message code="ridServiceProvided.inForm.label" default="In Form" />
		<span class="required-indicator">*</span>
	</label>
    <div class="controls">
        <% def choices = ['NO', 'YES, and no indication needed', 'YES, and indication required'] %>
        <g:select name="inForm" from="${choices}" value="${ridServiceProvidedInstance?.inForm}" keys="${ridServiceProvidedInstance.constraints.inForm.inList}" />
    </div>
</div>


<div class="control-group fieldcontain ${hasErrors(bean: ridServiceProvidedInstance, field: 'ridGroupType', 'error')} required">
    <label class="control-label" for="ridGroupType">
        <g:message code="ridServiceProvided.ridGroupType.label" default="Group Type" />
        <span class="required-indicator">*</span>
    </label>
    <div class="controls">
        <g:select id="ridGroupType" style="width:120px" name="ridGroupType.id" from="${metridoc.rid.RidGroupType.list()}"
                  optionKey="id" required="" value="${ridServiceProvidedInstance?.ridGroupType?.id}" class="many-to-one"/>
    </div>
</div>