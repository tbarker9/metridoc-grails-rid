
<%@ page import="metridoc.rid.RidDepartmentalAffiliation" %>
<g:set var="entityName" value="${message(code: 'ridDepartmentalAffiliation.label', default: 'RidDepartmentalAffiliation')}" />

<md:report>
        <div class="md-application-content">

            <div id="show-ridDepartmentalAffiliation" class="content scaffold-show" role="main">
                <h1><g:message code="default.show.label" args="[entityName]" /></h1>
                <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
                </g:if>
                <ol class="property-list ridDepartmentalAffiliation">
                
                    <g:if test="${ridDepartmentalAffiliationInstance?.name}">
                    <li class="fieldcontain">
                        <span id="name-label" class="property-label"><g:message code="ridDepartmentalAffiliation.name.label" default="Name" /></span>
                        
                            <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${ridDepartmentalAffiliationInstance}" field="name"/></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridDepartmentalAffiliationInstance?.ridTransaction}">
                    <li class="fieldcontain">
                        <span id="ridTransaction-label" class="property-label"><g:message code="ridDepartmentalAffiliation.ridTransaction.label" default="Rid Transaction" /></span>
                        
                            <g:each in="${ridDepartmentalAffiliationInstance.ridTransaction}" var="r">
                            <span class="property-value" aria-labelledby="ridTransaction-label"><g:link controller="ridTransaction" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
                            </g:each>
                        
                    </li>
                    </g:if>
                
                </ol>
                <g:form>
                    <fieldset class="buttons">
                        <g:hiddenField name="id" value="${ridDepartmentalAffiliationInstance?.id}" />
                        <g:actionSubmit class="btn btn-success" action="edit" id="${ridDepartmentalAffiliationInstance?.id}" value="${message(code: 'default.button.edit.label', default: 'Edit')}" />
                        <g:actionSubmit class="btn btn-danger" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </fieldset>
                </g:form>
            </div>
        </div>
</md:report>
