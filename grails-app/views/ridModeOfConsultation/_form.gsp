<%@ page import="metridoc.rid.RidModeOfConsultation" %>

<div class="control-group fieldcontain required">
    <label class="control-label" for="name">
        <g:message code="ridModeOfConsultation.name.label" default="Name"/>
        <span class="required-indicator">*</span>
    </label>

    <div class="controls">
        <g:textField class="userInput" name="name" required="" value="${ridModeOfConsultationInstance?.name}"/>
    </div>
</div>

<div class="control-group fieldcontain">
    <label class="control-label" for="inForm">
        <g:message code="ridModeOfConsultation.inForm.label" default="In Form"/>
        <span class="required-indicator">*</span>
    </label>

    <div class="controls">
        <% def choices = ['NO', 'YES, and no indication needed', 'YES, and indication required'] %>
        <g:select name="inForm" from="${choices}" required=""
                  value="${ridModeOfConsultationInstance?.inForm}"
                  keys="${ridModeOfConsultationInstance.constraints.inForm.inList}"/>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: ridModeOfConsultationInstance, field: 'ridLibraryUnit', 'error')} required">
    <label class="control-label" for="ridLibraryUnit">
        <g:message code="ridModeOfConsultationInstance.ridLibraryUnit.label" default="Library Unit"/>
        <span class="required-indicator">*</span>
    </label>

    <div class="controls">
        <g:select id="ridLibraryUnit" style="width:120px" name="ridLibraryUnit.id"
                  from="${metridoc.rid.RidLibraryUnit.list()}"
                  optionKey="id" required="" value="${ridModeOfConsultationInstance?.ridLibraryUnit?.id}"
                  class="many-to-one"/>
    </div>
</div>