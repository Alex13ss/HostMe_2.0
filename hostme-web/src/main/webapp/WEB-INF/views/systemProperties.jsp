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
<script src="<c:url value="/resources/js/admin/usersManager.js"/>"></script>
<script src="<c:url value="/resources/js/fnAjaxReload.js"/>"></script>
<script src="<c:url value="/resources/js/dataTables.bootstrap.js"/>"></script>
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

		<div class="box box-primary">

			<div class="box-body" style="margin: auto;">

				<form:form method="post" modelAttribute="systemproperties">
					<div class="row" style="margin-top: 1.5em;">
						<div class="form-group">
							<label for="imagePath" class="col-sm-3 control-label"
								style="margin-top: 0.7em"><spring:message
									code="admin.imagePath" />: </label>
							<div class="col-sm-9">
								<form:input path="imagePath" cssClass="form-control" />
								<form:errors path="imagePath" />
							</div>
						</div>
					</div>
					<div class="row" style="margin-top: 1.5em">
						<div class="form-group">
							<label for="imageURL" class="col-sm-3 control-label"
								style="margin-top: 0.7em"><spring:message
									code="admin.imageURL" />: </label>
							<div class="col-sm-9">
								<form:input path="imageURL" cssClass="form-control" />
								<form:errors path="imageURL" />
							</div>
						</div>
					</div>
					<div class="row" style="margin-top: 1.5em">
						<div class="form-group">
							<label for="emailPass" class="col-sm-3 control-label"
								style="margin-top: 0.7em"><spring:message
									code="admin.emailPass" />: </label>
							<div class="col-sm-9">
								<form:input path="emailPass" cssClass="form-control" />
								<form:errors path="emailPass" />
							</div>
						</div>
					</div>
					<div class="row" style="margin-top: 1.5em">
						<div class="form-group">
							<label for="emailLogin" class="col-sm-3 control-label"
								style="margin-top: 0.7em"><spring:message
									code="admin.emailLogin" />: </label>
							<div class="col-sm-9">
								<form:input path="emailLogin" cssClass="form-control" />
								<form:errors path="emailLogin" />
							</div>
						</div>
					</div>
					<div class="row" style="margin-top: 1.5em; margin-bottom: 1.5em">
						<div class="form-group">
							<label for="baseURL" class="col-sm-3 control-label"
								style="margin-top: 0.7em"><spring:message
									code="admin.baseurl" />: </label>
							<div class="col-sm-9">
								<form:input path="baseURL" cssClass="form-control" />
								<form:errors path="baseURL" />
							</div>
						</div>
					</div>
					<div class="modal-footer" style="margin-bottom: auto;">
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
