<%@ page import="metridoc.rid.RidModeOfConsutlation" %>

<div class="control-group fieldcontain">
	<label class="control-label" for="name">
		<g:message code="ridModeOfConsutlation.name.label" default="Name" />
		
	</label>
    <div class="controls">
	    <g:textField class="userInput" name="name" value="${ridModeOfConsutlationInstance?.name}"/>
    </div>
</div>
