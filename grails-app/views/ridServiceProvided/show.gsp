
<%@ page import="metridoc.rid.RidServiceProvided" %>
<g:set var="entityName" value="${message(code: 'ridServiceProvided.label', default: 'RidServiceProvided')}" />

<md:report>
        <div class="md-application-content">

            <div id="show-ridServiceProvided" class="content scaffold-show" role="main">
                <h1><g:message code="default.show.label" args="[entityName]" /></h1>
                <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
                </g:if>
                <ol class="property-list ridServiceProvided">
                
                    <g:if test="${ridServiceProvidedInstance?.name}">
                    <li class="fieldcontain">
                        <span id="name-label" class="property-label"><g:message code="ridServiceProvided.name.label" default="Name" /></span>
                        
                            <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${ridServiceProvidedInstance}" field="name"/></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridServiceProvidedInstance?.inForm}">
                    <li class="fieldcontain">
                        <span id="inForm-label" class="property-label"><g:message code="ridServiceProvided.inForm.label" default="In Form" /></span>
                        
                            <span class="property-value" aria-labelledby="inForm-label"><g:fieldValue bean="${ridServiceProvidedInstance}" field="inForm"/></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridServiceProvidedInstance?.ridTransaction}">
                    <li class="fieldcontain">
                        <span id="ridTransaction-label" class="property-label"><g:message code="ridServiceProvided.ridTransaction.label" default="Rid Transaction" /></span>
                        
                            <g:each in="${ridServiceProvidedInstance.ridTransaction}" var="r">
                            <span class="property-value" aria-labelledby="ridTransaction-label"><g:link controller="ridTransaction" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
                            </g:each>
                        
                    </li>
                    </g:if>
                
                </ol>
                <g:form>
                    <fieldset class="buttons">
                        <g:hiddenField name="id" value="${ridServiceProvidedInstance?.id}" />
                        <g:actionSubmit class="btn btn-success" action="edit" id="${ridServiceProvidedInstance?.id}" value="${message(code: 'default.button.edit.label', default: 'Edit')}" />
                        <g:actionSubmit class="btn btn-danger" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </fieldset>
                </g:form>
            </div>
        </div>
</md:report>
