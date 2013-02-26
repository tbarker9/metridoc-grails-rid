<%@ page import="metridoc.rid.RidUser" %>

<div class="control-group fieldcontain required">
	<label class="control-label" for="name">
		<g:message code="ridUser.name.label" default="Name" />
        <span class="required-indicator">*</span>
	</label>
    <div class="controls">
	    <g:textField class="userInput" name="name" required="" value="${ridUserInstance?.name}"/>
    </div>
</div>

<div class="control-group fieldcontain required">
	<label class="control-label" for="inForm">
		<g:message code="ridUser.inForm.label" default="In Form" />
		<span class="required-indicator">*</span>
	</label>
    <div class="controls">
        <% def choices = ['NO', 'YES, and no indication needed', 'YES, and indication required'] %>
	    %{--<g:select name="inForm" from="${ridUserInstance.constraints.inForm.inList}" required="" value="${fieldValue(bean: ridUserInstance, field: 'inForm')}" valueMessagePrefix="ridUser.inForm"/>--}%
        <g:select name="inForm" from="${choices}" required=""
                  value="${ridUserInstance?.inForm}" keys="${ridUserInstance.constraints.inForm.inList}" />
    </div>
</div>



