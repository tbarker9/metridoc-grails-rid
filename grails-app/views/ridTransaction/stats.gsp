<%@ page import="org.apache.shiro.SecurityUtils; metridoc.rid.RidConsTransaction; metridoc.rid.RidInsTransaction" %>

<g:if test="${session.transType == "consultation"}">
    <g:set var="entityName" value="${message(code: 'ridTransaction.label', default: 'RidConsTransaction')}"/>
</g:if>
<g:else>
    <g:set var="entityName" value="${message(code: 'ridTransaction.label', default: 'RidInsTransaction')}"/>
</g:else>
<md:report>
<r:external dir="css" file="pagination.css" plugin="metridoc-rid"/>
<r:external dir="css" file="table.css" plugin="metridoc-rid"/>
<r:external dir="js" file="RidStatistics.js" plugin="metridoc-rid"/>
<!--[if !IE]><!-->
<r:external dir="css" file="floating_tables_for_admin_5.css" plugin="metridoc-rid"/>
<!--<![endif]-->


<div class="md-application-content">

<tmpl:toggle/>
<tmpl:tabs/>
<g:if test="${session.transType == "consultation"}">

<g:if test="${statResults.totalTransactions == 0}">
    <p>No transactions have been reported</p>
</g:if>
<g:else>

<div id="stats-ridTransaction" class="content scaffold-list" role="main">
    <h1>
        Transaction Overview

    </h1>

</div>
<div style='overflow:auto; font-size:10pt'>
<input type="button" id="toggleCol" value="Expand to monthly view"> </input>
<table class="table table-striped table-hover" id="Table1">
<thead>
<tr>

    <th>Parameter</th>
    <th>Average</th>
    <th>Total</th>
    <th>Past Year</th>
<g:each in="${statResults.months}">
    <th>${it.getDateString().replaceAll('/1/', '/').padLeft(5, '0')}</th>
</g:each>

</tr>
</thead>
<tbody>
<tr>
    <td>Transactions</td>
    <td>N/A</td>
    <td>${statResults.totalTransactions}</td>
    <td>${statResults.yearTransactions}</td>
    <g:each in="${statResults.monthTransactions}">
        <th>${it}</th>
    </g:each>
</tr>
<tr>
    <td>Interact Occurrences</td>
    <td>${statResults.avgInteractOccurrences}</td>
    <td>${statResults.totalInteractOccurences}</td>
    <td>${statResults.yearInteractOccurences}</td>
    <g:each in="${statResults.monthInteractOccurences}">
        <th>${it}</th>
    </g:each>
</tr>
<tr>
    <td>Prep Time (min)</td>
    <td>${statResults.avgPrepTime}</td>
    <td>${statResults.totalPrepTime}</td>
    <td>${statResults.yearPrepTime}</td>
    <g:each in="${statResults.monthPrepTime}">
        <th>${it}</th>
    </g:each>
</tr>
<tr>
    <td>Event Length (min)</td>
    <td>${statResults.avgEventLength}</td>
    <td>${statResults.totalEventLength}</td>
    <td>${statResults.yearEventLength}</td>
    <g:each in="${statResults.monthEventLength}">
        <th>${it}</th>
    </g:each>
</tr>
</tbody>

</table>

    </div>



<h1 id="aDept">Department Data</h1>
<input type="button" id="allDepts" value="Show all Departments"> </input>
<input type="button" id="toggleCol2" value="Expand to monthly view"> </input>

