<%@ page import="metridoc.rid.RidTransaction" %>
<g:set var="entityName" value="${message(code: 'ridTransaction.label', default: 'RidTransaction')}"/>

<md:report>
    <div class="md-application-content">
        <tmpl:tabs/>
        <g:render template="/ridTransactionAdmin/modal"
                  model="[title: 'Academic Departments', myID: 'myDepartment']"/>

        <div id="edit-ridTransaction" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]"/></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <g:hasErrors bean="${ridTransactionInstance}">
                <div class="errors">
                    <g:renderErrors bean="${ridTransactionInstance}" as="list"/>
                </div>
            </g:hasErrors>
            <g:form method="post" useToken="true">
                <g:hiddenField name="id" value="${ridTransactionInstance?.id}"/>
                <g:hiddenField name="version" value="${ridTransactionInstance?.version}"/>
                <fieldset class="form">
                    <tmpl:form/>
                </fieldset>
                <fieldset class="buttons">
                    <g:actionSubmit class="btn btn-success" action="update"
                                    value="${message(code: 'default.button.update.label', default: 'Update')}"/>
                    <g:actionSubmit class="btn btn-danger" action="delete"
                                    value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                                    formnovalidate=""
                                    onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
                </fieldset>
            </g:form>
        </div>
    </div>
</md:report>