<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script
	src="<c:url value="/resources/js/plugins/slimScroll/jquery.slimscroll.min.js"/>"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/sightseeings/sightseeing.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.validate.js"/>"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/sightseeings/sightseeing.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/oka_slider_model.css"/>" />
<script src="<c:url value="/resources/js/oka_slider_model.js"/>"></script>
<title><spring:message code="label.sightseeing" />:
	${sightseeing.name}</title>
</head>
<script>
	$(document).ready(
			function() {
				loadPostsAjax(<c:out value="${sightseeing.id}"/>);
				$("#sendMsg").click(
						function() {
							sendMessage(<c:out value="${sightseeing.id}"/>, $(
									"#userMsg").val());
						});
			});
</script>
<body class="skin-blue pace-done">
	<section class="content-header">
	<h1>
		<spring:message code="label.sightseeing" />
	</h1>
	</section>
	<!-- Main content -->
	<section class="content">
	<div class="box box-primary">
		<div class="box-header">
			<div class="box-title col-md-8">
				<i class="fa fa-camera-retro"></i>
				<c:out value="${sightseeing.name}" />
			</div>
			<div class="box-title col-md-4">
				<div align="right">
					<security:authorize access="hasRole('USER')">
						<c:if test="${isFavourite eq false}">
							<a href="<spring:url value="/like/${sightseeing.id}" />"
								class="btn btn-default btn-sm"><i class="fa fa-star-o"></i>${rating}
								<spring:message code="label.like" /> </a>
						</c:if>
						<c:if test="${isFavourite eq true}">
							<a href="<spring:url value="/unlike/${sightseeing.id}" />"
								class="btn btn-default btn-sm"><i class="fa fa-star"></i>${rating}
								<spring:message code="label.unlike" /> </a>
						</c:if>
					</security:authorize>
					<security:authorize access="hasRole('MODERATOR')">
						<button type="button" class="btn btn-default btn-sm"
							data-toggle="modal" data-target="#sightseeingEdit">
							<i class="fa fa-fw fa-edit"></i>
							<spring:message code="label.editSightseeing" />
						</button>
						<a
							href="<spring:url value="sightseeing/delete/${sightseeing.id}" />"
							class="btn btn-default btn-sm triggerRemove"><i
							class="fa fa-fw fa-trash-o"></i> <spring:message
								code="label.delete" /> </a>
					</security:authorize>
				</div>
			</div>
		</div>
		<div class="box-body">
			<div class="row">
				<div class="col-md-6">
					<div class="col-xs-12 .col-sm-6 .col-lg-8">
						<c:forEach var="image" begin="0" end="0"
							items="${sightseeing.image}">
							<a href="${image_url}${image.link}" data-lightbox="images"> <img
								src="${image_url}${image.link}" />
							</a>
						</c:forEach>
					</div>
				</div>
				<div></div>
				<div class="col-md-6">
					<div class="box-body">
						<div class="callout callout-info">
							<h4>
								<spring:message code="label.location" />
							</h4>
							<p>${sightseeing.address},${sightseeing.city.city},
								${sightseeing.city.country.country}</p>
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
		</div>
	</div>
	<div class="col-md-12">
		<div class="box box-solid">
			<div class="box-header">
				<h3 class="box-title">
					<spring:message code="label.sightseeingDescription" />
				</h3>
			</div>
			<div class="box-body">
				<dl>
					<dd>${sightseeing.description}</dd>
				</dl>
			</div>
		</div>
	</div>


	<div class="col-md-6">
		<div class="box box-solid">
			<div class="box-header">
				<h3 class="box-title">
					<spring:message code="label.photos" />
				</h3>
			</div>
			<!-- /.box-header -->
			<div class="slider_model demo-8">
				<div class="slider_model_box">
					<c:forEach var="image" items="${sightseeing.image}">
						<img src="${image_url}${image.link}" />
					</c:forEach>
				</div>
			</div>
			<!-- /.box-body -->
		</div>
		<div class="box box-solid">
			<dl>
				<dd>
					<form:form method="post" action="addPhotosToSight"
						modelAttribute="sightseeing" enctype="multipart/form-data">
						<input type="hidden" value="${sightseeing.id}" name="id" />
						<div class="form-group">
							<label for="exampleInputFile">
								<div class="box-body">
									<h4 class="box-title">
										<spring:message code="label.addPhotos" />
									</h4>
									<h5>
										(
										<spring:message code="label.savePhotoHotKey" />
										)
									</h5>
							</label> <br> <input type="file" name="file" class="multi" multiple
								accept="gif|jpg|png" data-maxfile="10000" data-maxsize="50000" />
							<br>
							<button type="submit" class="btn btn-primary">
								<spring:message code="label.savePhotos" />
							</button>
						</div>
					</form:form>
				</dd>
			</dl>
		</div>

	</div>
	<div class="col-md-6">
		<div class="box">
			<div class="box-header">
				<i class="fa fa-comments-o"></i>
				<h3 class="box-title">
					<spring:message code="label.comment" />
				</h3>
			</div>
			<div class="box-body chat" id="chat-box"></div>
			<!-- /.chat -->
			<div class="box-footer">
				<div class="input-group">
					<input class="form-control" id="userMsg"
						placeholder="Type message..." />
					<div class="input-group-btn">
						<button class="btn btn-success" id="sendMsg" style="height: 34px;">
							<i class="fa fa-plus"></i>
						</button>
					</div>
				</div>
			</div>
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
								<form:select class="drop-menu" path="sightseeingType">
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
								<form:select class="drop-menu" id="country"
									path="city.country.country">
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
								<form:select class="drop-menu" id="city" path="city.city">
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
								<form:textarea path="description" cssClass="form-control" />
							</div>
						</div>

						<div class="form-group">
							<label for="priceCategory.priceCategory"
								class="col-sm-2 control-label"> <spring:message
									code="label.priceCategory" />
							</label>
							<div class="col-sm-10">
								<form:select class="drop-menu"
									path="priceCategory.priceCategory">
									<option value="${sightseeing.priceCategory.priceCategory}">${sightseeing.priceCategory.priceCategory}</option>
									<c:forEach items="${priceCategories}" var="priceCategory">
										<option value="${priceCategory.priceCategory}">${priceCategory.priceCategory}</option>
									</c:forEach>
								</form:select>
							</div>
						</div>
						<div class="form-group">
							<label for="status" class="col-sm-2 control-label"> <spring:message
									code="label.status" />
							</label>
							<div class="col-sm-10">
								<form:select class="drop-menu" path="status">
									<option value="${sightseeing.status}">${sightseeing.status}</option>
									<c:forEach items="${status}" var="status">
										<option value="${status}">${status}</option>
									</c:forEach>
								</form:select>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<input type="submit" class="btn btn-primary"
							value="<spring:message code="label.editSightseeing" />" />
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<spring:message code="label.close" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</form:form>


	<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<spring:message code="label.sightseeingDelete" />
					</h4>
				</div>
				<div class="modal-body">
					<div class="callout callout-danger" id="alert" align="center">
						<font size="5"><spring:message code="label.sightseeingDeleteMessage" /></font>
					</div>
				</div>
				<div class="modal-footer">
					<a href="" class="btn btn-primary removeBtn"><spring:message
							code="label.delete" /></a>
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<spring:message code="label.cancel" />
					</button>
				</div>
			</div>
		</div>
	</div>
	</section>
</body>
</html>