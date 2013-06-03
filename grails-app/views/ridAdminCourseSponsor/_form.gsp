<%@ page import="metridoc.rid.RidCourseSponsor" %>

<div class="control-group fieldcontain required">
    <label class="control-label" for="name">
        <g:message code="ridCourseSponsor.name.label" default="Name"/>
        <span class="required-indicator">*</span>
    </label>

    <div class="controls">
        <g:textField class="userInput" name="name" required="" value="${ridCourseSponsorInstance?.name}"/>
    </div>
</div>

<div class="control-group fieldcontain required">
    <label class="control-label" for="inForm">
        <g:message code="ridCourseSponsor.inForm.label" default="In Form"/>
        <span class="required-indicator">*</span>
    </label>

    <div class="controls">
        <% def choices = ['NO', 'YES, and no indication needed', 'YES, and indication required'] %>
        <g:select name="inForm" from="${choices}" required=""
                  value="${ridCourseSponsorInstance?.inForm}"
                  keys="${ridCourseSponsorInstance.constraints.inForm.inList}"/>
    </div>
</div>
