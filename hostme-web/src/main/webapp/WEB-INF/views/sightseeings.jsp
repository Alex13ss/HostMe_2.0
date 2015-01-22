<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet"
	href="resources/css/sightseeing.css" />
<script src="resources/js/jquery.dataTables.js" type="text/javascript"></script>
<script type='text/javascript' src="resources/js/sightseeing.js"></script>
<script src="resources/js/fnAjaxReload.js" type="text/javascript"></script>
<script src="resources/js/dataTables.bootstrap.js"
	type="text/javascript"></script>
<title>Sightseeings</title>
</head>

<body class="wysihtml5-supported">

	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">
					<i class="fa fa-camera-retro"></i>
					<spring:message code="label.sightseeings" />
				</h3>
				<div style="margin-top: 10px;">
					<a href="<c:url value='create-sightseeing' />"
						class="btn btn-primary button" id="textColor"><i
						class="fa fa-fw fa-plus-square-o"></i> <spring:message
							code="label.addSightseeing" /><b></b></a>
				</div>
			</div>

			<!-- /.box-header -->
			<ul class="nav nav-tabs">
				<li id="all-sightseeings" class="active"
					onclick="allSightseeings(this)"><a href="#" data-toggle="tab"><spring:message
							code="label.sightseeings" /></a></li>
				<li class="" onclick="favouriteSightseeings(this)"><a href="#"
					data-toggle="tab"><spring:message
							code="label.favouriteSightseeings" /></a></li>
			</ul>
			<div class="box-body table-responsive">
				<table id="request_table_obtain"
					class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Name</th>
							<th>Description</th>
							<th>City</th>
							<th>Price</th>
							<th>Website</th>
							<th>Sightseeing type</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</section>
</body>
</html>