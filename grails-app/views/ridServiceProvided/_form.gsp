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
	    <g:select name="inForm" from="${ridServiceProvidedInstance.constraints.inForm.inList}" required="" value="${fieldValue(bean: ridServiceProvidedInstance, field: 'inForm')}" valueMessagePrefix="ridServiceProvided.inForm"/>
    </div>
</div>
