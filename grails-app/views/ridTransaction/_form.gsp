<%@ page import="metridoc.rid.RidTransaction" %>



<div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'customerQuestion', 'error')} ">
	<label for="customerQuestion">
		<g:message code="ridTransaction.customerQuestion.label" default="Customer Question" />
		
	</label>
	<g:textArea name="customerQuestion" cols="40" rows="5" maxlength="500" value="${ridTransactionInstance?.customerQuestion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'interactTimes', 'error')} required">
	<label for="interactTimes">
		<g:message code="ridTransaction.interactTimes.label" default="Interact Times" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="interactTimes" type="number" max="50" value="${ridTransactionInstance.interactTimes}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'followUpContact', 'error')} ">
	<label for="followUpContact">
		<g:message code="ridTransaction.followUpContact.label" default="Follow Up Contact" />
		
	</label>
	<g:textField name="followUpContact" maxlength="50" value="${ridTransactionInstance?.followUpContact}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'prepTime', 'error')} required">
	<label for="prepTime">
		<g:message code="ridTransaction.prepTime.label" default="Prep Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="prepTime" type="number" value="${ridTransactionInstance.prepTime}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'eventLength', 'error')} required">
	<label for="eventLength">
		<g:message code="ridTransaction.eventLength.label" default="Event Length" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="eventLength" type="number" value="${ridTransactionInstance.eventLength}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'notes', 'error')} ">
	<label for="notes">
		<g:message code="ridTransaction.notes.label" default="Notes" />
		
	</label>
	<g:textField name="notes" maxlength="200" value="${ridTransactionInstance?.notes}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'facultySponsor', 'error')} ">
	<label for="facultySponsor">
		<g:message code="ridTransaction.facultySponsor.label" default="Faculty Sponsor" />
		
	</label>
	<g:textArea name="facultySponsor" cols="40" rows="5" maxlength="300" value="${ridTransactionInstance?.facultySponsor}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'courseName', 'error')} ">
	<label for="courseName">
		<g:message code="ridTransaction.courseName.label" default="Course Name" />
		
	</label>
	<g:textArea name="courseName" cols="40" rows="5" maxlength="300" value="${ridTransactionInstance?.courseName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'courseNumber', 'error')} ">
	<label for="courseNumber">
		<g:message code="ridTransaction.courseNumber.label" default="Course Number" />
		
	</label>
	<g:textArea name="courseNumber" cols="40" rows="5" maxlength="300" value="${ridTransactionInstance?.courseNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'librarian', 'error')} ">
	<label for="librarian">
		<g:message code="ridTransaction.librarian.label" default="Librarian" />
		
	</label>
	<g:textField name="librarian" maxlength="40" value="${ridTransactionInstance?.librarian}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'patronEmail', 'error')} ">
	<label for="patronEmail">
		<g:message code="ridTransaction.patronEmail.label" default="Patron Email" />
		
	</label>
	<g:field type="email" name="patronEmail" maxlength="40" value="${ridTransactionInstance?.patronEmail}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'consutlationMode', 'error')} required">
	<label for="consutlationMode">
		<g:message code="ridTransaction.consutlationMode.label" default="Consutlation Mode" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="consutlationMode" name="consutlationMode.id" from="${metridoc.rid.RidConsutlationMode.list()}" optionKey="id" required="" value="${ridTransactionInstance?.consutlationMode?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'courseSponsor', 'error')} required">
	<label for="courseSponsor">
		<g:message code="ridTransaction.courseSponsor.label" default="Course Sponsor" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="courseSponsor" name="courseSponsor.id" from="${metridoc.rid.RidCourseSponsor.list()}" optionKey="id" required="" value="${ridTransactionInstance?.courseSponsor?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'customer', 'error')} required">
	<label for="customer">
		<g:message code="ridTransaction.customer.label" default="Customer" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="customer" name="customer.id" from="${metridoc.rid.RidCustomer.findAllByInForm(1)}" optionKey="id" required="" value="${ridTransactionInstance?.customer?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'departmentalAffilication', 'error')} required">
	<label for="departmentalAffilication">
		<g:message code="ridTransaction.departmentalAffilication.label" default="Departmental Affilication" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="departmentalAffilication" name="departmentalAffilication.id" from="${metridoc.rid.RidDepartmentalAffiliation.list()}" optionKey="id" required="" value="${ridTransactionInstance?.departmentalAffilication?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'entityAffiliation', 'error')} required">
	<label for="entityAffiliation">
		<g:message code="ridTransaction.entityAffiliation.label" default="Entity Affiliation" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="entityAffiliation" name="entityAffiliation.id" from="${metridoc.rid.RidEntityAffiliation.list()}" optionKey="id" required="" value="${ridTransactionInstance?.entityAffiliation?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'productConnected', 'error')} required">
	<label for="productConnected">
		<g:message code="ridTransaction.productConnected.label" default="Product Connected" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="productConnected" name="productConnected.id" from="${metridoc.rid.RidProductConnected.list()}" optionKey="id" required="" value="${ridTransactionInstance?.productConnected?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'serviceProvided', 'error')} required">
	<label for="serviceProvided">
		<g:message code="ridTransaction.serviceProvided.label" default="Service Provided" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="serviceProvided" name="serviceProvided.id" from="${metridoc.rid.RidServiceProvided.list()}" optionKey="id" required="" value="${ridTransactionInstance?.serviceProvided?.id}" class="many-to-one"/>
</div>

