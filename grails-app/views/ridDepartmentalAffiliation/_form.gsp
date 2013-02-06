<%@ page import="metridoc.rid.RidDepartmentalAffiliation" %>

<div class="control-group fieldcontain">
	<label class="control-label" for="name">
		<g:message code="ridDepartmentalAffiliation.name.label" default="Name" />
		
	</label>
    <div class="controls">
	    <g:textField class="userInput" name="name" value="${ridDepartmentalAffiliationInstance?.name}"/>
    </div>
</div>
