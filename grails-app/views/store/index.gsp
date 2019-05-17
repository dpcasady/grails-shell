<!DOCTYPE html>
<html>
    
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'store.label', default: 'Store')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    
    <body>
        
        <div id="list-store" class="content scaffold-list" role="main">

            <h1>Shop Stores</h1>

            <p>Shop the latest and greatest in Sports, Electronics and Grocery</p>

            <g:each in="${storeList}" var="store">

                <div class="store-listing">
                    <h2>${store.name}</h2>
                    <p>${store.type}</p>
                    <p>${store.items.size()} Items</p>
                    <g:link uri="/store/items/${store.id}" class="btn btn-large btn-primary">Shop Now!</g:link>
                </div>    


            </g:each>


        </div>

    </body>
</html>