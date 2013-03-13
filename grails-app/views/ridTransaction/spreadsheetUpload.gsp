<%@ page import="metridoc.rid.RidTransaction" %>
<g:set var="entityName" value="${message(code: 'ridTransaction.label', default: 'RidTransaction')}" />

<md:report>
    <div class="md-application-content">
        <g:render template="tabs" plugin="metridocRid"/>

        <div id="spreadsheetUpload-ridTransaction" class="content scaffold-search" role="main">
            <h1><g:message code="Upload Spreadsheet" /></h1>

            <g:form style="padding-top: 15px" class="form-horizontal" enctype="multipart/form-data">
                <div class="control-group">
                    %{--TODO: move all scripts and css to separate js and css files--}%
                    <div class="controls">
                        <input id="spreadsheetUpload" name="spreadsheetUpload" type="file" style="display: none" />
                        <div class="input-append">
                            <input id="spreadsheetUploadPath" name="spreadsheetUploadPath" type="text" disabled="true"/>
                            <a class="btn" onclick="$('input[id=spreadsheetUpload]').click();">Browse</a>
                        </div>
                        <g:javascript>
                            $('input[id=spreadsheetUpload]').change(function(){
                                var fileName = $(this).val().replace("C:\\fakepath\\", "");
                                $('#spreadsheetUploadPath').val(fileName);
                            });
                        </g:javascript>
                    </div>

                    <div class="controls">
                        <button class="btn" type="submit" name="_action_upload">
                            <i class="icon-upload-alt"></i> Upload
                        </button>
                    </div>
                </div>
            </g:form>
        </div>
    </div>
</md:report>