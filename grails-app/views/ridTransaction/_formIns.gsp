<%@ page import="metridoc.rid.RidDepartment;metridoc.rid.RidSchool;metridoc.rid.RidLocation;metridoc.rid.RidRank;java.text.SimpleDateFormat;metridoc.rid.RidUserGoal;metridoc.rid.RidLibraryUnit;metridoc.rid.RidLibraryUnit;metridoc.rid.RidDepartment;metridoc.rid.RidCourseSponsor;metridoc.rid.RidConsTransaction;metridoc.rid.RidInsTransaction" %>

<r:external dir="datepicker/css" file="datepicker.css" plugin="metridoc-rid"/>
<r:external dir="datepicker/js" file="bootstrap-datepicker.js" plugin="metridoc-rid"/>
<r:external dir="css" file="ridtrans.css" plugin="metridoc-rid"/>
<r:external dir="js" file="RidTransaction.js" plugin="metridoc-rid"/>


<div class="row-fluid">
    <div class="span3">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'ridLibraryUnit', 'error')}">
            <label for="ridLibraryUnit">
                <g:message code="ridTransaction.ridLibraryUnit.label" default="Library Unit"/>
                <span class="required-indicator">*</span>
            </label>
            <g:select id="ridLibraryUnit" style="width:160px" name="ridLibraryUnit.id"
                      from="${metridoc.rid.RidLibraryUnit.list()}"
                      optionKey="id" required="" value="${ridTransactionInstance?.ridLibraryUnit?.id}"
                      class="many-to-one"/>
        </div>
    </div>

    <div class="span3">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'dateOfInstruction', 'error')} required">
            <label for="dateOfInstruction" style="width: 150px; margin-left: -37px">
                <g:message code="ridTransaction.dateOfInstruction.label" default="Date Of Instruction"/>
                <span class="required-indicator">*</span>
            </label>
            %{--<g:datePicker style="width: 150px" name="dateOfInstruction" precision="day"  --}%
            %{--value="${ridTransactionInstance?.dateOfInstruction}"  />--}%
            <% def dateString = ridTransactionInstance?.dateOfInstruction ? new SimpleDateFormat("MM/dd/yyyy").format(ridTransactionInstance?.dateOfInstruction) : ""; %>
            <input type="text" name="dateOfInstruction" style="width: 150px"
                   value="${dateString}" id="dp1" required=""/>
        </div>
    </div>

    <div class="span3">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'staffPennkey', 'error')} ">
            <label for="staffPennkey">
                <g:message code="ridTransaction.staffPennkey.label" default="Staff Pennkey"/>
                <span class="required-indicator">*</span>
            </label>
            <g:textField style="width:150px" class="userInput" name="staffPennkey" maxlength="100"
                         required="" value="${ridTransactionInstance?.staffPennkey}"/>
        </div>
    </div>
</div>


