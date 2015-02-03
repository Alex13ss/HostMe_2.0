<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/css/sightseeing.css"/>"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery.dataTables.js"/>"></script>
<script src="<c:url value="/resources/js/admin/usersManager.js"/>"></script>
<script src="<c:url value="/resources/js/fnAjaxReload.js"/>"></script>
<script src="<c:url value="/resources/js/dataTables.bootstrap.js"/>"></script>

<title>Users</title>
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
							<th><spring:message code="label.login" /></th>
							<th><spring:message code="user.name" /></th>
							<th><spring:message code="user.state" /></th>
							<th><spring:message code="user.role" /></th>
						</tr>
					</thead>

				</table>
			</div>
		</div>
	</section>
</body>
</html>