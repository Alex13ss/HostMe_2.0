<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel='stylesheet' href='resources/css/fullcalendar.min.css' />
<link rel="stylesheet" href="resources/css/dataTables.bootstrap.css"
	type="text/css" />
<link href="resources/css/style.css" rel="stylesheet" type="text/css" />

<script src="resources/js/jquery.dataTables.js" type="text/javascript"></script>
<script src="resources/js/event.js" type="text/javascript"></script>
<script src="resources/js/fnAjaxReload.js" type="text/javascript"></script>
<script src="resources/js/dataTables.bootstrap.js"
	type="text/javascript"></script>
<script src='resources/js/moment.min.js'></script>
<script src='resources/js/fullcalendar.min.js'></script>
<title>Events</title>
</head>
<body class="wysihtml5-supported">
	<section class="content-header">
		<h1>
			<spring:message code="label.events" />
		</h1>
		<security:authorize access="hasRole('USER')">
			<c:set var="role" value="USER" />
		</security:authorize>
		<security:authorize access="hasRole('MODERATOR')">
			<c:set var="role" value="MODERATOR" />
		</security:authorize>
		<div id="UserRole" style="display:none;">${role}</div>
	</section>
	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">
					<i class="fa fa-plus-square"></i>
					<spring:message code="label.EventMang" />
				</h3>
				<div style="margin-top: 10px;">
					<button type="button" class="btn btn-primary button" id="textColor"
						data-toggle="modal" data-target="#eventCreate">
						<i class="fa fa-fw fa-plus-square-o"></i>
						<spring:message code="label.addEvent" />
					</button>

				</div>
			</div>

			<!-- /.box-header -->
			<ul id="eventsTypesNav" class="nav nav-tabs">
				<security:authorize access="hasRole('MODERATOR')">
					<li id="all-events"><a href="#" data-toggle="tab"><spring:message
								code="label.events" /></a></li>
				</security:authorize>
				<security:authorize access="hasRole('USER')">
					<li id="my-events"><a href="#" data-toggle="tab"><spring:message
								code="label.Myevents" /></a></li>
					<li id="signed-events"><a href="#" data-toggle="tab"><spring:message
								code="label.SignedEvents" /></a></li>
				</security:authorize>
			</ul>
			<div class="box-body table-responsive">
				<select id="request_size" class="dataTableDropDown">
					<option value="10" selected="selected">10</option>
					<option value="25">25</option>
					<option value="50">50</option>
					<option value="100">100</option>
				</select>
				<table id="request_table_obtain"
					class="table table-bordered table-striped">

					<thead>
						<tr id="eventsTableHeader">
							<th headers="name"><spring:message code="label.eventTitle" /></th>
							<th headers="startDate"><spring:message
									code="label.startDate" /></th>
							<th headers="address"><spring:message code="label.location" /></th>
							<th headers="priceCategory"><spring:message
									code="label.price" /></th>
							<th headers="website"><spring:message code="label.website" /></th>
							<th headers="owner"><spring:message code="label.organizer" /></th>
							<th headers="status"><spring:message code="user.state" /></th>
							<th><spring:message code="label.action" /></th>
						</tr>
					</thead>
				</table>
				<div class="box-footer clearfix">
					<ul id="table_pages"
						class="pagination pagination-sm no-margin pull-right">
					</ul>
				</div>
				<div id='calendar'></div>
			</div>
		</div>
		<form:form modelAttribute="event" cssClass="form-horizontal groupForm">
			<!-- Modal -->
			<div class="modal fade" id="eventCreate" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">
								<spring:message code="label.createEvent" />
							</h4>
						</div>
						<div class="modal-body">

							<div class="form-group">
								<label for="name" class="col-sm-2 control-label"> <spring:message
										code="label.eventName" />
								</label>
								<div class="col-sm-10">
									<form:input path="name" cssClass="form-control" />
								</div>
							</div>

							<div class="form-group">
								<label for="address" class="col-sm-2 control-label"> <spring:message
										code="label.address" />
								</label>
								<div class="col-sm-10">
									<form:input path="address" cssClass="form-control" />
								</div>
							</div>


							<div class="form-group">
								<form:label path="city.country.country"
									class="col-sm-2 control-label">
									<spring:message code="label.country" />
								</form:label>
								<div class="col-sm-10">
									<form:select id="country" path="city.country.country">
										<option value="0"><spring:message
												code="label.countrySelect" /></option>
										<c:forEach items="${countries}" var="country">
											<option value="${country.country}">${country.country}</option>
										</c:forEach>
									</form:select>
								</div>
							</div>

							<div class="form-group">
								<form:label path="city.city" class="col-sm-2 control-label">
									<spring:message code="label.city" />
								</form:label>
								<div class="col-sm-10">
									<form:select id="city" path="city.city">
										<option value="0"><spring:message
												code="label.citySelect" /></option>

									</form:select>
								</div>
							</div>

							<div class="form-group">
								<label for="website" class="col-sm-2 control-label"> <spring:message
										code="label.website" />
								</label>
								<div class="col-sm-10">
									<form:input path="website" cssClass="form-control" />
								</div>
							</div>

							<div class="form-group">
								<label for="description" class="col-sm-2 control-label"><spring:message
										code="label.eventDescription" /> </label>
								<div class="col-sm-10">
									<form:input path="description" cssClass="form-control" />
								</div>
							</div>

							<div class="form-group">
								<label for="priceCategory.priceCategory"
									class="col-sm-2 control-label"><spring:message
										code="label.priceCategory" /></label>
								<div class="col-sm-10">
									<form:select path="priceCategory.priceCategory">
										<option value="0"><spring:message
												code="label.priceCategorySelect" /></option>
										<c:forEach items="${priceCategories}" var="priceCategory">
											<option value="${priceCategory.priceCategory}">${priceCategory.priceCategory}</option>
										</c:forEach>
									</form:select>
								</div>
							</div>

						</div>
						<div class="modal-footer">
							<input type="submit" class="btn btn-success"
								value="<spring:message code="label.addEvent" />" />
							<button type="button" class="btn btn-default"
								data-dismiss="modal">
								<spring:message code="label.close" />
							</button>
						</div>
					</div>
				</div>
			</div>
		</form:form>
	</section>
	<!-- /.content -->
</body>
</html>