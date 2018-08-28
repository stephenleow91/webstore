<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="x-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta name="viewport" content="width=divce-width, initial-scale=1">
<title><spring:message code="addProduct.title"/></title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>

<body>
	<section>
		<div class="pull-right" style="padding-right:50px">
			<a href="?language=en">English</a>|<a href="?language=nl">Dutch</a>
			<a href="<c:url value="/logout"/>">Logout</a>
		</div>
	</section>
	
	<section>
		<div class="jumbotron">
			<h1><spring:message code="addProduct.section.top.h1"/></h1>
			<p><spring:message code="addProduct.section.top.p"/></p>
		</div>
	</section>

	<section class="container">
		<form:form method="POST" modelAttribute="newProduct" class="form-horizontal" enctype="multipart/form-data">
		<form:errors path="*" cssClass="alert alert-danger" element="div"/>
			<fieldset>
				<legend><spring:message code="addProduct.form.fieldset.legend"/></legend>
			</fieldset>
			
			<div class="form-group">
				<label class="control-label col-lg-2" for="productId"><spring:message code="addProduct.form.productId.label"/></label>
				<div class="col-lg-10">
					<form:input id="productId" path="productId" type="text" class="form:input-large" />
					<form:errors path="productId" cssClass="text-danger" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-lg-2" for="name"><spring:message code="addProduct.form.name.label"/></label>
				<div class="col-lg-10">
					<form:input id="name" path="name" type="text" class="form:input-large" />
					<form:errors path="name" cssClass="text-danger" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-lg-2 " for="unitPrice"><spring:message code="addProduct.form.unitPrice.label"/></label>
				<div class="col-lg-10">
					<form:input id="unitPrice" path="unitPrice" type="text" class="form:input-large" />
					<form:errors path="unitPrice" cssClass="text-danger" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-lg-2" for="manufacturer"><spring:message code="addProduct.form.manufacturer.label"/></label>
				<div class="col-lg-10">
					<form:input id="manufacturer" path="manufacturer" type="text" class="form:input-large" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-lg-2" for="category"><spring:message code="addProduct.form.category.label"/></label>
				<div class="col-lg-10">
					<form:input id="category" path="category" type="text" class="form:input-large" />
					<form:errors path="category" cssClass="text-danger" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-lg-2" for="unitsInStock"><spring:message code="addProduct.form.unitInStock.label"/></label>
				<div class="col-lg-10">
					<form:input id="unitsInStock" path="unitsInStock" type="text" class="form:input-large" />
					<form:errors path="unitsInStock" cssClass="text-danger" />
				</div>
			</div>
			
			<%-- <div class="form-group">
				<label class="control-label col-lg-2 col-lg-2" for="unitsInOrder">Unit in Order</label>
				<div class="col-lg-10">
					<form:input id="unitsInOrder" path="unitsInOrder" type="text" class="form:input-large" />
				</div>
			</div> --%>
			
			<div class="form-group">
				<label class="control-label col-lg-2" for="description"><spring:message code="addProduct.form.description.label"/></label>
				<div class="col-lg-10">
					<form:textarea id="description" path="description" rows="2"/>
				</div>
			</div>
			
			<%-- <div class="form-group">
				<label class="control-label col-lg-2" for="discontinued">Discontinued</label>
				<div class="col-lg-10">
					<form:checkbox id="discontinued" path="discontinued" />
				</div>
			</div> --%>
			
			<div class="form-group">
				<label class="control-label col-lg-2" for="condition"><spring:message code="addProduct.form.condition.label"/></label>
				<div class="col-lg-10">
					<form:radiobutton path="condition" value="New"/><spring:message code="addProduct.form.condition.new.value"/>
					<form:radiobutton path="condition" value="Old"/><spring:message code="addProduct.form.condition.old.value"/>
					<form:radiobutton path="condition" value="Refurbished"/><spring:message code="addProduct.form.condition.refurbished.value"/>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-lg-2" for="productImage"><spring:message code="addProduct.form.productImage.label"/></label>
				<div class="col-lg-10">
					<form:input id="productImage" path="productImage" type="file" class="form:input-large"/>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-lg-2" for="productUserManual"><spring:message code="addProduct.form.productUserManual.label"/></label>
				<div class="col-lg-10">
					<form:input id="productUserManual" path="productUserManual" type="file" class="form:input-large"/>
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