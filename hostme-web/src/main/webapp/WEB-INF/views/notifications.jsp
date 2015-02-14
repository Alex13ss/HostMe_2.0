<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
<script type="text/javascript"
	src="resources/js/groups/notifications.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/groups.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="label.notifications" /></title>
</head>

<section class="content-header">
	<h1>
		<spring:message code="label.notifications" />
		<small><spring:message code="label.notifySlogan" /></small>
	</h1>
</section>

<div id="noNotify" style="display: none">
	<spring:message code="label.noNotifications" />
</div>

<section class="content">
	<div class="box box-success">
		<div class="box-header"></div>
		<div class="box-body">
			<ul class="timeline" id="timeline">
			</ul>
		</div>
	</div>
</section>
</html>