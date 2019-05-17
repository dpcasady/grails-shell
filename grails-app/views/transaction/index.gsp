<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'transaction.label', default: 'Transaction')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>


        <div id="list-transaction" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <g:if test="${transactionList?.size() > 0}">

                <table class="table">

                    <tr>
                        <g:sortableColumn property="id" title="Id"/>
                        <g:sortableColumn property="id" title="Items"/>
                        <g:sortableColumn property="id" title="Date"/>
                    </tr>

                    <g:each in="${transactionList}" var="transaction">
                        <tr>
                            <td><g:link action="show" id="${transaction.id}">${transaction.id}</g:link></td>
                            <td>${transaction.shoppingCart.shoppingCartItems.size()} Items</td>
                            <td><g:formatDate date="${transaction.dateCreated}" format="dd MMM yyyy hh:mm" /></td>
                        </tr>
                    </g:each>

                    <div class="pagination">
                        <g:paginate total="${transactionCount ?: 0}" />
                    </div>
                </table>
            </g:if>
            <g:else>
                <div class="alert alert-info">No transactions created yet...</div>
            </g:else>

        </div>
    </body>
</html>