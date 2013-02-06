
<%@ page import="metridoc.rid.RidCustomer" %>
<g:set var="entityName" value="${message(code: 'ridCustomer.label', default: 'RidCustomer')}" />

<md:report>
        <div class="md-application-content">

            <div id="show-ridCustomer" class="content scaffold-show" role="main">
                <h1><g:message code="default.show.label" args="[entityName]" /></h1>
                <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
                </g:if>
                <ol class="property-list ridCustomer">
                
                    <g:if test="${ridCustomerInstance?.name}">
                    <li class="fieldcontain">
                        <span id="name-label" class="property-label"><g:message code="ridCustomer.name.label" default="Name" /></span>
                        
                            <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${ridCustomerInstance}" field="name"/></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridCustomerInstance?.inForm}">
                    <li class="fieldcontain">
                        <span id="inForm-label" class="property-label"><g:message code="ridCustomer.inForm.label" default="In Form" /></span>
                        
                            <span class="property-value" aria-labelledby="inForm-label"><g:fieldValue bean="${ridCustomerInstance}" field="inForm"/></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridCustomerInstance?.ridTransaction}">
                    <li class="fieldcontain">
                        <span id="ridTransaction-label" class="property-label"><g:message code="ridCustomer.ridTransaction.label" default="Rid Transaction" /></span>
                        
                            <g:each in="${ridCustomerInstance.ridTransaction}" var="r">
                            <span class="property-value" aria-labelledby="ridTransaction-label"><g:link controller="ridTransaction" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
                            </g:each>
                        
                    </li>
                    </g:if>
                
                </ol>
                <g:form>
                    <fieldset class="buttons">
                        <g:hiddenField name="id" value="${ridCustomerInstance?.id}" />
                        <g:actionSubmit class="btn btn-success" action="edit" id="${ridCustomerInstance?.id}" value="${message(code: 'default.button.edit.label', default: 'Edit')}" />
                        <g:actionSubmit class="btn btn-danger" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </fieldset>
                </g:form>
            </div>
        </div>
</md:report>
