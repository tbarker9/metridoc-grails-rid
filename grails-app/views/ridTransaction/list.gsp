
<%@ page import="metridoc.rid.RidTransaction" %>
<g:set var="entityName" value="${message(code: 'ridTransaction.label', default: 'RidTransaction')}" />
%{--<!doctype html>--}%
%{--<html>--}%
	%{--<head>--}%
		%{--<meta name="layout" content="main">--}%
		%{--<g:set var="entityName" value="${message(code: 'ridTransaction.label', default: 'RidTransaction')}" />--}%
		%{--<title><g:message code="default.list.label" args="[entityName]" /></title>--}%
	%{--</head>--}%
	%{--<body>--}%

<md:report>
    <!--[if !IE]><!-->
    <style>
     /*
      Max width before this PARTICULAR table gets nasty
      This query will take effect for any screen smaller than 760px
      and also iPads specifically.
      */
    @media
    only screen and (max-width: 980px),
    (min-device-width: 768px) and (max-device-width: 1024px)  {

        /* Force table to not be like tables anymore */
        table, thead, tbody, th, td, tr {
            display: block;
        }

        /* Hide table headers (but not display: none;, for accessibility) */
        thead tr {
            position: absolute;
            top: -9999px;
            left: -9999px;
        }

        tr { border: 1px solid #ccc; }

        .table td {
            /* Behave  like a "row" */
            border: none;
            /*border-bottom: 1px solid #eee;*/
            position: relative;
            padding-left: 50%;
        }

        .table td a{
            /* Behave  like a "row" */
            border: none;
            position: relative;
            /*padding-left: 50%;*/
            margin-left: 49%;
            color: #48802c;
            text-decoration: none;
        }


        td:before {
            /* Now like a table header */
            position: absolute;
            /* Top/left values mimic padding */
            top: 6px;
            left: 6px;
            width: 45%;
            padding-right: 10px;
            white-space: nowrap;
        }

        /*
          Label the data
          */
        td:nth-of-type(1):before { content: "Customer Question"; }
        td:nth-of-type(2):before { content: "Staff Pennkey"; }
        td:nth-of-type(3):before { content: "Date of Consultation"; }
        td:nth-of-type(4):before { content: "Event Length"; }
        td:nth-of-type(5):before { content: "Notes"; }
        /*td:nth-of-type(6):before { content: "Porn Name"; }*/
        /*td:nth-of-type(7):before { content: "Date of Birth"; }*/
        /*td:nth-of-type(8):before { content: "Dream Vacation City"; }*/
        /*td:nth-of-type(9):before { content: "GPA"; }*/
        /*td:nth-of-type(10):before { content: "Arbitrary Data"; }*/
    }

        /* Smartphones (portrait and landscape) ----------- */
    @media only screen
    and (min-device-width : 320px)
    and (max-device-width : 480px) {
        body {
            padding: 0;
            margin: 0;
            width: 320px; }
    }

        /* iPads (portrait and landscape) ----------- */
    @media only screen and (min-device-width: 768px) and (max-device-width: 1024px) {
        body {
            width: 495px;
        }
    }

    </style>
    <!--<![endif]-->

        <div class="md-application-content">
            <g:render template="tabs" plugin="metridocRid"/>

            <div id="list-ridTransaction" class="content scaffold-list" role="main">
                <h1><g:message code="default.list.label" args="[entityName]" /></h1>
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        
                        <g:sortableColumn property="customerQuestion" title="${message(code: 'ridTransaction.customerQuestion.label', default: 'Customer Question')}" />
                        
                        <g:sortableColumn property="staffPennkey" title="${message(code: 'ridTransaction.staffPennkey.label', default: 'Staff Pennkey')}" />
                        
                        <g:sortableColumn property="dateOfConsultation" title="${message(code: 'ridTransaction.dateOfConsultation.label', default: 'Date of Consultation')}" />
                        
                        <g:sortableColumn property="eventLength" title="${message(code: 'ridTransaction.eventLength.label', default: 'Event Length')}" />
                        
                        <g:sortableColumn property="notes" title="${message(code: 'ridTransaction.notes.label', default: 'Notes')}" />
                        
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${ridTransactionInstanceList}" status="i" var="ridTransactionInstance">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                            <%
                                customerQ = ridTransactionInstance.customerQuestion
                                if (customerQ!=null && customerQ.length() > 12)
                                    customerQ = customerQ.substring(0,12) + "..."
                            %>
                            <td><g:link action="show" id="${ridTransactionInstance.id}"
                                        title="${ridTransactionInstance.customerQuestion}">
                                    ${customerQ}
                                </g:link>
                            </td>
                            
                            <td>${fieldValue(bean: ridTransactionInstance, field: "staffPennkey")}</td>

                            <td><g:formatDate format="yyyy-MM-dd" date="${ridTransactionInstance?.dateOfConsultation}" /></td>
                            %{--<td>${fieldValue(bean: ridTransactionInstance, field: "dateOfConsultation")}</td>--}%
                            
                            <td>${fieldValue(bean: ridTransactionInstance, field: "eventLength")}</td>
                            
                            <td>${fieldValue(bean: ridTransactionInstance, field: "notes")}</td>
                            
                        </tr>
                    </g:each>
                    </tbody>
                </table>
                <g:if test="${ridTransactionInstanceTotal > 10}">
                    <div class="pagination">
                        <g:paginate total="${ridTransactionInstanceTotal}" params="${params}" />
                    </div>
                </g:if>
            </div>
        </div>
</md:report>
	%{--</body>--}%
%{--</html>--}%
