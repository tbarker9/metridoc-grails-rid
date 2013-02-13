<%@ page import="metridoc.rid.RidModeOfConsultation" %>

<div class="control-group fieldcontain">
	<label class="control-label" for="name">
		<g:message code="ridModeOfConsultation.name.label" default="Name" />
		
	</label>
    <div class="controls">
	    <g:textField class="userInput" name="name" value="${ridModeOfConsultationInstance?.name}"/>
    </div>
</div>


<div class="control-group fieldcontain ${hasErrors(bean: ridModeOfConsultationInstance, field: 'ridGroupType', 'error')} required">
    <label class="control-label" for="ridGroupType">
        <g:message code="ridModeOfConsultationInstance.ridGroupType.label" default="Group Type" />
        <span class="required-indicator">*</span>
    </label>
    <div class="controls">
        <g:select id="ridGroupType" style="width:120px" name="ridGroupType.id" from="${metridoc.rid.RidGroupType.list()}"
                  optionKey="id" required="" value="${ridModeOfConsultationInstance?.ridGroupType?.id}" class="many-to-one"/>
    </div>
</div>