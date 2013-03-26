<%@ page import="metridoc.rid.RidTransaction" %>
<g:set var="entityName" value="${message(code: 'ridTransaction.label', default: 'RidTransaction')}"/>

<md:report>
    <div id="downloadModal" class="modal hide fade" role="dialog">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">x</button>
            <h3 style="color: #48802c;">Download Spreadsheet</h3>
        </div>

        <div class="modal-body">
            <g:form style="padding-top: 15px; padding-bottom: 35px">
                <div class="control-group" >
                    <div class="controls" style="margin-left: 30px; float: left;">
                        <label for="ridReportType" style="color: #48802c">Choose One Report Type</label>
                        <g:select id="ridReportType" style="width:120px" name="ridReportType.name"
                                  from="${metridoc.rid.RidReportType.list()}" optionKey="name" required=""/>
                    </div>

                    <div class="controls" style="margin-right: 30px;float: right;">
                        <button class="btn" type="submit" name="_action_download">
                            <i class="icon-download-alt"></i> Download
                        </button>
                    </div>
                </div>
            </g:form>
        </div>
    </div>

    <div class="md-application-content">
        <g:render template="tabs" plugin="metridocRid"/>

        <div id="spreadsheetUpload-ridTransaction" class="content scaffold-search" role="main">
            <h1><g:message code="Upload Spreadsheet"/></h1>

            <g:form style="padding-top: 15px" class="form-horizontal" enctype="multipart/form-data" useToken="true">
                <div class="control-group">
                    <div class="controls">
                        <input id="spreadsheetUpload" name="spreadsheetUpload" type="file" style="display: none"/>

                        <div class="input-append">
                            <input id="spreadsheetUploadPath" name="spreadsheetUploadPath" type="text" disabled="true"/>
                            <a class="btn" onclick="$('input[id=spreadsheetUpload]').click();">Browse</a>
                        </div>
                        <g:javascript>
                            $('input[id=spreadsheetUpload]').change(function () {
                                var fileName = $(this).val().replace("C:\\fakepath\\", "");
                                $('#spreadsheetUploadPath').val(fileName);
                            });
                        </g:javascript>
                    </div>

                    <div class="controls">
                        <button class="btn" type="submit" name="_action_upload">
                            <i class="icon-upload-alt"></i> Upload
                        </button>
                        <a href="#downloadModal" role="button" class="btn" data-toggle="modal">
                            <i class="icon-download-alt"></i> Download Spreadsheet
                        </a>
                    </div>
                </div>
            </g:form>
        </div>
    </div>
</md:report>