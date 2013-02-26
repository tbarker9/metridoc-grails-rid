<%@ page import="metridoc.rid.RidDepartmentalAffiliation" %>
<g:set var="entityName" value="${message(code: 'ridDepartmentalAffiliation.label', default: 'RidDepartmentalAffiliation')}" />
%{--<md:report>--}%
        %{--<div class="md-application-content">--}%

            <div id="edit-ridDepartmentalAffiliation" class="content scaffold-edit" role="main">
                <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
                %{--<g:hasErrors bean="${ridDepartmentalAffiliationInstance}">--}%
                    %{--<ul class="errors" role="alert">--}%
                        %{--<g:eachError bean="${ridDepartmentalAffiliationInstance}" var="error">--}%
                        %{--<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>--}%
                        %{--</g:eachError>--}%
                    %{--</ul>--}%
                %{--</g:hasErrors>--}%

                <g:form method="post" >
                    <g:hiddenField name="id" value="${ridDepartmentalAffiliationInstance?.id}" />
                    <g:hiddenField name="version" value="${ridDepartmentalAffiliationInstance?.version}" />
                    <div class="form-horizontal" style="margin-top: 2em">
                        <g:render template="form" plugin="metridocRid"/>
                    </div>
                    %{--<fieldset class="buttons">--}%
                        <g:actionSubmit style="float: right; margin-left: 1em" class="btn btn-success" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                        %{--<g:actionSubmit style="float: right" class="btn btn-danger" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}"--}%
                                        %{--formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />--}%
                    %{--</fieldset>--}%
                </g:form>
            </div>
        %{--</div>--}%
%{--</md:report>--}%
