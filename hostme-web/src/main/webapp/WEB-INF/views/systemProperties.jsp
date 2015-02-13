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
<link rel="stylesheet" type="text/css"
	href="resources/css/dataTables.bootstrap.css" />

<title><spring:message code="admin.systemProperties" /></title>
</head>
<body class="wysihtml5-supported">
	<section class="content-header">
		<h1>
			<spring:message code="admin.systemProperties" />
		</h1>
	</section>

	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<div style="margin-top: 10px;">
					<button type="button" class="btn btn-primary button" id="textColor"
						data-toggle="modal" data-target="#systemPropCreate" style="margin-right:20px;">
						<i class="fa fa-fw fa-plus-square-o"></i>
						<spring:message code="admin.addProp" />
					</button>

				</div>
			</div>
			<div class="box-body table-responsive">
				<table id="request_table_obtain"
					class="table table-bordered table-striped">
					<thead>
						<tr>
							<th><spring:message code="admin.propertyKey" /></th>
							<th><spring:message code="admin.propertyValue" /></th>
							<th style="width:5%"><spring:message code="label.edit" /></th>
						</tr>
					</thead>

				</table>
			</div>
		</div>
		<form:form modelAttribute="systemproperties" cssClass="form-horizontal groupForm">
			<!-- Modal -->
			<div class="modal fade" id="systemPropCreate" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">
								<spring:message code="admin.addProp" />
							</h4>
						</div>
						<div class="modal-body">

							<div class="form-group">
								<label for="propKey" class="col-sm-3 control-label"> <spring:message
										code="admin.propertyKey" />
								</label>
								<div class="col-sm-9">
									<form:input path="propKey" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="value" class="col-sm-3 control-label"> <spring:message
										code="admin.propertyValue" />
								</label>
								<div class="col-sm-9">
									<form:input path="value" cssClass="form-control" />
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<input type="submit" class="btn btn-primary"
								value="<spring:message code="admin.addProp"/>" />
							<button type="button" class="btn btn-default"
								data-dismiss="modal">
								<spring:message code="label.close" />
							</button>
						</div>
					</div>
				</div>
		</form:form>
	</section>
</body>
</html>
