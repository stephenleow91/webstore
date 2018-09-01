<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="x-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta name="viewport" content="width=divce-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<title>Cart</title>

</head>

<body>

	<section>
		<div class="jumbotron">
			<h1>Cart</h1>
			<p>All the selected products in your cart</p>
		</div>
	</section>
	
	<section class="container" ng-app="cartApp">				
		<div ng-controller="cartCtrl" ng-init="initCartId('${cartId}')">
			<div>
				<a class="btn btn-danger pull-left" ng-click="clearCart()">
					<span class="glyphicon glyphicon-remove-sign"></span> Clear Cart
				</a>
				<a href="<spring:url value="/checkout?cartId=${cartId}"/>" class="btn btn-success pull-right">
					<span class="glyphicon-shopping-cart glyphicon"></span> Check out
				</a>
			</div>
			
			<table class="table table-hover">
				<tr>
					<th>Product</th>
					<th>Unit Price</th>
					<th>Quantity</th>
					<th>Price</th>
					<th>Action</th>
				</tr>
				
				<tr ng-repeat="item in cart.cartItems">
					<td>{{item.product.productId}}-{{item.product.name}}</td>
					<td>{{item.product.unitPrice}}</td>
					<td>{{item.quantity}}</td>
					<td>{{item.totalPrice}}</td>
					<td><a href="#" class="label label-danger" ng-click="removeFromCart(item.product.productId)">
							<span class="glyphicon glyphicon-remove"></span> Remove
						</a>
					</td>
				</tr>
				
				<tr>
					<th></th>
					<th>Price</th>
					<th>Grand Total</th>
					<th>{{cart.grandTotal}}</th>
					<th></th>
				</tr>
			</table>
			<a href="<spring:url value="/market/products" />" class="btn btn-default">
				<span class="glyphicon-hand-left glyphicon"></span> Continue shopping
			</a>
		</div>
	</section>
	
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.1/angular.min.js"></script>
	<script src="/webstore/js/controllers.js"></script>
</body>
</html>
	