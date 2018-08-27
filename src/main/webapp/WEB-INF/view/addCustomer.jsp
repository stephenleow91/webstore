<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="x-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta name="viewport" content="width=divce-width, initial-scale=1">
<title><spring:message code="addCustomer.title"/></title>
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
			<h1><spring:message code="addCustomer.section.top.h1"/></h1>
			<p><spring:message code="addCustomer.section.top.p"/></p>
		</div>
	</section>

	<section class="container">
		<form:form method="POST" modelAttribute="newCustomer" class="form-horizontal">
			<fieldset>
				<legend><spring:message code="addCustomer.form.fieldset.legend"/></legend>
			</fieldset>
			
			<div class="form-group">
				<label class="control-label col-lg-2" for="customerId"><spring:message code="addCustomer.form.customerId.label"/></label>
				<div class="col-lg-10">
					<form:input id="customerId" path="customerId" type="text" class="form:input-large" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-lg-2" for="name"><spring:message code="addCustomer.form.name.label"/></label>
				<div class="col-lg-10">
					<form:input id="name" path="name" type="text" class="form:input-large" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-lg-2 " for="address"><spring:message code="addCustomer.form.address.label"/></label>
				<div class="col-lg-10">
					<form:input id="address" path="address" type="text" class="form:input-large" />
				</div>
			</div>
			
			<%-- <div class="form-group">
				<label class="control-label col-lg-2" for="noOfOrdersMade">No of Orders Made</label>
				<div class="col-lg-10">
					<form:input id="noOfOrdersMade" path="noOfOrdersMade" type="text" class="form:input-large" />
				</div>
			</div> --%>
			
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<input type="submit" id="btnAdd" class="btn btn-primary" value="Add"/>
				</div>
			</div>

		</form:form>
	</section>
	
</body>

</html>