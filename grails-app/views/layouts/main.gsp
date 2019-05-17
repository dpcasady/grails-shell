<!doctype html>
<html class="no-js" lang="">
    <head>
        
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        
        <title><g:layoutTitle default="Champion Healthcare Technologies"/></title>
        
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <asset:link rel="shortcut icon" href="favicon.ico" type="image/x-icon"/>
        <asset:stylesheet src="application.css"/>
        <asset:javascript src="jquery.js"/>
        <asset:javascript src="jquery.ui.js"/>

        <g:layoutHead/>

    </head>
    <body>
        <div id="content-container">
            <div class="row">
                <div class="col-lg-12">
                    <nav class="navbar navbar-light bg-faded">
                        <a class="navbar-brand" href="${createLink(uri:'/')}">
                            <asset:image src="logo.png" height="30" alt="Champion Healthcare Technologies"/>
                        </a>

                        <g:link uri="/store" class="btn btn-default btn-lg pull-right">Shop Now</g:link></li>
                        
                        <br class="clear"/>
                    </nav>
                    <g:layoutBody/>

                    <br class="clear"/>
                    
                    <div id="bottom-navigation">
                        <g:link uri="/">Dashboard</g:link>&nbsp;|&nbsp;
                        <g:link controller="customer" action="index">Customers</g:link>&nbsp;|&nbsp;
                        <g:link controller="store" action="index">Stores</g:link>&nbsp;|&nbsp;
                        <g:link controller="item" action="index">Items</g:link>&nbsp;|&nbsp;
                        <g:link controller="transaction" action="index">Transactions</g:link>
                    </div>
                </div>
            </div>
        </div>
        <asset:javascript src="application.js"/>
        <asset:deferredScripts/>
    </body>
</html>
