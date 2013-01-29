<%@ page import="metridoc.rid.RidTransaction" %>

<r:external dir="css" file="ridtrans.css" plugin="metridoc-rid"/>
<r:external dir="js" file="RidTransaction.js" plugin="metridoc-rid"/>

<table border="0" style="font-size: 12px; border-top: 0px">
    <tr align="center">
        <td colspan="3">
            <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'dateOfConsultation', 'error')} required">
                <label for="dateOfConsultation">
                    <g:message code="ridTransaction.myDate.label" default="Date Of Consultation" />
                    <span class="required-indicator">*</span>
                </label>
                <g:datePicker name="dateOfConsultation" precision="day"  value="${ridTransactionInstance?.dateOfConsultation}"  />
            </div>
        </td>
        <td colspan="2">
            <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'staffPennkey', 'error')} ">
                <label for="staffPennkey">
                    <g:message code="ridTransaction.staffPennkey.label" default="Staff Pennkey"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:textField style="width:150px" class="userInput" name="staffPennkey" maxlength="100"
                            value="${ridTransactionInstance?.staffPennkey}"/>
            </div>
        </td>
    </tr>
    <tr align="center">
        <td>
            <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'modeOfConsutlation', 'error')} required">
                <label for="modeOfConsutlation">
                    <g:message code="ridTransaction.modeOfConsutlation.label" default="Mode Of Consutlation"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:select style="width:100px" id="modeOfConsutlation" name="modeOfConsutlation.id" from="${metridoc.rid.RidModeOfConsutlation.list()}"
                          optionKey="id" required="" value="${ridTransactionInstance?.modeOfConsutlation?.id}" class="many-to-one"/>
            </div>
        </td>
        <td>
            <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'productConnected', 'error')} required">
                <label for="productConnected">
                    <g:message code="ridTransaction.productConnected.label" default="Product Connected"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:select id="productConnected" style="width:100px" name="productConnected.id" from="${metridoc.rid.RidProductConnected.list()}"
                          optionKey="id" required="" value="${ridTransactionInstance?.productConnected?.id}" class="many-to-one"/>
            </div>
        </td>
        <td>
            <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'serviceProvided', 'error')} required">
                <label for="serviceProvided">
                    <g:message code="ridTransaction.serviceProvided.label" default="Service Provided"/>
                    <span class="required-indicator">*</span>
                </label>
                <% serviceList = metridoc.rid.RidServiceProvided.findAllByInForm(1) %>
                <% if (ridTransactionInstance?.serviceProvided?.inForm == 0)
                    serviceList.addAll(metridoc.rid.RidServiceProvided.findAllById(
                            ridTransactionInstance?.serviceProvided?.id)) %>
                <% serviceList.addAll(metridoc.rid.RidServiceProvided.findAllByInForm(2)) %>
                <g:select id="serviceProvided" style="width:100px" name="serviceProvided.id" from="${serviceList}"
                          optionKey="id" required="" value="${ridTransactionInstance?.serviceProvided?.id}" class="many-to-one"/>
            </div>
            <div id="otherServiceDiv" style="display:none;" class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'otherService', 'error')} ">
                <label for="otherService">
                    <g:message code="ridTransaction.otherService.label" default="Other Service"/>
                </label>
                <g:textField class="userInput" name="otherService" maxlength="100" value="${ridTransactionInstance?.otherService}"/>
            </div>

        </td>
        <td>
            <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'prepTime', 'error')} required">
                <label for="prepTime">
                    <g:message code="ridTransaction.prepTime.label" default="Prep Time"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:field class="userInput" style="width:100px"
                         name="prepTime" type="number" value="${ridTransactionInstance.prepTime}" required=""/>
            </div>
        </td>
        <td>
            <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'eventLength', 'error')} required">
                <label for="eventLength">
                    <g:message code="ridTransaction.eventLength.label" default="Event Length"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:field style="width:100px" class="userInput" name="eventLength" type="number" value="${ridTransactionInstance.eventLength}" required=""/>
            </div>
        </td>
    </tr>
    <tr align="center">
        <td>
            <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'customer', 'error')} required">
                <label for="customer">
                    <g:message code="ridTransaction.customer.label" default="Customer"/>
                    <span class="required-indicator">*</span>
                </label>
                <% customerList = metridoc.rid.RidCustomer.findAllByInForm(1) %>
                <% if (ridTransactionInstance?.customer?.inForm == 0)
                    customerList.addAll(metridoc.rid.RidCustomer.findAllById(
                            ridTransactionInstance?.customer?.id)) %>
                <% customerList.addAll(metridoc.rid.RidCustomer.findAllByInForm(2)) %>
                <g:select style="width:100px" id="customer" name="customer.id"
                          from="${customerList}"
                          optionKey="id" required=""
                          value="${ridTransactionInstance?.customer?.id}" class="many-to-one"/>
            </div>
            <div id="otherCustomerDiv" style="display:none;" class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'otherCustomer', 'error')} ">
                <label for="otherCustomer">
                    <g:message code="ridTransaction.otherCustomer.label" default="Other Customer"/>
                </label>
                <g:textField class="userInput" name="otherCustomer" maxlength="50" value="${ridTransactionInstance?.otherCustomer}"/>
            </div>
        </td>
        <td>
            <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'entityAffiliation', 'error')} required">
                <label for="entityAffiliation">
                    <g:message code="ridTransaction.entityAffiliation.label" default="Entity Affiliation"/>
                    <span class="required-indicator">*</span>
                </label>
                <% entityAffiliationList = metridoc.rid.RidEntityAffiliation.findAllByInForm(1) %>
                <% if (ridTransactionInstance?.entityAffiliation?.inForm == 0)
                    entityAffiliationList.addAll(metridoc.rid.RidEntityAffiliation.findAllById(
                            ridTransactionInstance?.entityAffiliation?.id)) %>
                <% entityAffiliationList.addAll(metridoc.rid.RidEntityAffiliation.findAllByInForm(2)) %>
                <g:select style="width:100px" id="entityAffiliation" name="entityAffiliation.id" from="${entityAffiliationList}"
                          optionKey="id" required="" value="${ridTransactionInstance?.entityAffiliation?.id}" class="many-to-one"/>
            </div>
            <div id="otherEntityAffiliationDiv" style="display:none;" class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'otherEntityAffiliation', 'error')} ">
                <label for="otherEntityAffiliation">
                    <g:message code="ridTransaction.otherEntityAffiliation.label" default="Other Entity Affiliation"/>
                </label>
                <g:textField class="userInput" name="otherEntityAffiliation" maxlength="50" value="${ridTransactionInstance?.otherEntityAffiliation}"/>
            </div>
        </td>
        <td>
            <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'patronEmail', 'error')} ">
                <label for="patronEmail">
                    <g:message code="ridTransaction.patronEmail.label" default="Patron Email"/>

                </label>
                <g:field style="width:100px" class="userInput" type="email" name="patronEmail" maxlength="100" value="${ridTransactionInstance?.patronEmail}"/>
            </div>
        </td>
        <td>
            <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'interactTimes', 'error')} required">
                <label for="interactTimes">
                    <g:message code="ridTransaction.interactTimes.label" default="Interact Times"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:field style="width:100px" class="userInput" name="interactTimes" type="number" max="50" value="${ridTransactionInstance.interactTimes}" required=""/>
            </div>
        </td>
    </tr>
    <tr align="center">
        <td>
            <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'courseName', 'error')} ">
                <label for="courseName">
                    <g:message code="ridTransaction.courseName.label" default="Course Name"/>
                </label>
                <g:textField class="userInput" name="courseName" maxlength="100" value="${ridTransactionInstance?.courseName}"/>
            </div>
        </td>
        <td>
            <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'departmentalAffilication', 'error')} required">
                <label for="departmentalAffilication">
                    <g:message code="ridTransaction.departmentalAffilication.label" default="Departmental Affilication"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:select style="width:100px" id="departmentalAffilication" name="departmentalAffilication.id"
                          from="${metridoc.rid.RidDepartmentalAffiliation.list()}" optionKey="id" required=""
                          value="${ridTransactionInstance?.departmentalAffilication?.id}" class="many-to-one"/>
            </div>
        </td>
        <td>
            <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'courseNumber', 'error')} ">
                <label for="courseNumber">
                    <g:message code="ridTransaction.courseNumber.label" default="Course Number"/>
                </label>
                <g:textField class="userInput" name="courseNumber" maxlength="100" value="${ridTransactionInstance?.courseNumber}"/>
            </div>
        </td>
        <td>
            <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'librarian', 'error')} ">
                <label for="librarian">
                    <g:message code="ridTransaction.librarian.label" default="Librarian"/>
                </label>
                <g:textField class="userInput" name="librarian" maxlength="100" value="${ridTransactionInstance?.librarian}"/>
            </div>
        </td>
        <td>
            <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'courseSponsor', 'error')} required">
                <label for="courseSponsor">
                    <g:message code="ridTransaction.courseSponsor.label" default="Course Sponsor"/>
                    <span class="required-indicator">*</span>
                </label>
                <% courseSponsorList = metridoc.rid.RidCourseSponsor.findAllByInForm(1) %>
                <% if (ridTransactionInstance?.courseSponsor?.inForm == 0)
                    courseSponsorList.addAll(metridoc.rid.RidCourseSponsor.findAllById(
                            ridTransactionInstance?.courseSponsorn?.id)) %>
                <% courseSponsorList.addAll(metridoc.rid.RidCourseSponsor.findAllByInForm(2)) %>
                <g:select style="width:100px" id="courseSponsor" name="courseSponsor.id" from="${courseSponsorList}" optionKey="id"
                          required="" value="${ridTransactionInstance?.courseSponsor?.id}" class="many-to-one"/>
            </div>
            <div id="otherCourseSponsorDiv" style="display:none;" class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'otherCourseSponsor', 'error')} ">
                <label for="otherCourseSponsor">
                    <g:message code="ridTransaction.otherCourseSponsor.label" default="Other Course Sponsor"/>
                </label>
                <g:textField class="userInput" name="otherCourseSponsor" maxlength="50" value="${ridTransactionInstance?.otherCourseSponsor}"/>
            </div>
        </td>
    </tr>
    <tr align="center">
        <td colspan="2">
            <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'customerQuestion', 'error')} ">
                <label for="customerQuestion">
                    <g:message code="ridTransaction.customerQuestion.label" default="Customer Question"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:textArea class="userInput" name="customerQuestion" cols="40" rows="5" maxlength="500"
                            value="${ridTransactionInstance?.customerQuestion}"/>
            </div>
        </td>
        <td colspan="2">
            <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'notes', 'error')} ">
                <label for="notes">
                    <g:message code="ridTransaction.notes.label" default="Notes"/>
                </label>
                <br/>
                <g:textArea class="userInput" name="notes" cols="40" rows="5" maxlength="500" value="${ridTransactionInstance?.notes}"/>
            </div>
        </td>
    </tr>
</table>


%{--<div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'followUpContact', 'error')} ">--}%
    %{--<label for="followUpContact">--}%
        %{--<g:message code="ridTransaction.followUpContact.label" default="Follow Up Contact"/>--}%

    %{--</label>--}%
    %{--<g:textField class="userInput" name="followUpContact" maxlength="50" value="${ridTransactionInstance?.followUpContact}"/>--}%
%{--</div>--}%


%{--<div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'facultySponsor', 'error')} ">--}%
    %{--<label for="facultySponsor">--}%
        %{--<g:message code="ridTransaction.facultySponsor.label" default="Faculty Sponsor"/>--}%

    %{--</label>--}%
    %{--<g:textArea class="userInput" name="facultySponsor" cols="40" rows="5" maxlength="300"--}%
                %{--value="${ridTransactionInstance?.facultySponsor}"/>--}%
%{--</div>--}%
