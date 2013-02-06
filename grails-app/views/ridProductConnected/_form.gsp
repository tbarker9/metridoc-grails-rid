<%@ page import="metridoc.rid.RidProductConnected" %>

<div class="control-group fieldcontain">
	<label class="control-label" for="name">
		<g:message code="ridProductConnected.name.label" default="Name" />
		
	</label>
    <div class="controls">
	    <g:textField class="userInput" name="name" value="${ridProductConnectedInstance?.name}"/>
    </div>
</div>
