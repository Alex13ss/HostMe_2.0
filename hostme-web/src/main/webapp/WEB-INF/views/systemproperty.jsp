<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<c:url value="/resources/js/jquery.dataTables.js"/>"></script>
<script src="<c:url value="/resources/js/SystemProperties.js"/>"></script>
<script src="<c:url value="/resources/js/fnAjaxReload.js"/>"></script>
<script src="<c:url value="/resources/js/dataTables.bootstrap.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.validate.js"/>"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="admin.systemProperties" /></title>
</head>
<body>
	<section class="content-header">
		<h1>
			<spring:message code="admin.systemProperties" />
		</h1>
	</section>
	<section class="content">

		<div class="box box-primary" style="padding-bottom: 15px;">

			<div class="box-body" style="margin: auto">

				<form:form method="post" modelAttribute="systemproperty" cssClass="form-horizontal groupForm" >
					<div class="row" style="margin-top: 1.5em; margin-bottom: 0.7em;">
						<div class="form-group">
							<label for="propKey" class="col-sm-3 control-label" style=" text-align: center;"
								>${systemproperty.propKey}: </label>
							<div class="col-sm-9">
								<form:input path="value" cssClass="form-control" />
								<form:errors path="value" />
							</div>
						</div>
					</div>
					<div class="row"
						style="margin-top: 1.5em; display: none;">
						<div class="form-group">
							<div class="col-sm-9">
								<form:input path="propertyId" readonly="true"
									cssClass="form-control" />
								<form:errors path="propertyId" readonly="true" />
							</div>
						</div>
					</div>
					<div class="row"
						style="margin-top: 1.5em; display: none;">
						<div class="form-group">
							<div class="col-sm-9">
								<form:input path="propKey" readonly="true"
									cssClass="form-control" />
								<form:errors path="propKey" readonly="true" />
							</div>
						</div>
					</div>
					<div style="margin: auto; float: right;">
						<button type="submit" class="btn btn-primary button">
							<spring:message code="label.save" />
						</button>
					</div>
				</form:form>
			</div>
		</div>
	</section>
</body>
</html>