<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en" ng-app="app">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Struts2 - REST - AngularJS - Demo</title>

    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/app.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <base href="<s:url forceAddSchemeHostAndPort="true" includeContext="true" value="/" namespace="/" />">
</head>
<body ng-controller="AppController as app">
<div class="container-fluid">
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/orders">{{'app.name' | translate}}</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="orders">Home</a></li>
                    <li><a href="order/new">{{'order.new' | translate}}</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li ng-class="{'active': lang == 'en'}"><a ng-click="app.switchLanguage('en')">EN</a></li>
                    <li ng-class="{'active': lang == 'de'}"><a ng-click="app.switchLanguage('de')">DE</a></li>
                </ul>
            </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
    </nav>

    <div class="row">
        <div id="alerts" class="col-md-12">
            <uib-alert ng-repeat="alert in app.alerts" type="{{alert.type}}" close="app.closeAlert($index)" dismiss-on-timeout="4000">{{alert.msg}}</uib-alert>
        </div><!--/col-md-12--->

        <div class="col-md-12" ng-view>
        </div><!--/col-md-12--->
    </div><!--/row-->
</div><!--/container-->
<s:if test="useMinifiedResources">
    <script src="<s:url value="js/external.js" />"></script>
    <script src="<s:url value="js/application.js" />"></script>
</s:if>
<s:else>
    <script src="<s:url value="js/lib/angular/angular.min.js" />"></script>
    <script src="<s:url value="js/lib/angular/angular-route.min.js" />"></script>
    <script src="<s:url value="js/lib/ui-bootstrap-tpls-1.3.2.min.js" />"></script>
    <script src="<s:url value="js/lib/angular-translate.min.js" />"></script>
    <script src="<s:url value="js/lib/angular-translate-loader-url.min.js" />"></script>
    <script src="<s:url value="js/app.js" />"></script>
    <script src="<s:url value="js/config.js" />"></script>
    <script src="<s:url value="js/services/DataService.js" />"></script>
    <script src="<s:url value="js/controllers/AppController.js" />"></script>
    <script src="<s:url value="js/controllers/OrdersController.js" />"></script>
    <script src="<s:url value="js/controllers/OrderDetailController.js" />"></script>
    <script src="<s:url value="js/controllers/OrderEditController.js" />"></script>
    <script src="<s:url value="js/controllers/OrderAddController.js" />"></script>
</s:else>
</body>
</html>
