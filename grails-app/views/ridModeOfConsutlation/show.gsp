
<%@ page import="metridoc.rid.RidModeOfConsutlation" %>
<g:set var="entityName" value="${message(code: 'ridModeOfConsutlation.label', default: 'RidModeOfConsutlation')}" />

<md:report>
        <div class="md-application-content">

            <div id="show-ridModeOfConsutlation" class="content scaffold-show" role="main">
                <h1><g:message code="default.show.label" args="[entityName]" /></h1>
                <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
                </g:if>
                <ol class="property-list ridModeOfConsutlation">
                
                    <g:if test="${ridModeOfConsutlationInstance?.name}">
                    <li class="fieldcontain">
                        <span id="name-label" class="property-label"><g:message code="ridModeOfConsutlation.name.label" default="Name" /></span>
                        
                            <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${ridModeOfConsutlationInstance}" field="name"/></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridModeOfConsutlationInstance?.ridTransaction}">
                    <li class="fieldcontain">
                        <span id="ridTransaction-label" class="property-label"><g:message code="ridModeOfConsutlation.ridTransaction.label" default="Rid Transaction" /></span>
                        
                            <g:each in="${ridModeOfConsutlationInstance.ridTransaction}" var="r">
                            <span class="property-value" aria-labelledby="ridTransaction-label"><g:link controller="ridTransaction" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
                            </g:each>
                        
                    </li>
                    </g:if>
                
                </ol>
                <g:form>
                    <fieldset class="buttons">
                        <g:hiddenField name="id" value="${ridModeOfConsutlationInstance?.id}" />
                        <g:actionSubmit class="btn btn-success" action="edit" id="${ridModeOfConsutlationInstance?.id}" value="${message(code: 'default.button.edit.label', default: 'Edit')}" />
                        <g:actionSubmit class="btn btn-danger" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </fieldset>
                </g:form>
            </div>
        </div>
</md:report>
