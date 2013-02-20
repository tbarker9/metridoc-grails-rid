<%@ page import="metridoc.rid.RidModeOfConsultation" %>

<div class="control-group fieldcontain">
	<label class="control-label" for="name">
		<g:message code="ridModeOfConsultation.name.label" default="Name" />
		
	</label>
    <div class="controls">
	    <g:textField class="userInput" name="name" value="${ridModeOfConsultationInstance?.name}"/>
    </div>
</div>


<div class="control-group fieldcontain ${hasErrors(bean: ridModeOfConsultationInstance, field: 'ridReportType', 'error')} required">
    <label class="control-label" for="ridReportType">
        <g:message code="ridModeOfConsultationInstance.ridReportType.label" default="Report Type" />
        <span class="required-indicator">*</span>
    </label>
    <div class="controls">
        <g:select id="ridReportType" style="width:120px" name="ridReportType.id" from="${metridoc.rid.RidReportType.list()}"
                  optionKey="id" required="" value="${ridModeOfConsultationInstance?.ridReportType?.id}" class="many-to-one"/>
    </div>
</div>