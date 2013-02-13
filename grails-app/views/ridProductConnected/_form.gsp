<%@ page import="metridoc.rid.RidProductConnected" %>

<div class="control-group fieldcontain">
	<label class="control-label" for="name">
		<g:message code="ridProductConnected.name.label" default="Name" />
	</label>
    <div class="controls">
	    <g:textField class="userInput" name="name" value="${ridProductConnectedInstance?.name}"/>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: ridProductConnectedInstance, field: 'ridGroupType', 'error')} required">
    <label class="control-label" for="ridGroupType">
        <g:message code="ridProductConnectedInstance.ridGroupType.label" default="Group Type" />
        <span class="required-indicator">*</span>
    </label>
    <div class="controls">
        <g:select id="ridGroupType" style="width:120px" name="ridGroupType.id" from="${metridoc.rid.RidGroupType.list()}"
                  optionKey="id" required="" value="${ridProductConnectedInstance?.ridGroupType?.id}" class="many-to-one"/>
    </div>
</div>