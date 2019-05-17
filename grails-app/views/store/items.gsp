<!DOCTYPE html>
<html>
    
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'store.label', default: 'Store')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    
    <body>

        <div id="list-store" class="content scaffold-list" role="main">

            <h1 id="store-heading">${store.name} Store</h1>

            <g:link uri="/shoppingCart" elementId="shopping-cart-container" class="pull-right">
                <span id="shopping-cart-count">${session?.shoppingCart?.shoppingCartItems?.size()}</span>
                <br/>Cart Items

                <br class="clear"/>
            </g:link> 

            <p>Shop the lastest and greatest in ${store.type}</p>

            <div id="success-container">
                <p id="success-added" style="display:none">Successfully added to Shopping Cart</p>
            </div>

            <g:each in="${store.items}" var="item">

                <div class="item-listing">
                    <h2>${item.description}</h2>
                    <p>${item.price}</p>
                    <a href="javascript:" class="add-cart btn btn-large btn-warning" data-id="${item.id}">Add Cart!</a>
                </div>    

            </g:each>

        </div>


        <script type="text/javascript">
            $(document).ready(function(){
                var $addCartBtn = $(".add-cart"),
                    $success = $("#success-added"),
                    $cartCount = $("#shopping-cart-count");

                $addCartBtn.click(addShoppingCart)

                function addShoppingCart(event){
                    var id = $(event.target).attr("data-id")

                    $.ajax({
                        url : "/champion/shoppingCart/add/" + id,
                        method : "POST",
                        dataType : "json",
                        success : updateShoppingCart,
                        error : errored
                    })
                }
                
                function updateShoppingCart(data, b){
                    $cartCount.html(data.shoppingCartItems.length)
                    $success.show()
                    $success.fadeOut(2300)
                }

                function errored(){
                    console.log("errored")
                }

            })
        </script>

    </body>
</html>