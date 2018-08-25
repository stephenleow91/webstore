<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="x-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta name="viewport" content="width=divce-width, initial-scale=1">
<title>Customers</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>

<body>
	<section>
		<div class="jumbotron">
			<h1>Customers</h1>
			<p>All the customers in our store</p>
		</div>
	</section>

	<section class="container">
		<div class="row">
			<c:forEach items="${customers}" var="customer">
				<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
					<div class="thumbnail">
						<div class="caption">
							<h3>Customer name : ${customer.name}</h3>
							<p>Customer id : ${customer.customerId}</p>
							<p>Customer address : ${customer.address}</p>
							<p>Number of orders made : ${customer.noOfOrdersMade}</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>
</body>

</html>