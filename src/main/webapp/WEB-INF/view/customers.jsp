<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="x-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta name="viewport" content="width=divce-width, initial-scale=1">
<title><spring:message code="customers.title"/></title>
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
			<h1><spring:message code="customers.section.top.h1"/></h1>
			<p><spring:message code="customers.section.top.p"/></p>
		</div>
	</section>

	<section class="container">
		<div class="row">
			<c:forEach items="${customers}" var="customer">
				<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
					<div class="thumbnail">
						<div class="caption">
							<h3><spring:message code="customers.details.customerName"/> : ${customer.name}</h3>
							<p><spring:message code="customers.details.customerId"/> : ${customer.customerId}</p>
							<p><spring:message code="customers.details.customerAddress"/> : ${customer.address}</p>
							<p><spring:message code="customers.details.numberOfOrdersMade"/> : ${customer.noOfOrdersMade}</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>
</body>

</html>