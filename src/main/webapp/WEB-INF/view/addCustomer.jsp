<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
			<p>Add customers</p>
		</div>
	</section>

	<section class="container">
		<form:form method="POST" modelAttribute="newCustomer" class="form-horizontal">
			<fieldset>
				<legend>Add new customer</legend>
			</fieldset>
			
			<div class="form-group">
				<label class="control-label col-lg-2" for="customerId">Customer Id</label>
				<div class="col-lg-10">
					<form:input id="customerId" path="customerId" type="text" class="form:input-large" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-lg-2" for="name">Name</label>
				<div class="col-lg-10">
					<form:input id="name" path="name" type="text" class="form:input-large" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-lg-2 " for="address">Address</label>
				<div class="col-lg-10">
					<form:input id="address" path="address" type="text" class="form:input-large" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-lg-2" for="noOfOrdersMade">No of Orders Made</label>
				<div class="col-lg-10">
					<form:input id="noOfOrdersMade" path="noOfOrdersMade" type="text" class="form:input-large" />
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<input type="submit" id="btnAdd" class="btn btn-primary" value="Add"/>
				</div>
			</div>

		</form:form>
	</section>
	
</body>

</html>