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

        </style>

        <a href="#list-store" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>



        
        <div id="list-store" class="content scaffold-list" role="main">

            <h1>${store.name} Store</h1>

            <p>Shop the lastest and greatest in ${store.type}</p>

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
                var $addCartBtn = $(".add-cart")
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
                    console.log(data, b)



                }

                function errored(){
                    console.log("errored")
                }

            })
        </script>

    </body>
</html>