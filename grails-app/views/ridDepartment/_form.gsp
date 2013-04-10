<%@ page import="metridoc.rid.RidDepartment" %>

<div class="control-group fieldcontain required">
    <label class="control-label" for="name">
        <g:message code="ridDepartment.name.label" default="Name"/>
        <span class="required-indicator">*</span>
    </label>

    <div class="controls">
        <g:textField class="userInput" name="name" required="" value="${ridDepartmentInstance?.name}"/>
    </div>
</div>
