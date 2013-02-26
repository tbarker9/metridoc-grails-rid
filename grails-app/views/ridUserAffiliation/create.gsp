<%@ page import="metridoc.rid.RidUserAffiliation" %>
<g:set var="entityName" value="${message(code: 'ridUserAffiliation.label', default: 'RidUserAffiliation')}" />

            <div id="create-ridUserAffiliation" class="content scaffold-create" role="main">
                <h1><g:message code="default.create.label" args="[entityName]" /></h1>
                %{--<g:hasErrors bean="${ridUserAffiliationInstance}">--}%
                    %{--<ul class="errors" role="alert">--}%
                        %{--<g:eachError bean="${ridUserAffiliationInstance}" var="error">--}%
                            %{--<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>--}%
                        %{--</g:eachError>--}%
                    %{--</ul>--}%
                %{--</g:hasErrors>--}%

                <g:form class="form-horizontal" action="save" >
                    %{--<fieldset class="form">--}%
                    <div style="margin-top: 2em">
                        <g:render template="form" plugin="metridoc-rid"/>
                    %{--</fieldset>--}%
                    <fieldset class="buttons">
                        <g:submitButton name="create" class="btn btn-danger" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                    </fieldset>
                </g:form>
            </div>
