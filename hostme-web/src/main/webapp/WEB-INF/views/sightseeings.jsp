<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<c:url value="/resources/js/jquery.dataTables.js"/>"></script>
<script src="<c:url value="/resources/js/sightseeing.js"/>"></script>
<script src="<c:url value="/resources/js/fnAjaxReload.js"/>"></script>
<script src="<c:url value="/resources/js/dataTables.bootstrap.js"/>"></script>
<title><spring:message code="label.sightseeings" /></title>
</head>

<body class="wysihtml5-supported">
	<section class="content-header">
		<h1>
			<spring:message code="label.sightseeings" />
		</h1>
	</section>
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<div class="box-title col-md-9">
					<i class="fa fa-camera-retro"></i>
					<spring:message code="label.sightseeings" />
				</div>
				<div class="box-title col-md-3" align="right">
					<button type="button" class="btn btn-primary btn-sm"
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
							<th><spring:message code="label.sightseeingName" /></th>
							<th><spring:message code="label.sightseeingDescription" /></th>
							<th><spring:message code="label.city" /></th>
							<th><spring:message code="label.priceCategory" /></th>
							<th><spring:message code="label.website" /></th>
							<th><spring:message code="label.sightseeingType" /></th>
							<th><spring:message code="label.delete" /></th>
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
								<label for="address" class="col-sm-2 control-label"> <spring:message
										code="label.address" />
								</label>
								<div class="col-sm-10">
									<form:input path="address" cssClass="form-control" />
								</div>
							</div>

							<div class="form-group">
								<label for="sightseeingType" class="col-sm-2 control-label">
									<spring:message code="label.sightseeingType" />
								</label>
								<div class="col-sm-10">
									<form:select path="sightseeingType">
										<option value="0"><spring:message
												code="label.sightseeingTypeSelect" /></option>
										<c:forEach items="${sType}" var="type">
											<option value="${type}">${type}</option>
										</c:forEach>
									</form:select>
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
										code="label.sightseeingDescription" /> </label>
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
								value="<spring:message code="label.addSightseeing" />" />
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
</body>
</html>