<div style='overflow:auto; font-size:10pt; height:245px'>
    <table class="table table-striped table-hover" id="Table2">
        <thead>
        <tr>

            <th>Parameter</th>
            <th>Total</th>
            <th>Past Year</th>
            <g:each in="${statResults.months}">
                <th>${it.getDateString().replaceAll('/1/', '/').padLeft(5, '0')}</th>
            </g:each>

        </tr>
        </thead>
        <tbody>

        <g:each var="dept" in="${0..<statResults.topFiveDepartments.size()}">
            <tr>
                <td>${statResults.topFiveDepartments[dept].toString()}</td>
                <td>${statResults.topFiveTotalDepartments[dept]}</td>
                <td>${statResults.topFiveYearDepartments[dept]}</td>
                <g:each var="mon" in="${0..<13}">
                    <td>${statResults.topFiveMonthDepartments[dept][mon]}</td>
                </g:each>
            </tr>

        </g:each>

        <g:each var="dept" in="${0..<statResults.departments.size()}">
            <tr>
                <td>${statResults.departments[dept].toString()}</td>
                <td>${statResults.totalDepartments[dept]}</td>
                <td>${statResults.yearDepartments[dept]}</td>
                <g:each var="mon" in="${0..<13}">
                    <td>${statResults.monthDepartments[dept][mon]}</td>
                </g:each>
            </tr>

        </g:each>

        </tbody>
    </table>

</div>

<h1 id="aCourse">Course Data</h1>
<g:if test="${statResults.courses.size() > 5}">
    <input type="button" id="allCourses" value="Show all Courses"> </input>
</g:if>
<input type="button" id="toggleCol3" value="Expand to monthly view"> </input>

<div style='overflow:auto; font-size:10pt; height:${70 + statResults.courses.size * 35}px'>
    <table class="table table-striped table-hover" id="Table3">
        <thead>
        <tr>

            <th>Parameter</th>
            <th>Total</th>
            <th>Past Year</th>
            <g:each in="${statResults.months}">
                <th>${it.getDateString().replaceAll('/1/', '/').padLeft(5, '0')}</th>
            </g:each>

        </tr>
        </thead>
        <tbody>

        <g:each var="c" in="${0..<statResults.topFiveCourses.size()}">
            <tr>
                <td>${statResults.topFiveCourses[c].toString()}</td>
                <td>${statResults.topFiveTotalCourses[c]}</td>
                <td>${statResults.topFiveYearCourses[c]}</td>
                <g:each var="mon" in="${0..<13}">
                    <td>${statResults.topFiveMonthCourses[c][mon]}</td>
                </g:each>
            </tr>

        </g:each>
        <g:if test="${statResults.courses.size() > 5}">
            <g:each var="c" in="${0..<statResults.courses.size()}">
                <tr>
                    <td>${statResults.courses[c].toString()}</td>
                    <td>${statResults.totalCourses[c]}</td>
                    <td>${statResults.yearCourses[c]}</td>
                    <g:each var="mon" in="${0..<13}">
                        <td>${statResults.monthCourses[c][mon]}</td>
                    </g:each>
                </tr>

            </g:each>
        </g:if>

        </tbody>
    </table>

</div>

<h1 id="aRank">Rank Data</h1>

<input type="button" id="toggleCol4" value="Expand to monthly view"> </input>

<div style='overflow:auto; font-size:10pt; height:245px'>
    <table class="table table-striped table-hover" id="Table4">
        <thead>
        <tr>

            <th>Parameter</th>
            <th>Total</th>
            <th>Past Year</th>
            <g:each in="${statResults.months}">
                <th>${it.getDateString().replaceAll('/1/', '/').padLeft(5, '0')}</th>
            </g:each>

        </tr>
        </thead>
        <tbody>

        <g:if test="${statResults.ranks.size() > 5}">
            <g:each var="c" in="${0..<statResults.ranks.size()}">
                <tr>
                    <td>${statResults.ranks[c].toString()}</td>
                    <td>${statResults.totalRanks[c]}</td>
                    <td>${statResults.yearRanks[c]}</td>
                    <g:each var="mon" in="${0..<13}">
                        <td>${statResults.monthRanks[c][mon]}</td>
                    </g:each>
                </tr>

            </g:each>
        </g:if>

        </tbody>
    </table>

</div>

</g:else>
</g:if>
<g:else>Not Yet Implemented</g:else>
</md:report>

