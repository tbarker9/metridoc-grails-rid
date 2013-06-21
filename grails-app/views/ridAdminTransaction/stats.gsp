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
    <!--[if !IE]><!-->
    <r:external dir="css" file="floating_tables_for_admin_5.css" plugin="metridoc-rid"/>
    <!--<![endif]-->


    <div class="md-application-content">

    <g:render template="/ridAdminTransaction/toggle" plugin="metridoc-rid"/>

    <g:render template="/ridAdminTransaction/tabs" plugin="metridoc-rid"/>
    <g:if test="${session.transType == "consultation"}">

        <g:if test="${statResults.totalTransactions == 0}">
            <p>No transactions have been reported</p>
        </g:if>
        <g:else>
            <p>There have been ${statResults.totalTransactions} total transactions</p>

            <p>There have been ${statResults.yearTransactions} transactions the past year and ${statResults.monthTransactions} transactions in the past month</p>

            <p>The average number of interact occurrences is ${statResults.avgInteractOccurrences}</p>

            <p>The average length of an interact occurrence is ${statResults.avgEventLength}</p>

            <p>The average prep time before consultation is ${statResults.avgPrepTime}</p>

            <p>The PennKey with the most transactions is ${statResults.staffPennkey} with ${statResults.pennkeyMax} transactions</p>

            <g:if test="${statResults.departmentCount == 0}">
                <p>No departments have been specified in transactions</p>
            </g:if>
            <g:else>
                <p>The department that initiated the most transactions is ${statResults.department} with ${statResults.departmentCount}</p>
            </g:else>

            <g:if test="${statResults.courseMax == 0}">
                <p>No course names have been specified in transactions</p>
            </g:if>
            <g:else>
                <p>The course that initiated the most transactions is ${statResults.courseName} with ${statResults.courseMax}</p>
            </g:else>

            <br>

            <div class="row-fluid">
                <div class="span4">
                    <g:if test="${statResults.rank.size() == 0}">
                        <p>No ranks have been specified in transactions</p>
                    </g:if>
                    <g:else>
                        <p>The rank statistics are
                        <g:each in="${statResults.rank}">
                            <dd>${it.getKey()}: ${it.getValue()}</dd>
                        </g:each>
                        </p>
                    </g:else>
                </div>

                <div class="span4">
                    <g:if test="${statResults.userGoal.size() == 0}">
                        <p>No user goals have been specified in transactions</p>
                    </g:if>
                    <g:else>
                        <p>The user goal statistics are
                        <g:each in="${statResults.userGoal}">
                            <dd>${it.getKey()}: ${it.getValue()}</dd>
                        </g:each>
                        </p>
                    </g:else>
                </div>

                <div class="span4">
                    <g:if test="${statResults.modeOfConsultation.size() == 0}">
                        <p>No modes of consultation have been specified in transactions</p>
                    </g:if>
                    <g:else>
                        <p>The mode of consultation statistics are
                        <g:each in="${statResults.modeOfConsultation}">
                            <dd>${it.getKey()}: ${it.getValue()}</dd>
                        </g:each>
                        </p>
                    </g:else>
                </div>
            </div>

            <div class="row-fluid">
                <div class="span4">
                    <g:if test="${statResults.school.size() == 0}">
                        <p>No schools have been specified in transactions</p>
                    </g:if>
                    <g:else>
                        <p>The school statistics are
                        <g:each in="${statResults.school}">
                            <dd>${it.getKey()}: ${it.getValue()}</dd>
                        </g:each>
                        </p>
                    </g:else>
                </div>

                <div class="span4">
                    <g:if test="${statResults.courseSponsor.size() == 0}">
                        <p>No course sponsors have been specified in transactions</p>
                    </g:if>
                    <g:else>
                        <p>The course sponsor statistics are
                        <g:each in="${statResults.courseSponsor}">
                            <dd>${it.getKey()}: ${it.getValue()}</dd>
                        </g:each>
                        </p>
                    </g:else>
                </div>

                <div class="span4">
                    <g:if test="${statResults.modeOfConsultation.size() == 0}">
                        <p>No services provided have been specified in transactions</p>
                    </g:if>
                    <g:else>
                        <p>The service provided statistics are
                        <g:each in="${statResults.serviceProvided}">
                            <dd>${it.getKey()}: ${it.getValue()}</dd>
                        </g:each>
                        </p>
                    </g:else>
                </div>
            </div>

            <p>The library unit statistics are

            <g:each in="${statResults.libraryUnit}">
                <dd>${it.getKey()}: ${it.getValue()}</dd>
            </g:each>
            </p>

            </div>
        </g:else>
    </g:if>
    <g:else>Not Yet Implemented</g:else>
</md:report>

