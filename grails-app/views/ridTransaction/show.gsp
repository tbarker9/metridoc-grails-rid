<%@ page import="metridoc.rid.RidTransaction" %>
<g:set var="entityName" value="${message(code: 'ridTransaction.label', default: 'RidTransaction')}"/>

<md:report>
<r:external dir="css" file="ridtrans.css" plugin="metridoc-rid"/>
<div class="md-application-content">
<tmpl:toggle/>
<tmpl:tabs/>
<g:if test="${session.transType == "consultation"}">
<div id="show-ridTransaction" class="content scaffold-show" role="main">
<h1><g:message code="default.show.label" args="[entityName]"/></h1>
%{--<g:if test="${flash.message}">--}%
%{--<div class="message" role="status">${flash.message}</div>--}%
%{--</g:if>--}%
<br/>

<table border="0"
       style="font-size: 12px; border-top: 0px; width: 90%; table-layout: fixed; word-wrap: break-word"
       align="center">
<tr align="center">
    <td colspan="2">
        <span id="ridLibraryUnit-label" class="property-label"><g:message
                code="ridTransaction.ridLibraryUnit.label" default="Library Unit"/></span>
        :
        <span class="property-value" aria-labelledby="ridLibraryUnit-label"><g:fieldValue
                bean="${ridTransactionInstance}" field="ridLibraryUnit"/></span>
    </td>
    <td colspan="2">
        <span id="dateOfConsultation-label" class="property-label"><g:message
                code="ridTransaction.dateOfConsultation.label" default="Date Of Consultation"/></span>
        :
        <span class="property-value" aria-labelledby="dateOfConsultation-label"><g:formatDate
                format="yyyy-MM-dd" date="${ridTransactionInstance?.dateOfConsultation}"/></span>
    </td>
    <td colspan="2">
        <span id="staffPennkey-label" class="property-label"><g:message
                code="ridTransaction.staffPennkey.label" default="Staff Pennkey"/></span>
        :
        <span class="property-value" aria-labelledby="staffPennkey-label"><g:fieldValue
                bean="${ridTransactionInstance}" field="staffPennkey"/></span>
    </td>
</tr>

<tr align="center">
    <td>
        <span id="userName-label" class="property-label"><g:message code="ridTransaction.userName.label"
                                                                    default="User Name"/></span>
        :<br/>
        <span class="property-value" aria-labelledby="userName-label"><g:fieldValue
                bean="${ridTransactionInstance}" field="userName"/></span>
    </td>
    <td>
        <span id="rank-label" class="property-label"><g:message code="ridTransaction.rank.label"
                                                                default="Rank"/></span>
        :<br/>
        <span class="property-value" aria-labelledby="rank-label"><g:fieldValue
                bean="${ridTransactionInstance}" field="rank"/></span>
    </td>
    <td>
        <span id="school-label" class="property-label"><g:message
                code="ridTransaction.school.label" default="School"/></span>
        :<br/>
        <span class="property-value" aria-labelledby="school-label"><g:fieldValue
                bean="${ridTransactionInstance}" field="school"/></span>
    </td>
    %{--<td>--}%
    %{--<span id="patronEmail-label" class="property-label"><g:message code="ridTransaction.patronEmail.label" default="Patron Email" /></span>--}%
    %{--:<br/>--}%
    %{--<span class="property-value" aria-labelledby="patronEmail-label"><g:fieldValue bean="${ridTransactionInstance}" field="patronEmail"/></span>--}%
    %{--</td>--}%
    <td colspan="2">
        <span id="interactOccurrences-label" class="property-label"><g:message
                code="ridTransaction.interactOccurrences.label" default="Interact Occurrences"/></span>
        :<br/>
        <span class="property-value" aria-labelledby="interactOccurrences-label"><g:fieldValue
                bean="${ridTransactionInstance}" field="interactOccurrences"/></span>
    </td>
</tr>

