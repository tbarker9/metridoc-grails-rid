
<%@ page import="metridoc.rid.RidTransaction" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ridTransaction.label', default: 'RidTransaction')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
        <div class="md-application-content">
            <div class="nav" role="navigation">
                <ul>
                    <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                    <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                    <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                </ul>
            </div>
            <div id="show-ridTransaction" class="content scaffold-show" role="main">
                <h1><g:message code="default.show.label" args="[entityName]" /></h1>
                <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
                </g:if>
                <ol class="property-list ridTransaction">
                
                    <g:if test="${ridTransactionInstance?.customerQuestion}">
                    <li class="fieldcontain">
                        <span id="customerQuestion-label" class="property-label"><g:message code="ridTransaction.customerQuestion.label" default="Customer Question" /></span>
                        
                            <span class="property-value" aria-labelledby="customerQuestion-label"><g:fieldValue bean="${ridTransactionInstance}" field="customerQuestion"/></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridTransactionInstance?.interactTimes}">
                    <li class="fieldcontain">
                        <span id="interactTimes-label" class="property-label"><g:message code="ridTransaction.interactTimes.label" default="Interact Times" /></span>
                        
                            <span class="property-value" aria-labelledby="interactTimes-label"><g:fieldValue bean="${ridTransactionInstance}" field="interactTimes"/></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridTransactionInstance?.followUpContact}">
                    <li class="fieldcontain">
                        <span id="followUpContact-label" class="property-label"><g:message code="ridTransaction.followUpContact.label" default="Follow Up Contact" /></span>
                        
                            <span class="property-value" aria-labelledby="followUpContact-label"><g:fieldValue bean="${ridTransactionInstance}" field="followUpContact"/></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridTransactionInstance?.prepTime}">
                    <li class="fieldcontain">
                        <span id="prepTime-label" class="property-label"><g:message code="ridTransaction.prepTime.label" default="Prep Time" /></span>
                        
                            <span class="property-value" aria-labelledby="prepTime-label"><g:fieldValue bean="${ridTransactionInstance}" field="prepTime"/></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridTransactionInstance?.eventLength}">
                    <li class="fieldcontain">
                        <span id="eventLength-label" class="property-label"><g:message code="ridTransaction.eventLength.label" default="Event Length" /></span>
                        
                            <span class="property-value" aria-labelledby="eventLength-label"><g:fieldValue bean="${ridTransactionInstance}" field="eventLength"/></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridTransactionInstance?.notes}">
                    <li class="fieldcontain">
                        <span id="notes-label" class="property-label"><g:message code="ridTransaction.notes.label" default="Notes" /></span>
                        
                            <span class="property-value" aria-labelledby="notes-label"><g:fieldValue bean="${ridTransactionInstance}" field="notes"/></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridTransactionInstance?.facultySponsor}">
                    <li class="fieldcontain">
                        <span id="facultySponsor-label" class="property-label"><g:message code="ridTransaction.facultySponsor.label" default="Faculty Sponsor" /></span>
                        
                            <span class="property-value" aria-labelledby="facultySponsor-label"><g:fieldValue bean="${ridTransactionInstance}" field="facultySponsor"/></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridTransactionInstance?.courseName}">
                    <li class="fieldcontain">
                        <span id="courseName-label" class="property-label"><g:message code="ridTransaction.courseName.label" default="Course Name" /></span>
                        
                            <span class="property-value" aria-labelledby="courseName-label"><g:fieldValue bean="${ridTransactionInstance}" field="courseName"/></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridTransactionInstance?.courseNumber}">
                    <li class="fieldcontain">
                        <span id="courseNumber-label" class="property-label"><g:message code="ridTransaction.courseNumber.label" default="Course Number" /></span>
                        
                            <span class="property-value" aria-labelledby="courseNumber-label"><g:fieldValue bean="${ridTransactionInstance}" field="courseNumber"/></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridTransactionInstance?.otherCustomer}">
                    <li class="fieldcontain">
                        <span id="otherCustomer-label" class="property-label"><g:message code="ridTransaction.otherCustomer.label" default="Other Customer" /></span>
                        
                            <span class="property-value" aria-labelledby="otherCustomer-label"><g:fieldValue bean="${ridTransactionInstance}" field="otherCustomer"/></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridTransactionInstance?.otherEntityAffiliation}">
                    <li class="fieldcontain">
                        <span id="otherEntityAffiliation-label" class="property-label"><g:message code="ridTransaction.otherEntityAffiliation.label" default="Other Entity Affiliation" /></span>
                        
                            <span class="property-value" aria-labelledby="otherEntityAffiliation-label"><g:fieldValue bean="${ridTransactionInstance}" field="otherEntityAffiliation"/></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridTransactionInstance?.otherCourseSponsor}">
                    <li class="fieldcontain">
                        <span id="otherCourseSponsor-label" class="property-label"><g:message code="ridTransaction.otherCourseSponsor.label" default="Other Course Sponsor" /></span>
                        
                            <span class="property-value" aria-labelledby="otherCourseSponsor-label"><g:fieldValue bean="${ridTransactionInstance}" field="otherCourseSponsor"/></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridTransactionInstance?.librarian}">
                    <li class="fieldcontain">
                        <span id="librarian-label" class="property-label"><g:message code="ridTransaction.librarian.label" default="Librarian" /></span>
                        
                            <span class="property-value" aria-labelledby="librarian-label"><g:fieldValue bean="${ridTransactionInstance}" field="librarian"/></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridTransactionInstance?.patronEmail}">
                    <li class="fieldcontain">
                        <span id="patronEmail-label" class="property-label"><g:message code="ridTransaction.patronEmail.label" default="Patron Email" /></span>
                        
                            <span class="property-value" aria-labelledby="patronEmail-label"><g:fieldValue bean="${ridTransactionInstance}" field="patronEmail"/></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridTransactionInstance?.otherService}">
                    <li class="fieldcontain">
                        <span id="otherService-label" class="property-label"><g:message code="ridTransaction.otherService.label" default="Other Service" /></span>
                        
                            <span class="property-value" aria-labelledby="otherService-label"><g:fieldValue bean="${ridTransactionInstance}" field="otherService"/></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridTransactionInstance?.courseSponsor}">
                    <li class="fieldcontain">
                        <span id="courseSponsor-label" class="property-label"><g:message code="ridTransaction.courseSponsor.label" default="Course Sponsor" /></span>
                        
                            <span class="property-value" aria-labelledby="courseSponsor-label"><g:link controller="ridCourseSponsor" action="show" id="${ridTransactionInstance?.courseSponsor?.id}">${ridTransactionInstance?.courseSponsor?.encodeAsHTML()}</g:link></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridTransactionInstance?.customer}">
                    <li class="fieldcontain">
                        <span id="customer-label" class="property-label"><g:message code="ridTransaction.customer.label" default="Customer" /></span>
                        
                            <span class="property-value" aria-labelledby="customer-label"><g:link controller="ridCustomer" action="show" id="${ridTransactionInstance?.customer?.id}">${ridTransactionInstance?.customer?.encodeAsHTML()}</g:link></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridTransactionInstance?.dateOfConsultation}">
                    <li class="fieldcontain">
                        <span id="dateOfConsultation-label" class="property-label"><g:message code="ridTransaction.dateOfConsultation.label" default="Date Of Consultation" /></span>
                        
                            <span class="property-value" aria-labelledby="dateOfConsultation-label"><g:formatDate date="${ridTransactionInstance?.dateOfConsultation}" /></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridTransactionInstance?.departmentalAffilication}">
                    <li class="fieldcontain">
                        <span id="departmentalAffilication-label" class="property-label"><g:message code="ridTransaction.departmentalAffilication.label" default="Departmental Affilication" /></span>
                        
                            <span class="property-value" aria-labelledby="departmentalAffilication-label"><g:link controller="ridDepartmentalAffiliation" action="show" id="${ridTransactionInstance?.departmentalAffilication?.id}">${ridTransactionInstance?.departmentalAffilication?.encodeAsHTML()}</g:link></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridTransactionInstance?.entityAffiliation}">
                    <li class="fieldcontain">
                        <span id="entityAffiliation-label" class="property-label"><g:message code="ridTransaction.entityAffiliation.label" default="Entity Affiliation" /></span>
                        
                            <span class="property-value" aria-labelledby="entityAffiliation-label"><g:link controller="ridEntityAffiliation" action="show" id="${ridTransactionInstance?.entityAffiliation?.id}">${ridTransactionInstance?.entityAffiliation?.encodeAsHTML()}</g:link></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridTransactionInstance?.modeOfConsutlation}">
                    <li class="fieldcontain">
                        <span id="modeOfConsutlation-label" class="property-label"><g:message code="ridTransaction.modeOfConsutlation.label" default="Mode Of Consutlation" /></span>
                        
                            <span class="property-value" aria-labelledby="modeOfConsutlation-label"><g:link controller="ridModeOfConsutlation" action="show" id="${ridTransactionInstance?.modeOfConsutlation?.id}">${ridTransactionInstance?.modeOfConsutlation?.encodeAsHTML()}</g:link></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridTransactionInstance?.productConnected}">
                    <li class="fieldcontain">
                        <span id="productConnected-label" class="property-label"><g:message code="ridTransaction.productConnected.label" default="Product Connected" /></span>
                        
                            <span class="property-value" aria-labelledby="productConnected-label"><g:link controller="ridProductConnected" action="show" id="${ridTransactionInstance?.productConnected?.id}">${ridTransactionInstance?.productConnected?.encodeAsHTML()}</g:link></span>
                        
                    </li>
                    </g:if>
                
                    <g:if test="${ridTransactionInstance?.serviceProvided}">
                    <li class="fieldcontain">
                        <span id="serviceProvided-label" class="property-label"><g:message code="ridTransaction.serviceProvided.label" default="Service Provided" /></span>
                        
                            <span class="property-value" aria-labelledby="serviceProvided-label"><g:link controller="ridServiceProvided" action="show" id="${ridTransactionInstance?.serviceProvided?.id}">${ridTransactionInstance?.serviceProvided?.encodeAsHTML()}</g:link></span>
                        
                    </li>
                    </g:if>
                
                </ol>
                <g:form>
                    <fieldset class="buttons">
                        <g:hiddenField name="id" value="${ridTransactionInstance?.id}" />
                        <g:link class="edit" action="edit" id="${ridTransactionInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </fieldset>
                </g:form>
            </div>
        </div>
	</body>
</html>
