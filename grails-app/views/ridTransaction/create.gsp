<%@ page import="metridoc.rid.RidTransaction" %>
<g:set var="entityName" value="${message(code: 'ridTransaction.label', default: 'RidTransaction')}" />

<md:report>
        <r:external dir="js" file="RidTransaction.js" plugin="metridoc-rid"/>
        <div class="md-application-content">
            <g:render template="/ridTransaction/tabs" plugin="metridocRid"/>

            <div id="create-ridTransaction" class="content scaffold-create" role="main">
                <h1><g:message code="default.create.label" args="[entityName]" /></h1>
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
                <g:form action="save">
                    <fieldset class="form">
                        <g:render template="form" plugin="metridoc-rid"/>
                    </fieldset>
                    <fieldset class="buttons">
                        <input id="resetButton" class="btn btn-success" type="reset" value="Reset" />
                        <g:submitButton name="create" class="btn btn-danger" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                    </fieldset>
                </g:form>
            </div>
        </div>
</md:report>