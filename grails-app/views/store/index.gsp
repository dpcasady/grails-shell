<!DOCTYPE html>
<html>
    
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'store.label', default: 'Store')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    
    <body>


        <a href="#list-store" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

        
        <div id="list-store" class="content scaffold-list" role="main">

            <g:each in="${storeList}" var="store">

                <div class="store-listing">
                    <h2>${store.name}</h2>
                    <p>${store.items.size()}</p>
                    <g:link uri="/store/items/${store.id}" class="btn btn-large btn-primary">Shop Now!</g:link>
                </div>    


            </g:each>


        </div>

    </body>
</html>