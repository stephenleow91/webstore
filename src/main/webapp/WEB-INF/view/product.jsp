<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="x-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta name="viewport" content="width=divce-width, initial-scale=1">
<title>Products</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>

<body>
	<section>
		<div class="jumbotron">
			<h1>Products</h1>
			<p>All the available products in our store</p>
		</div>
	</section>

	<section class="container">
		<div class="row">
			<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
				<div class="thumbnail">
					<div class="caption">
						<h3>${product.name}</h3>
						<p>${product.description}</p>
						<p>
							<strong>Item Code : </strong> <span class="label label-warning">${product.productId}</span>
						</p>
						<p>
							<strong>manufacturer </strong> : ${product.manufacturer}
						</p>
						<p>
							<strong>category </strong> : ${product.category}
						</p>
						<p>
							<strong>Available units in stock </strong> :
							${product.unitsInStock}
						</p>
						<h4>${product.unitPrice} USD</h4>
						<p>
							<a href="<spring:url value="/market/products"/>" class="btn btn-default"> 
								<span class="glyphicon-hand-left glyphicon"></span> back
							</a> 
							<a href="#" class="btn btn-warning btn-large"> 
								<span class="glyphicon-shopping-cart glyphicon"></span> Order Now
							</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>

</html>