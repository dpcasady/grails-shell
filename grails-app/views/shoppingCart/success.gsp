<!DOCTYPE html>
<html>
    
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'store.label', default: 'Store')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    
    <body>


        <div id="list-store" class="content scaffold-list" role="main">

            <h1>Checkout Success</h1>
            <p>Successfully checked out ${transaction.customer.name}</p>

            <table class="table" style="background:#f8f8f8">
                <tr>
                    <th>Product</th>
                    <th>Quantity</th>
                    <th class="align-right">Price</th>
                </tr>
                
                <% def total = 0 %>
                <g:each in="${shoppingCart.shoppingCartItems}" var="shoppingCartItem">
                    <% total += shoppingCartItem.item.price %>
                    <tr>
                        <td>${shoppingCartItem.item.description}</td>
                        <td>${shoppingCartItem.quantity}</td>
                        <td class="align-right">${shoppingCartItem.item.price}</td>
                    </tr>

                </g:each>
                <tr>
                    <td colspan="3" class="align-right" id="total">$${total}</td>
                </tr>

            </table>

            <div id="checkout-container">
                <g:form controller="transaction" action="delete" method="DELETE">
                    <input type="hidden" name="id" value="${transaction.id}">
                    <input type="submit" value="Delete Transaction" class="btn btn-danger"/>
                </g:form>
            </div>
        </div>

    </body>
</html>