<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'customer.label', default: 'Customer')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        
        <a href="#list-customer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>


        <div id="list-customer" class="content scaffold-list" role="main">
            
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>


            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>


            <g:if test="${customers.size() > 0}">

                <table class="table">
                    <tr>
                        <g:sortableColumn property="id" title="Id"/>
                        <g:sortableColumn property="id" title="Name"/>
                        <g:sortableColumn property="id" title="Address"/>
                        <g:sortableColumn property="id" title="City"/>
                        <g:sortableColumn property="id" title="State"/>
                        <g:sortableColumn property="id" title="Zip"/>
                        <g:sortableColumn property="id" title="Phone"/>
                        <g:sortableColumn property="id" title="Birthday"/>
                    </tr>
                    <g:each in="${customers}" var="customer">
                        <tr>
                            <td>${customer.id}</td>
                            <td>${customer.address}</td>
                            <td>${customer.name}</td>
                            <td>${customer.city}</td>
                            <td>${customer.state.name}</td>
                            <td>${customer.zip}</td>
                            <td>${customer.phone}</td>
                            <td><g:formatDate date="${customer.birthday}" format="dd MMM yyyy" /></td>
                        </tr>
                    </g:each>
                    <div class="pagination">
                        <g:paginate total="${customerCount ?: 0}" />
                    </div>
                </table>
            </g:if>
        </div>
    </body>
</html>