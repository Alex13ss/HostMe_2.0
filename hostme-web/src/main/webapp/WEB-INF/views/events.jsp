<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html style="min-height: 682px;">
<head>
<meta charset="UTF-8">
<link href="resources/css/dataTables.bootstrap.css" rel="stylesheet"
	type="text/css" />
<script src="resources/js/jquery.dataTables.js" type="text/javascript"></script>
<script src="resources/js/event.js" type="text/javascript"></script>
<script src="resources/js/fnAjaxReload.js" type="text/javascript"></script>
<script src="resources/js/dataTables.bootstrap.js"
	type="text/javascript"></script>
<title>Events</title>
</head>
<body class="wysihtml5-supported">

	<!--  	<video id="bgvid" autoplay loop poster="resources/images/nature.jpg">
		<source src="resources/video/mp4/nature1.mp4" type="video/mp4">
	</video> -->


	<section class="content-header">
		<h1>
			<spring:message code="label.events" />
		</h1>
	</section>

	<!-- Main content -->
	<section class="content">
		<div class="row">

			<div class="box">
				<div class="box-header">

					<h3 class="box-title">
						<i class="fa fa-plus-square"></i> Events management

					</h3>

					<div id="addButtonEvent" style="margin-top: 10px;">
						<a href="event-creation" class="btn btn-default btn-sm"><i
							class="fa fa-fw fa-plus-square-o"></i> <spring:message
								code="label.addEvent" /></a>

						<button onclick="refresh()" class="btn btn-default btn-sm">
							<i class="fa fa-fw fa-refresh"></i>
							<spring:message code="label.Refresh" />
						</button>
					</div>


				</div>

				<!-- /.box-header -->
				<ul class="nav nav-tabs">

					<li id="all-events" class="active" 
						onclick="allEvents(this)"><a
						href="#" data-toggle="tab"><spring:message code="label.events" /></a></li>

					<!--  		<li class="" onclick="initialize_request_to_me(this)"><a
						href="#" data-toggle="tab"><spring:message
								code="label.Myevents" /></a></li>
					<li class="" onclick="initialize_request_to_me(this)"><a
						href="#" data-toggle="tab"><spring:message
								code="label.SignedEvents" /></a></li> -->

				</ul>

				

				<div class="box-body table-responsive">
					<table id="request_table_obtain"
						class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>Title</th>
								<th>Starts</th>
								<th>Ends</th>
								<th>Price</th>
								<th>Website</th>
								<th>Status</th>
								<th>Attendees</th>
								<th>Comments</th>
							</tr>
						</thead>
				<!-- 	<tbody>
							<c:forEach var="events" items="${events}">
								<tr>
									<td><c:out value="${events.description}">
										</c:out></td>
									<td><c:out value="${events.startDate}">
										</c:out></td>

									<td><c:out value="${events.address}">
										</c:out></td>
									<td><c:out
											value="${events.priceCategory.getPriceCategory()}">
										</c:out></td>
									<td><c:out value="${events.website}">
										</c:out></td>
									<td><c:out value="${events.owner.getLogin()}">
										</c:out></td>

									<td><c:out value="${events.attendee}">
										</c:out></td>
									<td><c:out value="${events.comment}">
										</c:out></td>
									

								</tr>
							</c:forEach>
						</tbody>  -->	
					</table>
				</div>

			</div>
			<!-- /.box-body -->
		</div>
		<!-- /.box -->

	</section>
	<!-- /.content -->


</body>
</html>