<%@ page import="org.apache.shiro.SecurityUtils; metridoc.rid.RidTransaction" %>
<g:set var="entityName" value="${message(code: 'ridTransaction.label', default: 'RidTransaction')}"/>

<md:report>
    <r:external dir="js" file="RidTransaction.js" plugin="metridoc-rid"/>
    <div class="md-application-content">
        <g:render template="tabs" plugin="metridocRid"/>
        <g:render template="/ridTransactionAdmin/modal" plugin="metridocRid" model="[title: entityName]"/>

        <div id="create-ridTransaction" class="content scaffold-create" role="main">
            <h1>
                <g:message code="default.create.label" args="[entityName]"/>
                <g:if test="${SecurityUtils.getSubject().getPrincipal()}">
                    <a data-toggle="modal" href="templateList" data-target="#myModal">
                        <i title="Choose from template list" class="icon-list"></i>
                    </a>
                </g:if>
            </h1>
        %{--<g:if test="${flash.message}">--}%
        %{--<div class="message" role="status">${flash.message}</div>--}%
        %{--</g:if>--}%
        %{--<g:hasErrors bean="${ridTransactionInstance}">--}%
        %{--<ul class="errors" role="alert">--}%
        %{--<g:eachError bean="${ridTransactionInstance}" var="error">--}%
        %{--<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>>--}%
        %{--<g:message error="${error}"/>--}%
        %{--</li>--}%
        %{--</g:eachError>--}%
        %{--</ul>--}%
        %{--</g:hasErrors>--}%
            <g:hasErrors bean="${ridTransactionInstance}">
                <div class="errors">
                    <g:renderErrors bean="${ridTransactionInstance}" as="list"/>
                </div>
            </g:hasErrors>

            <g:form controller="RidTransaction" useToken="true">
                <fieldset class="form">
                    <g:render template="form" plugin="metridocRid"/>
                </fieldset>collectively                       c
                <fieldset class="buttons">
                    <input id="resetButton" class="btn btn-success" type="reset" value="Reset"/>
                    <g:actionSubmit action="save" name="create" class="btn btn-danger"
                                    value="${message(code: 'default.button.create.label', default: 'Create')}"/>
                    <g:if test="${SecurityUtils.getSubject().getPrincipal()}">
                        <g:actionSubmit action="remember" style="float: right" name="remember" class="btn btn-warning"
                                        value="${message(code: 'default.button.remember.label', default: 'Remember as template')}"
                                        onmouseover="removeRequired()" onmouseout="setRequired()"/>
                    </g:if>
                </fieldset>
            </g:form>
        </div>
    </div>
</md:report>