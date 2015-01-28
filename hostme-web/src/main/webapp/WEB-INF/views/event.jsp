<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
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
<body class="skin-blue  pace-done" style="min-height: 1293px;">
	<section class="content-header">
		<h1>
			<spring:message code="label.events" />
		</h1>
	</section>
	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-body">
				<div class="row">
					<div class="col-md-3">
						<div class="row col-md-12" style="padding-bottom: 5px">
							<h4>
								<strong>${event.name}</strong>
							</h4>
						</div>

					</div>
					<div class="col-md-4" style="padding-left: 0em;">
						<div class="panel box box-warning col-md-12"
							style="margin-top: 3em; padding-left: 0.3em;">
							<div class="box-header">
								<h4 class="box-title"
									style="padding-top: 0.8em; padding-bottom: 0em;">
									<spring:message code="label.genInfo" />
									:
								</h4>
							</div>
							<div id="collapseOne" class="panel-collapse in"
								style="height: auto;">
								<div class="box-body"
									style="padding-bottom: 0.5em; padding-left: 1.5em;">
									<div class="row"
										style="padding-top: 0.3em; padding-bottom: 0.3em">
										<div class="col-md-4" style="padding-right: 0em;">
											<spring:message code="label.country" />
											:
										</div>
										<div class="col-md-8 selected">${event.city.country.country}</div>
									</div>
									<div class="row" style="padding-bottom: 0.3em">
										<div class="col-md-4" style="padding-right: 0em;">
											<spring:message code="label.city" />
											:
										</div>
										<div class="col-md-8 selected">${event.city.city}</div>
									</div>
									<div class="row" style="padding-bottom: 0.3em">
										<div class="col-md-4" style="padding-right: 0em;">
											<spring:message code="label.address" />
											:
										</div>
										<div class="col-md-8 selected">${event.address}</div>
									</div>
									<div class="row" style="padding-bottom: 0.3em">
										<div class="col-md-4" style="padding-right: 0em;">
											<spring:message code="label.start/end" />
											:
										</div>
										<div class="col-md-8 selected">${event.startDate}/${event.endDate}</div>
									</div>
									<div class="row" style="padding-bottom: 0.3em">
										<div class="col-md-4" style="padding-right: 0em;">
											<spring:message code="label.price" />
											:
										</div>
										<div class="col-md-8 selected">${event.priceCategory.priceCategory}</div>
									</div>
									<div class="row" style="padding-bottom: 0.3em">
										<div class="col-md-4" style="padding-right: 0em;">
											<spring:message code="label.website" />
											:
										</div>
										<div class="col-md-8 selected">${event.website}</div>
									</div>
									<div class="row" style="padding-bottom: 0.3em">
										<div class="col-md-4" style="padding-right: 0em;">
											<spring:message code="label.owner" />
											:
										</div>
										<div class="col-md-8 selected">${event.owner.firstName}&nbsp;${event.owner.lastName}</div>
									</div>

								</div>
							</div>
						</div>
					</div>
					<div class="col-md-5">
						<div>
							<a href="event/delete/${event.id}" id="editbutton"><i
								class="fa fa-fw fa-trash-o"></i> <spring:message
									code="label.delete" /> </a> <a type="button" data-toggle="modal"
								data-target="#eventEdit" id="editbutton"> <i
								class="fa fa-fw fa-pencil"></i> <spring:message
									code="label.edit" />
							</a>
						</div>
						<div class="row col-md-12">
							<div class="callout callout-danger">
								<h4>
									<spring:message code="label.description" />
									:
								</h4>
								<p>${event.description}</p>
								<h4>
									<spring:message code="label.comment" />
									:
								</h4>
								<p>${event.comment}</p>
							</div>
							<div class="callout callout-warning">
								<h4>
									<spring:message code="label.attendees" />
									:
								</h4>
								<c:set var="leng" value="${fn:length(event.attendee)}" />
								<c:forEach var="attendee" items="${event.attendee}"
									varStatus="counter">
									<c:out value="${attendee.firstName} ${attendee.lastName}"></c:out>
									<c:if test="${counter.count lt leng}">
										<c:out value=","></c:out>
									</c:if>

								</c:forEach>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div style="margin-top: 20px;">
							<a href="" class="btn btn-primary button" id="leftButtonPosition">
								<i class="fa fa-plus"></i> <spring:message
									code="label.addRoutes" />
							</a>

						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<form:form modelAttribute="event" method="post"
		cssClass="form-horizontal groupForm">
		<!-- Modal -->
		<div class="modal fade" id="eventEdit" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">
							<spring:message code="label.edit" />
						</h4>
					</div>
					<div class="modal-body">

						<div class="form-group">
							<label for="name" class="col-sm-2 control-label"><spring:message
									code="label.eventTitle" />: </label>
							<div class="col-sm-10">
								<form:input path="name" cssClass="form-control" />
								<form:errors path="name" />
							</div>
						</div>

						<div class="form-group">
							<label for="startDate" class="col-sm-2 control-label"> <spring:message
									code="label.startDate" />:
							</label>
							<div class="col-sm-10">
								<form:textarea id="group-dscrptn-textarea" path="startDate"
									cssClass="form-control" />
								<form:errors path="startDate" />
							</div>
						</div>
						<div class="form-group">
							<label for="endDate" class="col-sm-2 control-label"><spring:message
									code="label.endDate" />: </label>
							<div class="col-sm-10">
								<form:textarea id="group-dscrptn-textarea" path="endDate"
									cssClass="form-control" />
								<form:errors path="endDate" />
							</div>
						</div>
						<div class="form-group">
							<form:label path="city.country.country"
								class="col-sm-2 control-label">
								<spring:message code="label.country" />
							</form:label>
							<div class="col-sm-10">
								<form:select id="country" path="city.country.country">
									<option value="${event.city.country.country}">${event.city.country.country}</option>
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
							<label for="address" class="col-sm-2 control-label"> <spring:message
									code="label.address" />:
							</label>
							<div class="col-sm-10">
								<form:textarea id="group-dscrptn-textarea" path="address"
									cssClass="form-control" />
								<form:errors path="address" />
							</div>
						</div>
						<div class="form-group">
							<label for="description" class="col-sm-2 control-label">
								<spring:message code="label.description" />:
							</label>
							<div class="col-sm-10">
								<form:textarea id="group-dscrptn-textarea" path="description"
									cssClass="form-control" />
								<form:errors path="description" />
							</div>
						</div>
						<div class="form-group">
							<label for="comment" class="col-sm-2 control-label"> <spring:message
									code="label.comment" />:
							</label>
							<div class="col-sm-10">
								<form:textarea id="group-dscrptn-textarea" path="comment"
									cssClass="form-control" />
								<form:errors path="comment" />
							</div>
						</div>
						<div class="form-group">
							<label for="website" class="col-sm-2 control-label"> <spring:message
									code="label.website" />:
							</label>
							<div class="col-sm-10">
								<form:textarea id="group-dscrptn-textarea" path="website"
									cssClass="form-control" />
								<form:errors path="website" />
							</div>
						</div>
						<div class="form-group">
							<label for="priceCategory.priceCategory"
								class="col-sm-2 control-label"> Price category </label>
							<div class="col-sm-10">
								<form:select path="priceCategory.priceCategory">
									<option value="${event.priceCategory.priceCategory}">${event.priceCategory.priceCategory}</option>
									<c:forEach items="${priceCategories}" var="priceCategory">
										<option value="${priceCategory.priceCategory}">${priceCategory.priceCategory}</option>
									</c:forEach>
								</form:select>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<input type="submit" class="btn btn-primary button"
							value=<spring:message code="label.save" />>
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<spring:message code="label.close" />
						</button>
					</div>
				</div>

			</div>
		</div>
	</form:form>
</body>
</html>