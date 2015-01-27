<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>

<script type="text/javascript" src="resources/js/group.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/groups.css">
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

			<div class="row">
				<div class="col-md-4">
					<div class="panel box box-success">
						<div class="panel-body">
							<h4>
								<span>Funny group image</span>
							</h4>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="box box-solid bg-light-blue">
						<div class="box-header">
							<h3 class="box-title">
								<spring:message code="label.description" />
							</h3>
						</div>
						<div class="box-body">
							<p>
								<c:out value="${group.groupDescription}" />
							</p>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div class="panel-title">
								<div class="row">
									<div class="col-md-6">
										<spring:message code="label.groupPubInfo" />
									</div>
									<div class="col-md-6">
										<div align="right">
											<button type="button" class="btn btn-success btn-xs"
												data-toggle="modal" data-target="#groupEditModal">Edit</button>
											<a href="<spring:url value="/group/remove/${group.id}" />"
												class="btn btn-danger btn-xs triggerRemove"> Del </a>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="panel-body">
							<p>
								<c:out value="Created at: ${group.createdAt}" />
								<br>
								<c:out
									value="Created by: ${group.creatorUser.firstName} 
									${group.creatorUser.lastName}" />
								<br>
								<c:if test="${!empty group.lastEditedAt}">
									<c:out value="Last edited: ${group.lastEditedAt}" />
								</c:if>
								<br>
								<c:if test="${!empty group.lastEditor}">
									<c:out
										value="Last editor: ${group.lastEditor.firstName} 
									${group.lastEditor.lastName}" />
								</c:if>
							</p>
						</div>
					</div>
				</div>
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

			<div class="row">
				<div class="col-md-12">
					<c:url var="conversationsUrl" value="/conversations">
						<c:param name="group_id" value="${group.id}" />
					</c:url>
					<%@ include file="conversations/latest.jsp"%>
				</div>
			</div>

		</div>
		<!-- /.box-body -->

	</div>
	<!-- /.box -->
</section>

<form:form modelAttribute="group" cssClass="form-horizontal groupForm">
	<!-- Modal -->
	<div class="modal fade" id="groupEditModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
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
							<form:textarea id="group-dscrptn-textarea"
								path="groupDescription" cssClass="form-control" rows="5" />
							<form:errors path="groupDescription" />
						</div>
					</div>

					<div style="display: none;">
						<div class="form-group">
							<label for="createdAt" class="col-sm-2 control-label">
								Created at: </label>
							<div class="col-sm-10">
								<form:input readonly="true" path="createdAt"
									cssClass="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="creator" class="col-sm-2 control-label">
								Creator: </label>
							<div class="col-sm-10">
								<form:input readonly="true" path="creatorUser.userId"
									cssClass="form-control" />
							</div>
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
</html>