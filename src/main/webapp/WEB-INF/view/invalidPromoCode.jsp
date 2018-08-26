<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="x-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta name="viewport" content="width=divce-width, initial-scale=1">
<title><spring:message code="addProduct.title" /></title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>

<body>
	<section>
		<div class="jumbotron">
			<h1 class="alert alert-danger">Invalid Promo Code</h1>
		</div>
	</section>

	<section>
		<div class="container">
			<p>${url}</p>
			<p>${exception}</p>
		</div>

		<div class="form-group">
			<p>
				<a href="<spring:url value="/market/products" />" class="btn btn-primary">
					<span class="glyphicon-hand-left glyphicon"></span>
				</a>
			</p>
		</div>
	</section>
</body>

</html>