<%@ page import="metridoc.rid.RidCustomer" %>

<div class="control-group fieldcontain">
	<label class="control-label" for="name">
		<g:message code="ridCustomer.name.label" default="Name" />
		
	</label>
    <div class="controls">
	    <g:textField class="userInput" name="name" value="${ridCustomerInstance?.name}"/>
    </div>
</div>

<div class="control-group fieldcontain">
	<label class="control-label" for="inForm">
		<g:message code="ridCustomer.inForm.label" default="In Form" />
		<span class="required-indicator">*</span>
	</label>
    <div class="controls">
	    <g:select name="inForm" from="${ridCustomerInstance.constraints.inForm.inList}" required="" value="${fieldValue(bean: ridCustomerInstance, field: 'inForm')}" valueMessagePrefix="ridCustomer.inForm"/>
    </div>
</div>



