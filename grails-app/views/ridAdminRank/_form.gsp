<%@ page import="metridoc.rid.RidRank" %>

<div class="control-group fieldcontain required">
    <label class="control-label" for="name">
        <g:message code="ridRank.name.label" default="Name"/>
        <span class="required-indicator">*</span>
    </label>

    <div class="controls">
        <g:textField class="userInput" name="name" required="" value="${ridRankInstance?.name}"/>
    </div>
</div>

<div class="control-group fieldcontain required">
    <label class="control-label" for="inForm">
        <g:message code="ridRank.inForm.label" default="In Form"/>
        <span class="required-indicator">*</span>
    </label>

    <div class="controls">
        <% def choices = ['NO', 'YES, and no indication needed', 'YES, and indication required'] %>
        %{--<g:select name="inForm" from="${ridRankInstance.constraints.inForm.inList}" required="" value="${fieldValue(bean: ridRankInstance, field: 'inForm')}" valueMessagePrefix="ridRank.inForm"/>--}%
        <g:select name="inForm" from="${choices}" required=""
                  value="${ridRankInstance?.inForm}" keys="${ridRankInstance.constraints.inForm.inList}"/>
    </div>
</div>



