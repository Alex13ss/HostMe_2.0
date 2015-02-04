<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<html>
<head>
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

		<ul class="timeline">

			<!-- timeline time label -->
			<li class="time-label"><span class="bg-red"> 27 Jan. 1989
			</span></li>
			<!-- /.timeline-label -->

			<!-- timeline item -->
			<li>
				<!-- timeline icon --> <i class="fa fa-envelope bg-blue"></i>
				<div class="timeline-item">
					<span class="time"><i class="fa fa-clock-o"></i> 12:05</span>

					<h3 class="timeline-header">
						<a href="#">Support Team</a> ...
					</h3>

					<div class="timeline-body"><c:out value="Msg: ${notifications.notifyMessage}" /></div>

					<div class='timeline-footer'>
						<a class="btn btn-primary btn-xs">...</a>
					</div>
				</div>
			</li>
			<li>
				<!-- timeline icon --> <i class="fa fa-envelope bg-blue"></i>
				<div class="timeline-item">
					<span class="time"><i class="fa fa-clock-o"></i> 12:05</span>

					<h3 class="timeline-header">
						<a href="#">Support Team</a> ...
					</h3>

					<div class="timeline-body">... Content goes here</div>

					<div class='timeline-footer'>
						<a class="btn btn-primary btn-xs">...</a>
					</div>
				</div>
			</li>
			<li>
				<!-- timeline icon --> <i class="fa fa-envelope bg-blue"></i>
				<div class="timeline-item">
					<span class="time"><i class="fa fa-clock-o"></i> 12:05</span>

					<h3 class="timeline-header">
						<a href="#">Support Team</a> ...
					</h3>

					<div class="timeline-body">... Content goes here</div>

					<div class='timeline-footer'>
						<a class="btn btn-primary btn-xs">...</a>
					</div>
				</div>
			</li>
			<!-- END timeline item -->

		</ul>

	</div>
	<!-- /.box-body -->


</section>
</html>