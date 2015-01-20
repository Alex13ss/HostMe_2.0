<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>

<script type="text/javascript" src="resources/js/group.js"></script>

<link rel="stylesheet" type="text/css"
	href="resources/css/conversations.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Group</title>

</head>

<section class="content-header">
	<h1>
		Group<small>Have a fun!</small>
	</h1>
</section>

<!-- Main content -->
<section class="content">

	<div class="box box-primary">

		<div class="box-header">
			<h3 class="box-title col-md-10">
				<i class="fa fa-fw fa-users"></i>
				<c:out value="${group.groupName}" />
			</h3>
		</div>
		<!-- /.box-header -->

		<div class="box-body">

			<c:if test="${groupCreated eq true}">
				<div class="alert alert-success" style="margin-right: 15px;"
					align="center">
					<h3>Welcome to your new group! Create conversations and have a
						fun! :)</h3>
				</div>
			</c:if>

			Here will be group info.

			<div align="right">
				<a href="<spring:url value="/group/remove/${group.id}" />"
					class="btn btn-danger triggerRemove"> Remove </a>
			</div>

			<!-- Modal -->
			<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Remove group</h4>
						</div>
						<div class="modal-body">
							<div class="callout callout-danger" id="alert" align="center">
								<font size="5">Are you sure you want to destroy this
									group???</font>
							</div>
						</div>
						<div class="modal-footer">
							<a href="" class="btn btn-danger removeBtn">Remove</a>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Cancel</button>
						</div>
					</div>
				</div>
			</div>

		</div>
		<!-- /.box-body -->





	</div>
	<!-- /.box -->

</section>

<body>

	<br />

	<p>Begin Conversations block</p>
	<c:url var="conversationsUrl" value="/conversations">
		<c:param name="group_id" value="${group.id}" />
	</c:url>
	<%@ include file="conversations/latest.jsp"%>
	<p>End Conversations block</p>

</body>
</html>