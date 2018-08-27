<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="x-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta name="viewport" content="width=divce-width, initial-scale=1">
<title><spring:message code="products.title"/></title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>

<body>
	<section>
		<div class="pull-right" style="padding-right:50px">
			<a href="<c:url value="/logout"/>">Logout</a>
		</div>
	</section>
	<section>
		<div class="jumbotron">
			<h1><spring:message code="products.section.top.h1"/></h1>
			<p><spring:message code="products.section.top.p"/></p>
		</div>
	</section>

	<section class="container">
		<div class="row">
			<c:forEach items="${products}" var="product">
				<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
					<div class="thumbnail">
						<img src="<c:url value="/img/${product.productId}.jpg"/>" alt="image" style="width:300px; height:350px"/>
						<div class="caption">
							<h3>${product.name}</h3>
							<p>${product.description}</p>
							<p>${product.unitPrice} <spring:message code="products.currency.usd"/></p>
							<p>${product.unitsInStock} <spring:message code="products.unitsInStock"/></p>
							<p>
								<a href="<spring:url value="/market/product?id=${product.productId}" htmlEscape="true"/>" class="btn btn-primary">
									<span class="glyphicon-info-sign glyphicon"></span> <spring:message code="products.details"/>
								</a>
							</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>
</body>

</html>