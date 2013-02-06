<%@ page import="metridoc.rid.RidTransaction" %>
<g:set var="entityName" value="${message(code: 'ridTransaction.label', default: 'RidTransaction')}" />

<md:report>
    <div class="md-application-content">
        <r:external dir="css" file="ridtrans.css" plugin="metridoc-rid"/>
        <r:external dir="js" file="RidTransaction.js" plugin="metridoc-rid"/>

        <g:render template="tabs" plugin="metridocRid"/>

        <div id="search-ridTransaction" class="content scaffold-search" role="main">
            <h1><g:message code="RidTransaction Search" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${ridTransactionInstance}">
                <ul class="errors" role="alert">
                    <g:eachError bean="${ridTransactionInstance}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                    </g:eachError>
                </ul>
            </g:hasErrors>

            <g:form class="form-horizontal" action="query" method="GET">
                <fieldset class="form">
                    <div class="control-group fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'dateOfConsultation', 'error')} required">
                        <label class="control-label" for="dateOfConsultation">
                            <g:message code="ridTransaction.dateOfConsultation.label" default="Consult-Date Between" />
                        </label>
                        <%
                            Calendar dateOfConsultation1 = Calendar.getInstance()
                            Calendar dateOfConsultation2 = (Calendar) dateOfConsultation1.clone()
                            dateOfConsultation2.add(Calendar.DAY_OF_YEAR, - (365 * 2))
                        %>
                        <div class="controls">
                            <g:datePicker id="dateOfConsultation_start" name="dateOfConsultation_start" precision="day" value="${dateOfConsultation2}" />
                            <span style="color: #666666">and</span>
                            <g:datePicker id="dateOfConsultation_end" name="dateOfConsultation_end" precision="day" value="${dateOfConsultation1}" />
                        </div>
                    </div>
                    <div class="control-group fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'staffPennkey', 'error')} required">
                        <label class="control-label" for="staffPennkey">
                            <g:message code="ridTransaction.staffPennkey.label" default="Staff Pennkey"/>
                        </label>
                        <div class="controls">
                            <g:textField id="staffPennkey" style="width:150px" class="userInput" name="staffPennkey" maxlength="100" value="" />
                        </div>
                    </div>
                    <div class="control-group fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'customerQuestion', 'error')} ">
                        <label class="control-label" for="customerQuestion">
                            <g:message code="ridTransaction.customerQuestion.label" default="Customer Question"/>
                        </label>
                        <div class="controls">
                            <g:textField id="customerQuestion" style="width:350px" class="userInput" name="customerQuestion" maxlength="100" value="" />
                        </div>
                    </div>
                    <div class="control-group fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'notes', 'error')} ">
                        <label class="control-label" for="notes">
                            <g:message code="ridTransaction.notes.label" default="Notes"/>
                        </label>
                        <div class="controls">
                            <g:textField id="notes" style="width:350px" class="userInput" name="notes" maxlength="100" value="" />
                        </div>
                    </div>
                </fieldset>
                <fieldset class="buttons">
                    <input id="resetButton" class="btn btn-success" type="reset" value="Reset" />
                    <g:submitButton name="search" class="btn btn-primary" value="${message(code: 'default.button.search.label', default: 'Search')}" />
                </fieldset>
            </g:form>
        </div>
    </div>
</md:report>