<%@ page import="metridoc.rid.RidRank" %>
<g:set var="entityName" value="${message(code: 'ridRank.label', default: 'RidRank')}"/>

<md:report>
    <r:external dir="css" file="pagination.css" plugin="metridoc-rid"/>
    <r:external dir="css" file="table.css" plugin="metridoc-rid"/>
    <!--[if !IE]><!-->
    <r:external dir="css" file="floating_tables_for_admin_1.css" plugin="metridoc-rid"/>
    <!--<![endif]-->

    <div class="md-application-content">
        <g:render template="/ridAdminTransaction/toggle" plugin="metridoc-rid"/>

        <g:render template="/ridAdminTransaction/tabs" plugin="metridoc-rid"/>
        <g:render template="/ridAdminTransaction/modal" plugin="metridocRid"
                  model="[title: entityName + ' Create/Edit']"/>

        <div id="list-ridRank" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]"/>

                <a data-tooltip="Creating" href="create?dummy=${org.apache.commons.lang.math.RandomUtils.nextInt()}"
                   data-target="#myModal" data-toggle="modal">
                    <i title="Create Rank" class="icon-plus-sign-alt"></i>
                </a>
            </h1>

            <g:hasErrors bean="${ridRankError}">
                <div class="errors">
                    <g:renderErrors bean="${ridRankError}" as="list"/>
                </div>
            </g:hasErrors>

            <table class="table table-striped table-hover">
                <thead>
                <tr>

                    <g:sortableColumn property="name" title="${message(code: 'ridRank.name.label', default: 'Name')}"/>

                    <g:sortableColumn property="inForm"
                                      title="${message(code: 'ridRank.inForm.label', default: 'In Form')}"/>

                    <th>Number of RidTransaction</th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${ridRankInstanceList}" status="i" var="ridRankInstance">
                    <tr>

                        <td>
                            <a data-toggle="modal"
                               href="edit/${ridRankInstance.id}?dummy=${org.apache.commons.lang.math.RandomUtils.nextInt()}"
                               data-target="#myModal">
                                ${fieldValue(bean: ridRankInstance, field: "name")}
                            </a>
                        </td>

                        <% def choices = ['NO', 'YES, and no indication needed', 'YES, and indication required'] %>
                        <td>${choices.get(ridRankInstance?.inForm)}</td>
                        <g:if test="${session.transType == "consultation"}">
                            <td>${ridRankInstance?.ridConsTransaction?.size()}</td>
                        </g:if>
                        <g:else>
                            <td>${ridRankInstance?.ridInsTransaction?.size()}</td>
                        </g:else>

                    </tr>
                </g:each>
                </tbody>
            </table>
            <g:if test="${ridRankInstanceTotal > 10}">
                <div class="pagination">
                    <g:paginate total="${ridRankInstanceTotal}"/>
                </div>
            </g:if>
        </div>
    </div>
</md:report>
