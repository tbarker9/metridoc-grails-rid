
<%@ page import="metridoc.rid.RidEntityAffiliation" %>
<g:set var="entityName" value="${message(code: 'ridEntityAffiliation.label', default: 'RidEntityAffiliation')}" />

<md:report>
        <div class="md-application-content">

            <div id="show-ridEntityAffiliation" class="content scaffold-show" role="main">
                <h1><g:message code="default.show.label" args="[entityName]" /></h1>
                <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
                </g:if>
                <ol class="property-list ridEntityAffiliation">
                
                    <g:if test="${ridEntityAffiliationInstance?.name}">
                    <li class="fieldcontain">
                        <span id="name-label" class="property-label"><g:message code="ridEntityAffiliation.name.label" default="Name" /></span>
                        
                            <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${ridEntityAffiliationInstance}" field="name"/></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridEntityAffiliationInstance?.inForm}">
                    <li class="fieldcontain">
                        <span id="inForm-label" class="property-label"><g:message code="ridEntityAffiliation.inForm.label" default="In Form" /></span>
                        
                            <span class="property-value" aria-labelledby="inForm-label"><g:fieldValue bean="${ridEntityAffiliationInstance}" field="inForm"/></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridEntityAffiliationInstance?.ridTransaction}">
                    <li class="fieldcontain">
                        <span id="ridTransaction-label" class="property-label"><g:message code="ridEntityAffiliation.ridTransaction.label" default="Rid Transaction" /></span>
                        
                            <g:each in="${ridEntityAffiliationInstance.ridTransaction}" var="r">
                            <span class="property-value" aria-labelledby="ridTransaction-label"><g:link controller="ridTransaction" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
                            </g:each>
                        
                    </li>
                    </g:if>
                
                </ol>
                <g:form>
                    <fieldset class="buttons">
                        <g:hiddenField name="id" value="${ridEntityAffiliationInstance?.id}" />
                        <g:actionSubmit class="btn btn-success" action="edit" id="${ridEntityAffiliationInstance?.id}" value="${message(code: 'default.button.edit.label', default: 'Edit')}" />
                        <g:actionSubmit class="btn btn-danger" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </fieldset>
                </g:form>
            </div>
        </div>
</md:report>
