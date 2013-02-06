<%@ page import="metridoc.rid.RidCourseSponsor" %>
<g:set var="entityName" value="${message(code: 'ridCourseSponsor.label', default: 'RidCourseSponsor')}" />



<md:report>
    <!--[if !IE]><!-->
    <style>
        /*
        Max width before this PARTICULAR table gets nasty
        This query will take effect for any screen smaller than 760px
        and also iPads specifically.
        */
    @media
    only screen and (max-width: 760px),
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
        td:nth-of-type(1):before { content: "Name"; }
        td:nth-of-type(2):before { content: "In Form"; }
        td:nth-of-type(3):before { content: "Number of RidTransaction"; }
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

    <div style="font-size: 12px">
        <g:render template="/ridTransactionAdmin/tabs" plugin="metridoc-rid"/>
    </div>

    <div class="md-application-content">
        <g:render template="modal" plugin="metridocRid"/>

        <div id="list-ridCourseSponsor" class="content scaffold-list" role="main">

                <h1>
                    <g:message code="default.list.label" args="[entityName]" />

                    <a data-tooltip="Creating" href="create" data-target="#myModal" data-toggle="modal">
                        <i title="Create Course Sponsor" class="icon-cog"></i>
                    </a>
                </h1>

                %{--<g:if test="${flash.message}">--}%
                    %{--<div class="message" role="status">${flash.message}</div>--}%
                %{--</g:if>--}%

                <table class="table table-striped table-hover">
                    <thead>
                    <tr>

                        <g:sortableColumn property="name" width="35%" title="${message(code: 'ridCourseSponsor.name.label', default: 'Name')}" />

                        <g:sortableColumn property="inForm" width="35%" title="${message(code: 'ridCourseSponsor.inForm.label', default: 'In Form')}" />

                        <th>Number of RidTransaction</th>
                    </tr>
                    </thead>

                    <tbody>
                    <g:each in="${ridCourseSponsorInstanceList}" status="i" var="ridCourseSponsorInstance">
                        <tr>
                            <td>
                                <a data-toggle="modal" href="edit/${ridCourseSponsorInstance.id}" data-target="#myModal">
                                    ${fieldValue(bean: ridCourseSponsorInstance, field: "name")}
                                </a>
                            </td>

                            <td>${fieldValue(bean: ridCourseSponsorInstance, field: "inForm")}</td>

                            <td>${ridCourseSponsorInstance?.ridTransaction?.size()}</td>
                            
                        </tr>
                    </g:each>
                    </tbody>
                </table>

                <g:if test="${ridCourseSponsorInstanceTotal > 10}">
                    <div class="pagination">
                        <g:paginate total="${ridCourseSponsorInstanceTotal}" />
                    </div>
                </g:if>
            </div>
    </div>
</md:report>
