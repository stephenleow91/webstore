<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="x-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta name="viewport" content="width=divce-width, initial-scale=1">

<title><spring:message code="product.title"/></title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

</head>

<body>
	<section>
		<div class="pull-right" style="padding-right:50px">
			<a href="<c:url value="/logout"/>">Logout</a>
		</div>
	</section>
	<section>
		<div class="jumbotron">
			<h1><spring:message code="product.section.top.h1"/></h1>
			<p><spring:message code="product.section.top.p"/></p>
		</div>
	</section>

	<section class="container" ng-app="cartApp">
		<div class="row">
			<div class="col-md-4">
				<img src="<c:url value="/img/${product.productId}.jpg"/>" alt="image" style="width:300px; height:350px"/>
			</div>
			<div class="col-sm-6 col-md-4" style="padding-bottom: 15px">
				<div class="thumbnail">
					<div class="caption">
						<h3>${product.name}</h3>
						<p>${product.description}</p>
						<p>
							<strong><spring:message code="product.itemCode"/> : </strong> <span class="label label-warning">${product.productId}</span>
						</p>
						<p>
							<strong><spring:message code="product.manufacturer"/> </strong> : ${product.manufacturer}
						</p>
						<p>
							<strong><spring:message code="product.category"/> </strong> : ${product.category}
						</p>
						<p>
							<strong><spring:message code="product.availableUnitsInStock"/> </strong> :
							${product.unitsInStock}
						</p>
						<h4>${product.unitPrice} <spring:message code="product.currency.usd"/></h4>
						<p ng-controller="cartCtrl">
							<a href="<spring:url value="/market/products"/>" class="btn btn-default"> 
								<span class="glyphicon-hand-left glyphicon"></span> <spring:message code="product.back"/>
							</a> 
							<a href="#" class="btn btn-warning btn-large" ng-click="addToCart('${product.productId}')"> 
								<span class="glyphicon-shopping-cart glyphicon"></span> <spring:message code="product.orderNow"/>
							</a>
							<a href="<spring:url value="/cart"/>" class="btn btn-default">
								<span class="glyphicon-hand-right glyphicon"></span> View Cart 
							</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular.min.js"></script>
	<script src="/webstore/js/controllers.js"></script>
</body>

</html>