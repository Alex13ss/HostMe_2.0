<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<html>
<head>
<script type="text/javascript"
	src="resources/js/groups/notifications.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/groups.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Your notifications</title>
</head>

<section class="content-header">
	<h1>
		<spring:message code="label.notifications" />
		<small>Look at it!</small>
	</h1>
</section>

<!-- Main content -->
<section class="content">

	<div class="box box-primary">
		<div class="col-md-12">

			<ul class="timeline" id="timeline">
			</ul>

		</div>
	</div>
	<!-- /.box-body -->

</section>
</html>