<%@ page import="metridoc.rid.RidLibraryUnit; metridoc.rid.RidServiceProvided" %>

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


<div class="control-group fieldcontain ${hasErrors(bean: ridServiceProvidedInstance, field: 'ridLibraryUnit', 'error')} required">
    <label class="control-label" for="ridLibraryUnit">
        <g:message code="ridServiceProvided.ridLibraryUnit.label" default="Library Unit"/>
        <span class="required-indicator">*</span>
    </label>

    <div class="controls">
        <g:select id="ridLibraryUnit" style="width:120px" name="ridLibraryUnit.id"
                  from="${RidLibraryUnit.list()}"
                  optionKey="id" required="" value="${ridServiceProvidedInstance?.ridLibraryUnit?.id}"
                  class="many-to-one"/>
    </div>
</div>