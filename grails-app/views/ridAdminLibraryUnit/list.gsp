<%@ page import="org.codehaus.groovy.grails.io.support.ClassPathResource; metridoc.rid.RidLibraryUnit" %>
<g:set var="entityName" value="${message(code: 'ridLibraryUnit.label', default: 'RidLibraryUnit')}"/>

<md:report>
    <r:external dir="css" file="pagination.css" plugin="metridoc-rid"/>
    <r:external dir="css" file="table.css" plugin="metridoc-rid"/>

    <!--[if !IE]><!-->
    <r:external dir="css" file="floating_tables_for_admin_6.css" plugin="metridoc-rid"/>
    <!--<![endif]-->

    <div class="md-application-content">
        <g:render template="/ridAdminTransaction/toggle" plugin="metridoc-rid"/>

        <g:render template="/ridAdminTransaction/tabs" plugin="metridoc-rid"/>
        <g:render template="/ridAdminTransaction/modal" plugin="metridocRid"
                  model="[title: entityName + ' Create/Edit']"/>

        <div id="list-ridLibraryUnit" class="content scaffold-list" role="main">
            <h1>
                <g:message code="default.list.label" args="[entityName]"/>
                <a data-tooltip="Creating" href="create?dummy=${org.apache.commons.lang.math.RandomUtils.nextInt()}"
                   data-target="#myModal" data-toggle="modal">
                    <i title="Create Library Unit" class="icon-plus-sign-alt"></i>
                </a>
            </h1>

            <g:hasErrors bean="${ridLibraryUnitError}">
                <div class="errors">
                    <g:renderErrors bean="${ridLibraryUnitError}" as="list"/>
                </div>
            </g:hasErrors>

            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <g:sortableColumn property="name"
                                      title="${message(code: 'ridLibraryUnit.name.label', default: 'Name')}"/>
                    <th>Number of RidTransaction</th>
                    <th>Spreadsheet File</th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${ridLibraryUnitInstanceList}" var="ridLibraryUnitInstance">
                    <tr>
                        <td>
                            <a data-toggle="modal"
                               href="edit/${ridLibraryUnitInstance.id}?dummy=${org.apache.commons.lang.math.RandomUtils.nextInt()}"
                               data-target="#myModal">${fieldValue(bean: ridLibraryUnitInstance, field: "name")}</a>
                        </td>
                        <g:if test="${session.transType == "consultation"}">
                            <td>${ridLibraryUnitInstance?.ridConsTransaction?.size()}</td>
                        </g:if>
                        <g:else>
                            <td>${ridLibraryUnitInstance?.ridInsTransaction?.size()}</td>
                        </g:else>
                        <%
                            File resource =
                                new File(System.getProperty("user.home") + "/.metridoc/files/rid/libraryUnit/" + ridLibraryUnitInstance.name + '_Bulkload_Schematic.xlsx')
                        %>
                        <td>
                            <g:if test="${resource.exists()}">
                                <g:link action="download"
                                        params="${[sname: ridLibraryUnitInstance.name + '_Bulkload_Schematic.xlsx']}">
                                    ${ridLibraryUnitInstance.name + '_Bulkload_Schematic.xlsx'}
                                </g:link>
                            </g:if>
                            <g:else>
                                <span style="color: #ff0000;">WARNING: NO spreadsheet!</span>
                            </g:else>
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
            <g:if test="${ridLibraryUnitInstanceTotal > 10}">
                <div class="pagination">
                    <g:paginate total="${ridLibraryUnitInstanceTotal}"/>
                </div>
            </g:if>
        </div>
    </div>
</md:report>

