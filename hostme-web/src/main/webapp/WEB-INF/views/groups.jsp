<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>

<script type="text/javascript" src="resources/js/group.js"></script>

<title>Groups</title>

</head>
<body class="wysihtml5-supported">

	<section class="content-header">
		<h1>
			Groups<small>Conversations with minded people</small>
		</h1>
	</section>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">

			<div class="box-header">

				<h3 class="box-title col-md-10">
					<i class="fa fa-fw fa-list-alt"></i> Choose group and have a fun
					and useful conversations!
				</h3>

				<form:form commandName="group" cssClass="form-horizontal groupForm">
					<!-- Modal -->
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">Create a new
										group</h4>
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
										<label for="name" class="col-sm-2 control-label">
											Description: </label>
										<div class="col-sm-10">
											<!-- form:input path="description" cssClass="form-control" /-->
											<!-- form:errors path="description" /-->
										</div>
									</div>

								</div>
								<div class="modal-footer">
									<input type="submit" class="btn btn-primary"
										value="Create one!" />
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>
				</form:form>

			</div>
			<!-- /.box-header -->

			<div class="box-body">

				<div class="alert alert-info" style="margin-right: 15px;"
					align="center">Find minded people and get some conversations!
					You'll get many useful information here! Have a fun! ;)</div>

				<table class="table table-bordered table-hover table-striped">
					<thead>
						<tr>
							<th>Group name</th>
							<th>Published at</th>
							<th>Operations</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${groups}" var="group">
							<tr>
								<td><a> <c:url var="groupUrl" value="/group">
											<c:param name="id" value="${group.id}" />
										</c:url> <a href="<c:out value="${groupUrl}"/>"><c:out
												value="${group.groupName}" /></a> <br>
								</a></td>
								<td>${group.createdAt}</td>
								<td><a
									href="<spring:url value="/groups/remove/${group.id}" />"
									class="btn btn-danger triggerRemove"> Remove </a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

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

				<div align="right" style="margin-top: 21px;">
					<button type="button" class="btn btn-primary btn-md"
						data-toggle="modal" data-target="#myModal">Create a new
						group</button>
				</div>

			</div>
			<!-- /.box-body -->
		</div>
		<!-- /.box -->
	</section>

</body>
</html>