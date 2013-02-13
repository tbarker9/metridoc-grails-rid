
<%@ page import="metridoc.rid.RidModeOfConsultation" %>
<g:set var="entityName" value="${message(code: 'ridModeOfConsultation.label', default: 'RidModeOfConsultation')}" />

<md:report>
        <div class="md-application-content">

            <div id="show-ridModeOfConsultation" class="content scaffold-show" role="main">
                <h1><g:message code="default.show.label" args="[entityName]" /></h1>
                <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
                </g:if>
                <ol class="property-list ridModeOfConsultation">
                
                    <g:if test="${ridModeOfConsultationInstance?.name}">
                    <li class="fieldcontain">
                        <span id="name-label" class="property-label"><g:message code="ridModeOfConsultation.name.label" default="Name" /></span>
                        
                            <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${ridModeOfConsultationInstance}" field="name"/></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridModeOfConsultationInstance?.ridTransaction}">
                    <li class="fieldcontain">
                        <span id="ridTransaction-label" class="property-label"><g:message code="ridModeOfConsultation.ridTransaction.label" default="Rid Transaction" /></span>
                        
                            <g:each in="${ridModeOfConsultationInstance.ridTransaction}" var="r">
                            <span class="property-value" aria-labelledby="ridTransaction-label"><g:link controller="ridTransaction" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
                            </g:each>
                        
                    </li>
                    </g:if>
                
                </ol>
                <g:form>
                    <fieldset class="buttons">
                        <g:hiddenField name="id" value="${ridModeOfConsultationInstance?.id}" />
                        <g:actionSubmit class="btn btn-success" action="edit" id="${ridModeOfConsultationInstance?.id}" value="${message(code: 'default.button.edit.label', default: 'Edit')}" />
                        <g:actionSubmit class="btn btn-danger" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </fieldset>
                </g:form>
            </div>
        </div>
</md:report>
