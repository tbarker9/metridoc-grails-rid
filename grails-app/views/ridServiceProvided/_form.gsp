<%@ page import="metridoc.rid.RidServiceProvided" %>

<div class="control-group fieldcontain required">
    <label class="control-label" for="name">
        <g:message code="ridServiceProvided.name.label" default="Name"/>
        <span class="required-indicator">*</span>
    </label>

    <div class="controls">
        <g:textField class="userInput" name="name" required="" value="${ridServiceProvidedInstance?.name}"/>
    </div>
</div>

<div class="control-group fieldcontain">
    <label class="control-label" for="inForm">
        <g:message code="ridServiceProvided.inForm.label" default="In Form"/>
        <span class="required-indicator">*</span>
    </label>

    <div class="controls">
        <% def choices = ['NO', 'YES, and no indication needed', 'YES, and indication required'] %>
        <g:select name="inForm" from="${choices}" required=""
                  value="${ridServiceProvidedInstance?.inForm}"
                  keys="${ridServiceProvidedInstance.constraints.inForm.inList}"/>
    </div>
</div>


<div class="control-group fieldcontain ${hasErrors(bean: ridServiceProvidedInstance, field: 'ridReportType', 'error')} required">
    <label class="control-label" for="ridReportType">
        <g:message code="ridServiceProvided.ridReportType.label" default="Report Type"/>
        <span class="required-indicator">*</span>
    </label>

    <div class="controls">
        <g:select id="ridReportType" style="width:120px" name="ridReportType.id"
                  from="${metridoc.rid.RidReportType.list()}"
                  optionKey="id" required="" value="${ridServiceProvidedInstance?.ridReportType?.id}"
                  class="many-to-one"/>
    </div>
</div>