<tr align="center">
    <td>
        <span id="modeOfConsultation-label" class="property-label"><g:message
                code="ridTransaction.modeOfConsultation.label" default="Consultation Mode"/></span>
        :<br/>
        <span class="property-value" aria-labelledby="modeOfConsultation-label"><g:fieldValue
                bean="${ridTransactionInstance}" field="modeOfConsultation"/></span>
    </td>
    <td>
        <span id="serviceProvided-label" class="property-label"><g:message
                code="ridTransaction.serviceProvided.label" default="Service Provided"/></span>
        :<br/>
        <span class="property-value" aria-labelledby="serviceProvided-label"><g:fieldValue
                bean="${ridTransactionInstance}" field="serviceProvided"/></span>
    </td>
    <td>
        <span id="userGoal-label" class="property-label"><g:message code="ridTransaction.userGoal.label"
                                                                    default="User Goal"/></span>
        :<br/>
        <span class="property-value" aria-labelledby="userGoal-label"><g:fieldValue
                bean="${ridTransactionInstance}" field="userGoal"/></span>
    </td>
    <td>
        <span id="prepTime-label" class="property-label"><g:message code="ridTransaction.prepTime.label"
                                                                    default="Prep Time (min)"/></span>
        :<br/>
        <span class="property-value" aria-labelledby="prepTime-label"><g:fieldValue
                bean="${ridTransactionInstance}" field="prepTime"/></span>
    </td>
    <td>
        <span id="eventLength-label" class="property-label"><g:message
                code="ridTransaction.eventLength.label" default="Event Length (min)"/></span>
        :<br/>
        <span class="property-value" aria-labelledby="eventLength-label"><g:fieldValue
                bean="${ridTransactionInstance}" field="eventLength"/></span>
    </td>
</tr>

<tr align="center">
    <td>
        <span id="courseName-label" class="property-label"><g:message
                code="ridTransaction.courseName.label" default="Course Name"/></span>
        :<br/>
        <span class="property-value" aria-labelledby="courseName-label"><g:fieldValue
                bean="${ridTransactionInstance}" field="courseName"/></span>
    </td>
    <td>
        <span id="courseSponsor-label" class="property-label"><g:message
                code="ridTransaction.courseSponsor.label" default="Course Sponsor"/></span>
        :<br/>
        <span class="property-value" aria-labelledby="courseSponsor-label"><g:fieldValue
                bean="${ridTransactionInstance}" field="courseSponsor"/></span>
    </td>
    <td>
        <span id="department-label" class="property-label"><g:message
                code="ridTransaction.department.label"
                default="Department"/></span>
        :<br/>
        %{--<span class="property-value" aria-labelledby="department-label"><g:link controller="ridDepartmentalAffiliation" action="show" id="${ridTransactionInstance?.department?.id}">${ridTransactionInstance?.department?.encodeAsHTML()}</g:link></span>--}%
        <span class="property-value" aria-labelledby="department-label"><g:fieldValue
                bean="${ridTransactionInstance}" field="department"/></span>
    </td>
    <td>
        <span id="courseNumber-label" class="property-label"><g:message
                code="ridTransaction.courseNumber.label" default="Course Number"/></span>
        :<br/>
        <span class="property-value" aria-labelledby="courseNumber-label"><g:fieldValue
                bean="${ridTransactionInstance}" field="courseNumber"/></span>
    </td>
    %{--<td>--}%
    %{--<span id="librarian-label" class="property-label"><g:message code="ridTransaction.librarian.label" default="Librarian" /></span>--}%
    %{--:<br/>--}%
    %{--<span class="property-value" aria-labelledby="librarian-label"><g:fieldValue bean="${ridTransactionInstance}" field="librarian"/></span>--}%
    %{--</td>--}%
    <td>
        <span id="facultySponsor-label" class="property-label"><g:message
                code="ridTransaction.facultySponsor.label" default="Faculty Sponsor"/></span>
        :<br/>
        <span class="property-value" aria-labelledby="facultySponsor-label"><g:fieldValue
                bean="${ridTransactionInstance}" field="facultySponsor"/></span>
    </td>
</tr>
<tr align="center">
    <td colspan="2">
        <span id="userQuestion-label" class="property-label"><g:message
                code="ridTransaction.userQuestion.label" default="User Question"/></span>
        :<br/>
        <span class="property-value" aria-labelledby="userQuestion-label"><g:fieldValue
                bean="${ridTransactionInstance}" field="userQuestion"/></span>
    </td>
    <td>
        <span id="notes-label" class="property-label"><g:message code="ridTransaction.notes.label"
                                                                 default="Notes"/></span>
        :<br/>
        <span class="property-value" aria-labelledby="notes-label"><g:fieldValue
                bean="${ridTransactionInstance}" field="notes"/></span>
    </td>
