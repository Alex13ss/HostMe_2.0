<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<c:url value="/resources/js/sightseeing.js"/>"></script>
<title><spring:message code="label.sightseeing" />:
	${sightseeing.name}</title>
</head>
<body class="skin-blue  pace-done" style="min-height: 1293px;">
	<section class="content-header">
	<h1>
		<spring:message code="label.sightseeing" />
	</h1>
	</section>
	<!-- Main content -->
	<section class="content">

	<div>
		<div class="box box-primary">

			<div class="box-body">
				<div class="row">
					<div class="row col-md-12"></div>
					<div class="col-md-4">
						<div class="box-header">
							<h3 class="box-title"></h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<div class="row col-md-12" style="padding-bottom: 5px">
								<h4>
									<strong>${sightseeing.name}</strong>
								</h4>
								<c:forEach var="image" items="${sightseeing.image}">
									<a href="${image.link}" data-lightbox="images"> <img
										src="${image.link}" />
									</a>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="box-header">
							<h3 class="box-title"></h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<div class="callout callout-info">
								<h4>
									<spring:message code="label.country" />
								</h4>
								<p>${sightseeing.city.country.country}</p>
								<h4>
									<spring:message code="label.city" />
								</h4>
								<p>${sightseeing.city.city}</p>
								<h4>
									<spring:message code="label.address" />
								</h4>
								<p>${sightseeing.address}</p>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="box-header">
							<h3 class="box-title"></h3>
						</div>
						<div class="box-body">
							<div class="callout callout-info">
								<h4>
									<spring:message code="label.sightseeingType" />
								</h4>
								<p>${sightseeing.sightseeingType}</p>
								<h4>
									<spring:message code="label.priceCategory" />
								</h4>
								<p>${sightseeing.priceCategory.priceCategory}</p>
								<h4>
									<spring:message code="label.website" />
								</h4>
								<p>
									<a href="${sightseeing.website}">${sightseeing.website}</a>
								</p>
							</div>
						</div>
					</div>
				</div>
				<div>
					<a href="sightseeing/delete/${sightseeing.id}"><i
						class="fa fa-fw fa-trash-o"></i> <spring:message
							code="label.delete" /> </a> <a> <i class="fa fa-fw fa-pencil"
						button type="button" class="btn btn-success btn-md"
						data-toggle="modal" data-target="#sightseeingEdit"></i> <spring:message
							code="label.editSightseeing" />
					</a>
				</div>
			</div>
		</div>
		<div class="col-md-12">
			<div class="box box-solid">
				<div class="box-header">
					<h3 class="box-title">
						<spring:message code="label.sightseeingDescription" />
					</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<dl>
						<dd>${sightseeing.description}</dd>
					</dl>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
		<div class="col-md-12">
			<div class="box box-solid">
				<div class="box-header">
					<h3 class="box-title">
						<spring:message code="label.photos" />
					</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<dl>
						<dd>
							<div id="links">
								<c:forEach var="image" items="${sightseeing.image}">
									<a href="${image.link}"><img width="150px" height="150px"
										src="${image.link}"></a>
								</c:forEach>
							</div>
						</dd>
					</dl>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
	</div>

	<form:form modelAttribute="sightseeing"
		cssClass="form-horizontal groupForm">
		<!-- Modal -->
		<div class="modal fade" id="sightseeingEdit" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">
							<spring:message code="label.editSightseeing" />
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
									<option value="${sightseeing.sightseeingType}">${sightseeing.sightseeingType}</option>
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
									<option value="${sightseeing.city.country.country}">${sightseeing.city.country.country}</option>
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
									<option value="${sightseeing.city.city}">${sightseeing.city.city}</option>
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
							<label for="description" class="col-sm-2 control-label">
								<spring:message code="label.sightseeingDescription" />
							</label>
							<div class="col-sm-10">
								<form:input path="description" cssClass="form-control" />
							</div>
						</div>

						<div class="form-group">
							<label for="priceCategory.priceCategory"
								class="col-sm-2 control-label"> <spring:message
									code="label.priceCategory" />
							</label>
							<div class="col-sm-10">
								<form:select path="priceCategory.priceCategory">
									<option value="${sightseeing.priceCategory.priceCategory}">${sightseeing.priceCategory.priceCategory}</option>
									<c:forEach items="${priceCategories}" var="priceCategory">
										<option value="${priceCategory.priceCategory}">${priceCategory.priceCategory}</option>
									</c:forEach>
								</form:select>
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<input type="submit" class="btn btn-success"
							value="<spring:message code="label.editSightseeing" />" />
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<spring:message code="label.close" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</form:form> </section>
</body>
</html>