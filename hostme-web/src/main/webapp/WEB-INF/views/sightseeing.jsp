<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="resources/css/AdminLTE.css">
<link rel="stylesheet" href="css/bootstrap-image-gallery.css">
<title>Sightseeing: ${sightseeing.name}</title>
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
					<div class="col-md-3">
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
			</div>
		</div>

		<div class="col-md-13">
			<div class="box box-solid">
				<div class="box-header">
					<h3 class="box-title">Sightseeing description</h3>
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
		<div class="col-md-13">
			<div class="box box-solid">
				<div class="box-header">
					<h3 class="box-title">Photos</h3>
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
	</section>
</body>
</html>