<div class="row-fluid">
    <div class="span2">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'userName', 'error')} ">
            <label for="userName">
                <g:message code="ridTransaction.user.label" default="User Name"/>
            </label>
            <g:textField class="userInput" name="userName" style="width: 120px" maxlength="100"
                         value="${ridTransactionInstance?.userName}"/>
        </div>
    </div>

    <div class="span2">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'rank', 'error')} required">
            <label for="rank">
                <g:message code="ridTransaction.rank.label" default="Rank"/>
                <span class="required-indicator">*</span>
            </label>
            <%
                rankList = RidRank.findAllByInForm(1)
                if (ridTransactionInstance?.rank?.inForm == 0)
                    rankList.add(0, RidRank.findById(
                            ridTransactionInstance?.rank?.id))
                rankList = rankList.sort { it.name }
                rankList.addAll(RidRank.findAllByInForm(2))
            %>
            <select style="width:120px" id="rank" name="rank.id" required="" class="many-to-one">
                <g:each in="${rankList}">
                    <option value="${it.id}" inForm="${it.inForm}"
                            <g:if test="${ridTransactionInstance?.rank?.id == it.id}">selected=""</g:if>>
                        ${it.name}
                    </option>
                </g:each>
            </select>
        </div>

        <div id="otherRankDiv" style="display:none"
             class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'otherRank', 'error')} ">
            <label for="otherRank">
                <g:message code="ridTransaction.otherRank.label" default="Other Rank"/>
            </label>
            <g:textField class="userInput" name="otherRank" style="width:120px" maxlength="50"
                         value="${ridTransactionInstance?.otherRank}"/>
        </div>
    </div>

    <div class="span2">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'school', 'error')} required">
            <label for="school">
                <g:message code="ridTransaction.school.label" default="School"/>
                <span class="required-indicator">*</span>
            </label>
            <% schoolList = RidSchool.findAllByInForm(1) %>
            <% if (ridTransactionInstance?.school?.inForm == 0)
                schoolList.add(0, RidSchool.findById(
                        ridTransactionInstance?.school?.id))
            schoolList = schoolList.sort { it.name }
            %>
            <% schoolList.addAll(RidSchool.findAllByInForm(2)) %>
            <select style="width:120px" id="school" name="school.id" required="" class="many-to-one">
                <g:each in="${schoolList}">
                    <option value="${it.id}" inForm="${it.inForm}"
                            <g:if test="${ridTransactionInstance?.school?.id == it.id}">selected=""</g:if>>
                        ${it.name}
                    </option>
                </g:each>
            </select>
        </div>

        <div id="otherSchoolDiv" style="display:none;"
             class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'otherSchool', 'error')} ">
            <label for="otherSchool">
                <g:message code="ridTransaction.otherSchool.label" default="Other School"/>
            </label>
            <g:textField class="userInput" style="width:120px" name="otherSchool" maxlength="50"
                         value="${ridTransactionInstance?.otherSchool}"/>
        </div>
    </div>


    <div class="span2">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'location', 'error')} required">
            <label for="location">
                <g:message code="ridTransaction.location.label" default="Location"/>
                <span class="required-indicator">*</span>
            </label>
            <% locationList = RidLocation.findAllByInForm(1) %>
            <% if (ridTransactionInstance?.location?.inForm == 0)
                locationList.add(0, RidLocation.findById(
                        ridTransactionInstance?.location?.id))
            locationList = locationList.sort { it.name }
            %>
            <% locationList.addAll(RidLocation.findAllByInForm(2)) %>
            <select style="width:120px" id="location" name="location.id" required="" class="many-to-one">
                <g:each in="${locationList}">
                    <option value="${it.id}" inForm="${it.inForm}"
                            <g:if test="${ridTransactionInstance?.location?.id == it.id}">selected=""</g:if>>
                        ${it.name}
                    </option>
                </g:each>
            </select>
        </div>

        <div id="otherLocationDiv" style="display:none;"
             class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'otherLocation', 'error')} ">
            <label for="otherLocation">
                <g:message code="ridTransaction.otherLocation.label" default="Other Location"/>
            </label>
            <g:textField class="userInput" style="width:120px" name="otherLocation" maxlength="50"
                         value="${ridTransactionInstance?.otherLocation}"/>
        </div>
    </div>

</div>

<div class="row-fluid">

    <div class="span2">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'prepTime', 'error')} required">
            <label for="prepTime">
                <g:message code="ridTransaction.prepTime.label" default="Prep Time (minutes)"/>
                <span class="required-indicator">*</span>
            </label>
            <g:field class="userInput" style="width:120px"
                     name="prepTime" type="number" value="${ridTransactionInstance.prepTime}" required=""/>
        </div>
    </div>

    <div class="span2">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'eventLength', 'error')} required">
            <label for="eventLength">
                <g:message code="ridTransaction.eventLength.label" default="Event Length (minutes)"/>
                <span class="required-indicator">*</span>
            </label>
            <g:field style="width:120px" class="userInput" name="eventLength" type="number"
                     value="${ridTransactionInstance.eventLength}" required=""/>
        </div>
    </div>

    <div class="span2">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'attendanceTotal', 'error')} required">
            <label for="attendanceTotal">
                <g:message code="ridTransaction.attendanceTotal.label" default="Total Attendance"/>
                <span class="required-indicator">*</span>
            </label>
            <g:field style="width:120px" class="userInput" name="attendanceTotal" type="number" max="50"
                     value="${ridTransactionInstance.attendanceTotal}" required=""/>
        </div>
    </div>
