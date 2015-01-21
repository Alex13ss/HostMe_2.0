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
			<h3 class="box-title col-md-9">
				<i class="fa fa-fw fa-users"></i>
				<c:out value="${group.groupName}" />
			</h3>
			<h3 class="box-title col-md-3">
				<small><c:out value="Created: ${group.createdAt}" /> <br>
					<c:if test="${!empty group.lastEditedAt}">
						<c:out value="Last edited: ${group.lastEditedAt}" />
					</c:if></small>
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
			<c:if test="${groupEdited eq true}">
				<div class="alert alert-warning" style="margin-right: 15px;"
					align="center">
					<h3>Group editing was successful!</h3>
				</div>
			</c:if>

			<div class="alert alert-info" style="margin-right: 15px;">
				<c:out value="${group.groupDescription}" />
			</div>

			<div align="right">
				<button type="button" class="btn btn-success btn-md"
					data-toggle="modal" data-target="#groupEditModal">Edit</button>
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

	<form:form modelAttribute="group" cssClass="form-horizontal groupForm">
		<!-- Modal -->
		<div class="modal fade" id="groupEditModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Group editing...</h4>
					</div>
					<div class="modal-body">

						<div class="form-group">
							<label for="groupName" class="col-sm-2 control-label">
								Name: </label>
							<div class="col-sm-10">
								<form:input path="groupName" cssClass="form-control" />
								<form:errors path="groupName" />
							</div>
						</div>

						<div class="form-group">
							<label for="groupDescription" class="col-sm-2 control-label">
								Description: </label>
							<div class="col-sm-10">
								<form:input path="groupDescription" cssClass="form-control" />
								<form:errors path="groupDescription" />
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<input type="submit" class="btn btn-success"
							value="Finish editing!" />
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	</form:form>

</body>
</html>