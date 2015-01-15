<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet"
	href="resources/css/sightseeing.css" />
<script type='text/javascript' src="resources/js/sightseeing.js"></script>
<title>Sightseeing</title>
</head>
<body class="skin-blue  pace-done">
	<section class="content-header">
	<h1>
		<spring:message code="label.sightseeings" />
	</h1>
	</section>
	<div class="box">
		<section class="content-header">
		<h3>List of all sightseeings</h3>
		<ol class="breadcrumb">
			<li><a href="<c:url value='create-sightseeing' />"><i
					class="fa fa-fw fa-plus-square-o"></i> <spring:message
						code="label.addSightseeing" /></a></li>
		</ol>
		</section>
		<div class="box-body table-responsive">
			<div id="example1_wrapper" class="dataTables_wrapper form-inline"
				role="grid">
				<table id="example1"
					class="table table-bordered table-striped dataTable">
					<thead>
						<tr role="row">
							<th>Name</th>
							<th>Description</th>
							<th>City</th>
							<th>Image</th>
							<th>Delete</th>
							<th>Edit</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sightseeing" items="${sightseeings}">
							<tr
								href="<c:url value="sightseeing"><c:param name="id" value="${sightseeing.id}" /></c:url>"
								class="clickableRow" class="odd">
								<td class=" "><c:out value="${sightseeing.name}">
									</c:out></td>
								<td class=" "><c:out value="${sightseeing.description}">
									</c:out></td>
								<td class=" "><c:out value="${sightseeing.city.city}">
									</c:out></td>
								<td class=" "><a href="${image.link}"><img
										width="150px" height="150px" src="${image.link}"></a></td>
								<td class=" "><a
									href="sightseeing/delete/${sightseeing.id}"><i
										class="fa fa-fw fa-trash-o"></i></a></td>
								<td class=" "><a
									href="<c:url value='update-sightseeing' />"><i
										class="fa fa-fw fa-pencil"></i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>