</div>

<div class="row-fluid">
    <div class="span2">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'courseName', 'error')} ">
            <label for="courseName">
                <g:message code="ridTransaction.courseName.label" default="Course Name"/>
            </label>
            <g:textField class="userInput" name="courseName" style="width: 120px" maxlength="100"
                         value="${ridTransactionInstance?.courseName}"/>
        </div>
    </div>


    <div class="span2">
        <div class="fieldcontain ${hasErrors(bea: ridTransactionInstance, field: 'department', 'error')}">
            <label for="department">
                <g:message code="ridTransaction.department.label" default="Department"/>
                <a style="font-size: 14px" data-toggle="modal"
                   href="../ridDepartment/departmentList" data-target="#myDepartment">
                    <i class="icon-file-alt"></i>
                </a>
            </label>
            <g:select style="width:120px" id="department" name="department.id"
                      from="${RidDepartment.list().sort { it.name }}" optionKey="id"
                      value="${ridTransactionInstance?.department?.id}" class="many-to-one"/>
        </div>
    </div>

    <div class="span2">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'courseNumber', 'error')} ">
            <label for="courseNumber">
                <g:message code="ridTransaction.courseNumber.label" default="Course Number"/>
            </label>
            <g:textField class="userInput" name="courseNumber" style="width: 120px" maxlength="100"
                         value="${ridTransactionInstance?.courseNumber}"/>
        </div>
    </div>

    <div class="span2">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'facultySponsor', 'error')} ">
            <label for="facultySponsor">
                <g:message code="ridTransaction.facultySponsor.label" default="Faculty Sponsor"/>
            </label>
            <g:textField class="userInput" name="facultySponsor" style="width: 120px" maxlength="300"
                         value="${ridTransactionInstance?.facultySponsor}"/>
        </div>
    </div>
</div>

<div class="row-fluid">
    <div class="span2">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'coInstructor', 'error')} ">
            <label for="coInstructor">
                <g:message code="ridTransaction.coInstructor.label" default="Co-instructor"/>
            </label>
            <g:textField class="userInput" name="coInstructor" style="width: 120px" maxlength="100"
                         value="${ridTransactionInstance?.coInstructor}"/>
        </div>
    </div>

    <div class="span2">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'instructionalMaterials', 'error')} ">
            <label for="instructionalMaterials">
                <g:message code="ridTransaction.instructionalMaterials.label" default="Instructional Materials"/>
            </label>
            <g:textField class="userInput" name="instructionalMaterials" style="width: 120px" maxlength="300"
                         value="${ridTransactionInstance?.instructionalMaterials}"/>
        </div>
    </div>
</div>

<div class="row-fluid">

    <div class="span4">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'sessionDescription', 'error')} ">
            <label for="sessionDescription">
                <g:message code="ridTransaction.sessionDescription.label" default="Session Description"/>
            </label>
            <br/>
            <g:textArea class="userInput" name="sessionDescription" cols="40" rows="5" maxlength="500"
                        value="${ridTransactionInstance?.sessionDescription}" onkeydown="isFull(this)"/>
        </div>
    </div>

    <div class="span4">
        <div class="fieldcontain ${hasErrors(bean: ridTransactionInstance, field: 'notes', 'error')} ">
            <label for="notes">
                <g:message code="ridTransaction.notes.label" default="Notes"/>
            </label>
            <br/>
            <g:textArea class="userInput" id="notes" name="notes" cols="40" rows="5" maxlength="500"
                        value="${ridTransactionInstance?.notes}" onkeydown="isFull(this)"/>

        </div>
    </div>

</div>



