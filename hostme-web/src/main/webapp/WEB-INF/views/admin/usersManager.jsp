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
<link rel="stylesheet" type="text/css"
	href="resources/css/dataTables.bootstrap.css" />

<title><spring:message code="admin.usersManager" /></title>
</head>
<body class="wysihtml5-supported">
	<section class="content-header">
		<h1>
			<spring:message code="admin.usersManager" />
		</h1>
	</section>

	<section class="content">
		<div class="box box-primary">

			<!-- /.box-header -->
			<ul class="nav nav-tabs">
				<li id="all-users" class="active" onclick="allUsers(this)"><a
					href="#" data-toggle="tab"><spring:message code="admin.users" /></a></li>
			</ul>
			<div class="box-body table-responsive">
				<table id="request_table_obtain"
					class="table table-bordered table-striped">
					<thead>
						<tr>
							<th style="width:20%"><spring:message code="label.login" /></th>
							<th style="width:20%"><spring:message code="user.name" /></th>
							<th style="width:15%"><spring:message code="user.role" /></th>
							<th style="width:15%"><spring:message code="user.resetPass" /></th>
							<th style="width:15%"><spring:message code="user.delete" /></th>
							<th style="width:15%"><spring:message code="user.action" /></th>
						</tr>
					</thead>

				</table>
			</div>
		</div>
	</section>
</body>
</html>