</tr>


%{--<g:if test="${ridTransactionInstance?.followUpContact}">--}%
%{--<li class="fieldcontain">--}%
%{--<span id="followUpContact-label" class="property-label"><g:message code="ridTransaction.followUpContact.label" default="Follow Up Contact" /></span>--}%
%{----}%
%{--<span class="property-value" aria-labelledby="followUpContact-label"><g:fieldValue bean="${ridTransactionInstance}" field="followUpContact"/></span>--}%
%{----}%
%{--</li>--}%
%{--</g:if>--}%

%{--<g:if test="${ridTransactionInstance?.courseSponsor}">--}%
%{--<li class="fieldcontain">--}%
%{--<span id="courseSponsor-label" class="property-label"><g:message code="ridTransaction.courseSponsor.label" default="Course Sponsor" /></span>--}%
%{----}%
%{--<span class="property-value" aria-labelledby="courseSponsor-label"><g:link controller="ridCourseSponsor" action="show" id="${ridTransactionInstance?.courseSponsor?.id}">${ridTransactionInstance?.courseSponsor?.encodeAsHTML()}</g:link></span>--}%
%{----}%
%{--</li>--}%
%{--</g:if>--}%

%{--<g:if test="${ridTransactionInstance?.otherSchool}">--}%
%{--<li class="fieldcontain">--}%
%{--<span id="otherSchool-label" class="property-label"><g:message--}%
%{--code="ridTransaction.otherSchool.label"--}%
%{--default="Other User Affiliation"/></span>--}%

%{--<span class="property-value" aria-labelledby="otherSchool-label"><g:fieldValue--}%
%{--bean="${ridTransactionInstance}" field="otherSchool"/></span>--}%

%{--</li>--}%
%{--</g:if>--}%

%{--<g:if test="${ridTransactionInstance?.otherRank}">--}%
%{--<li class="fieldcontain">--}%
%{--<span id="otherRank-label" class="property-label"><g:message--}%
%{--code="ridTransaction.otherRank.label" default="Other Rank"/></span>--}%

%{--<span class="property-value" aria-labelledby="otherRank-label"><g:fieldValue--}%
%{--bean="${ridTransactionInstance}" field="otherRank"/></span>--}%

%{--</li>--}%
%{--</g:if>--}%

%{--<g:if test="${ridTransactionInstance?.otherCourseSponsor}">--}%
%{--<li class="fieldcontain">--}%
%{--<span id="otherCourseSponsor-label" class="property-label"><g:message--}%
%{--code="ridTransaction.otherCourseSponsor.label" default="Other Course Sponsor"/></span>--}%

%{--<span class="property-value" aria-labelledby="otherCourseSponsor-label"><g:fieldValue--}%
%{--bean="${ridTransactionInstance}" field="otherCourseSponsor"/></span>--}%

%{--</li>--}%
%{--</g:if>--}%

%{--<g:if test="${ridTransactionInstance?.otherService}">--}%
%{--<li class="fieldcontain">--}%
%{--<span id="otherService-label" class="property-label"><g:message--}%
%{--code="ridTransaction.otherService.label" default="Other Service"/></span>--}%

%{--<span class="property-value" aria-labelledby="otherService-label"><g:fieldValue--}%
%{--bean="${ridTransactionInstance}" field="otherService"/></span>--}%

%{--</li>--}%
%{--</g:if>--}%

</table>

<g:form>
    <fieldset class="buttons">
        <g:hiddenField name="id" value="${ridTransactionInstance?.id}"/>
        <g:actionSubmit class="btn btn-success" action="edit" id="${ridTransactionInstance?.id}"
                        value="${message(code: 'default.button.edit.label', default: 'Edit')}"/>
        <g:actionSubmit class="btn btn-danger" action="delete"
                        value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                        onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
    </fieldset>
</g:form>

</div>
</g:if>
<g:else>Not implemented</g:else>
</div>
</md:report>