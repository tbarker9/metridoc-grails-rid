<%@ page import="metridoc.rid.RidUserGoal" %>

<div class="control-group fieldcontain required">
    <label class="control-label" for="name">
        <g:message code="ridUserGoal.name.label" default="Name"/>
        <span class="required-indicator">*</span>
    </label>

    <div class="controls">
        <g:textField class="userInput" name="name" required="" value="${ridUserGoalInstance?.name}"/>
    </div>
</div>

<div class="control-group fieldcontain">
    <label class="control-label" for="inForm">
        <g:message code="ridUserGoal.inForm.label" default="In Form"/>
        <span class="required-indicator">*</span>
    </label>

    <div class="controls">
        <% def choices = ['NO', 'YES, and no indication needed', 'YES, and indication required'] %>
        <g:select name="inForm" from="${choices}" required=""
                  value="${ridUserGoalInstance?.inForm}" keys="${ridUserGoalInstance.constraints.inForm.inList}"/>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: ridUserGoalInstance, field: 'ridLibraryUnit', 'error')} required">
    <label class="control-label" for="ridLibraryUnit">
        <g:message code="ridUserGoalInstance.ridLibraryUnit.label" default="Library Unit"/>
        <span class="required-indicator">*</span>
    </label>

    <div class="controls">
        <g:select id="ridLibraryUnit" style="width:120px" name="ridLibraryUnit.id"
                  from="${metridoc.rid.RidLibraryUnit.list()}"
                  optionKey="id" required="" value="${ridUserGoalInstance?.ridLibraryUnit?.id}" class="many-to-one"/>
    </div>
</div>