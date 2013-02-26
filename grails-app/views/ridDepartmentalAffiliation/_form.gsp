<%@ page import="metridoc.rid.RidDepartmentalAffiliation" %>

<div class="control-group fieldcontain required">
	<label class="control-label" for="name">
		<g:message code="ridDepartmentalAffiliation.name.label" default="Name" />
        <span class="required-indicator">*</span>
	</label>
    <div class="controls">
	    <g:textField class="userInput" name="name" required="" value="${ridDepartmentalAffiliationInstance?.name}"/>
    </div>
</div>
