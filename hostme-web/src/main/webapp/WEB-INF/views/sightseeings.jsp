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
					<button type="button" class="btn btn-primary button" id="textColor"
						data-toggle="modal" data-target="#sightseeingCreate">
						<i class="fa fa-fw fa-plus-square-o"></i>
						<spring:message code="label.addSightseeing" />
					</button>
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
		<form:form modelAttribute="sightseeing"
			cssClass="form-horizontal groupForm">
			<!-- Modal -->
			<div class="modal fade" id="sightseeingCreate" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">
								<spring:message code="label.createSightseeing" />
							</h4>
						</div>
						<div class="modal-body">

							<div class="form-group">
								<label for="name" class="col-sm-2 control-label"> <spring:message
										code="label.sightseeingName" />
								</label>
								<div class="col-sm-10">
									<form:input path="name" cssClass="form-control" />
								</div>
							</div>

							<div class="form-group">
								<label for="address" class="col-sm-2 control-label">
									Address </label>
								<div class="col-sm-10">
									<form:input path="address" cssClass="form-control" />
								</div>
							</div>

							<div class="form-group">
								<label for="sightseeingType" class="col-sm-2 control-label">
									Sightseeing type</label>
								<div class="col-sm-10">
									<form:select path="sightseeingType">
										<option value="0">Sightseeing type</option>
										<c:forEach items="${sType}" var="type">
											<option value="${type}">${type}</option>
										</c:forEach>
									</form:select>
								</div>
							</div>
							<div class="form-group">
								<form:label path="city.country.country"
									class="col-sm-2 control-label">
									Country</form:label>
								<div class="col-sm-10">
									<form:select path="city.country.country">
										<option value="0">Select Country</option>
										<c:forEach items="${countries}" var="country">
											<option value="${country.country}">${country.country}</option>
										</c:forEach>
									</form:select>
								</div>
							</div>

							<div class="form-group">
								<form:label path="city.city" class="col-sm-2 control-label">
										City</form:label>
								<div class="col-sm-10">
									<form:select path="city.city">
										<option value="0">Select city</option>
										<c:forEach items="${cities}" var="city">
											<option value="${city.city}">${city.city}</option>
										</c:forEach>
									</form:select>
								</div>
							</div>

							<div class="form-group">
								<label for="website" class="col-sm-2 control-label">
									Website </label>
								<div class="col-sm-10">
									<form:input path="website" cssClass="form-control" />
								</div>
							</div>

							<div class="form-group">
								<label for="description" class="col-sm-2 control-label">
									<spring:message code="label.sightseeingDescription" />
								</label>
								<div class="col-sm-10">
									<form:input path="description" cssClass="form-control" />
								</div>
							</div>

							<div class="form-group">
								<label for="priceCategory.priceCategory"
									class="col-sm-2 control-label"> Price category </label>
								<div class="col-sm-10">
									<form:select path="priceCategory.priceCategory">
										<option value="0">Select price category</option>
										<c:forEach items="${priceCategories}" var="priceCategory">
											<option value="${priceCategory.priceCategory}">${priceCategory.priceCategory}</option>
										</c:forEach>
									</form:select>
								</div>
							</div>

						</div>
						<div class="modal-footer">
							<input type="submit" class="btn btn-success"
								value="Add new sightseeing" />
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
		</form:form>
	</section>
</body>
</html>