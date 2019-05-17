<!DOCTYPE html>
<html>
    
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'store.label', default: 'Store')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    
    <body>

        <style type="text/css">
            .item-listing{
                float:left;
                border:solid 1px #ddd;
                margin:20px 20px;
                padding:53px;
            }

            #shopping-cart-count{
                font-size:27px;
            }

            #checkout-container{
                text-align:right;
            }

            .align-right{
                text-align:right;
            }
        </style>

        <a href="#list-store" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>



        
        <div id="list-store" class="content scaffold-list" role="main">

            <h1>Shopping Cart</h1>


            <table class="table">
                <tr>
                    <th>Product</th>
                    <th>Quantity</th>
                    <th class="align-right">Price</th>
                    <th></th>
                </tr>
                
                <% def total = 0 %>
                <g:each in="${shoppingCart.shoppingCartItems}" var="shoppingCartItem">
                    <% total += shoppingCartItem.item.price %>
                    <tr>
                        <td>${shoppingCartItem.item.description}</td>
                        <td>${shoppingCartItem.quantity}</td>
                        <td class="align-right">${shoppingCartItem.item.price}</td>
                        <td class="align-right">
                            <g:form action="remove" id="${shoppingCartItem.item.id}">
                                <g:actionSubmit class="btn btn-warning" value="Remove"/>
                            </g:form>
                        </td>
                    </tr>

                </g:each>
                <tr>
                    <td colspan="3" class="align-right">$${total}</td>
                </tr>

            </table>

            <div id="checkout-container">
                <g:form controller="transaction" action="checkout">
                    <g:actionSubmit value="\$${total} Checkout!" class="btn btn-primary btn-large"/>
                </g:form>
            </div>
        </div>

    </body>
</html>