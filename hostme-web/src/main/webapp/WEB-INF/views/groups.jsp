<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>

<script type="text/javascript" src="resources/js/fnAjaxReload.js"></script>

<title>Groups</title>
<link rel="stylesheet" type="text/css" href="resources/css/AdminLTE.css">

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

				<div class="col-md-2" align="right" style="margin-top: 10px;">
					<button onclick="refresh()" class="btn btn-default btn-sm">
						<i class="fa fa-fw fa-refresh"></i> Refresh list
					</button>
				</div>

			</div>
			<!-- /.box-header -->

			<div class="box-body">

				<script type="text/javascript">
					$(document).ready(
							function() {
								$(".triggerRemove").click(
										function(e) {
											e.preventDefault();
											$("#modalRemove .removeBtn").attr(
													"href",
													$(this).attr("href"));
											$("#modalRemove").modal();
										});
							});
				</script>

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
							<div class="modal-body">Really remove?</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Cancel</button>
								<a href="" class="btn btn-danger removeBtn">Remove</a>
							</div>
						</div>
					</div>
				</div>

				<div align="right" style="margin-top: 21px;">
					<button type="button" class="btn btn-primary btn-md"
						data-toggle="modal" data-target="">Create a new group</button>
				</div>

			</div>
			<!-- /.box-body -->
		</div>
		<!-- /.box -->
	</section>

</body>
</html>