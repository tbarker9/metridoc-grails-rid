<%@ page import="metridoc.rid.RidProductConnected" %>
<g:set var="entityName" value="${message(code: 'ridProductConnected.label', default: 'RidProductConnected')}" />

            <div id="create-ridProductConnected" class="content scaffold-create" role="main">
                <h1><g:message code="default.create.label" args="[entityName]" /></h1>
                <g:hasErrors bean="${ridProductConnectedInstance}">
                    <ul class="errors" role="alert">
                        <g:eachError bean="${ridProductConnectedInstance}" var="error">
                            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                        </g:eachError>
                    </ul>
                </g:hasErrors>

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
