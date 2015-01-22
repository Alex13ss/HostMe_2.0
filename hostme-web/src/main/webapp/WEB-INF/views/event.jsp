<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
									style="padding-top: 0.8em; padding-bottom: 0em;">General
									information :</h4>
							</div>
							<div id="collapseOne" class="panel-collapse in"
								style="height: auto;">
								<div class="box-body"
									style="padding-bottom: 0.5em; padding-left: 1.5em;">
									<div class="row"
										style="padding-top: 0.3em; padding-bottom: 0.3em">
										<div class="col-md-4" style="padding-right: 0em;">Country:
										</div>
										<div class="col-md-8 selected">${event.city.country.country}</div>
									</div>
									<div class="row" style="padding-bottom: 0.3em">
										<div class="col-md-4" style="padding-right: 0em;">City:</div>
										<div class="col-md-8 selected">${event.city.city}</div>
									</div>

									<div class="row" style="padding-bottom: 0.3em">
										<div class="col-md-4" style="padding-right: 0em;">Address:
										</div>
										<div class="col-md-8 selected">${event.address}</div>
									</div>

									<div class="row" style="padding-bottom: 0.3em">
										<div class="col-md-4" style="padding-right: 0em;">
											Start/End date:</div>
										<div class="col-md-8 selected">${event.startDate}/${event.endDate}</div>
									</div>

									<div class="row" style="padding-bottom: 0.3em">
										<div class="col-md-4" style="padding-right: 0em;">Price
											range</div>
										<div class="col-md-8 selected">${event.priceCategory.priceCategory}</div>
									</div>

									<div class="row" style="padding-bottom: 0.3em">
										<div class="col-md-4" style="padding-right: 0em;">
											Website:</div>
										<div class="col-md-8 selected">${event.website}</div>
									</div>
									<div class="row" style="padding-bottom: 0.3em">
										<div class="col-md-4" style="padding-right: 0em;">Owner</div>
										<div class="col-md-8 selected">${event.owner.firstName}&nbsp;${event.owner.lastName}</div>
									</div>

								</div>
							</div>
						</div>
					</div>
					<div class="col-md-5">
						<div class="row col-md-12" style="padding-top: 3em;">

							<div class="callout callout-danger">
								<h4>Description:</h4>
								
								<p>${event.comment}</p>
							</div>
							<div class="callout callout-warning">
								<h4>Attendees:</h4>
								<p>${event.attendee}</p>
							</div>
						</div>
					</div>
				</div>


				<div class="row">
					<div class="col-md-12">
						<div style="margin-top: 20px;">

							<a href="" class="btn btn-primary button"><i class="fa fa-edit"></i>
								Edit</a> <a href=""
								class="btn btn-primary button"> Add to routes</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>