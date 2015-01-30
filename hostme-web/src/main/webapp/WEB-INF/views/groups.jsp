<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script type="text/javascript" src="resources/js/group.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/groups.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/dataTables.bootstrap.css" />
<script type="text/javascript" src="resources/js/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="resources/js/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="resources/js/fnAjaxReload.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Groups</title>

</head>

<section class="content-header">
	<h1>
		Groups<small>Conversations with minded people</small>
	</h1>
</section>

<!-- Main content -->
<section class="content">
	<div class="box box-primary">

		<div class="box-header">
			<div class="box-title col-md-9">
				<i class="fa fa-fw fa-list-alt"></i> Choose group and have a fun and
				useful conversations!
			</div>

			<div class="box-title col-md-3" align="right">
				<button type="button" class="btn btn-primary btn-sm"
					data-toggle="modal" data-target="#groupModal">
					<i class="fa fa-fw fa-plus-square-o"></i>
					<spring:message code="label.addGroup" />
				</button>
			</div>
			<c:if test="${groupNotCreated eq true}">
				<div class="box-title col-md-12 alert alert-danger"
					style="margin-bottom: 13px;" align="center">
					<h3>Oops... Wrong input data! Your group is NOT created.
						Please try again!</h3>
				</div>
			</c:if>
			<div class="col-md-offset-2">
				<div class="col-md-9 alert alert-info" align="center">Find
					minded people and get some conversations! You'll get many useful
					information here! Have a fun! ;)</div>
			</div>
		</div>
		<!-- /.box-header -->

		<ul class="nav nav-tabs">
			<li id="all-groups" class="active" onclick="allGroups(this)"><a
				href="#" data-toggle="tab"><spring:message
						code="label.allGroups" /></a></li>
			<li id="my-groups" class="" onclick="myGroups(this)"><a href="#"
				data-toggle="tab"><spring:message code="label.myGroups" /></a></li>
			<li id="interesting-groups" class=""
				onclick="interestingGroups(this)"><a href="#" data-toggle="tab"><spring:message
						code="label.interestingGroups" /></a></li>
		</ul>

		<div class="box-body table-responsive">
			<table id="groups-table" class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>Image of group</th>
						<th>Group name/description</th>
						<th>Publishing</th>
					</tr>
				</thead>
			</table>
		</div>
		<!-- /.box-body -->

	</div>
	<!-- /.box -->
</section>

<form:form modelAttribute="group" cssClass="form-horizontal groupForm">
	<!-- Modal -->
	<div class="modal fade" id="groupModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Create a new group</h4>
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
				</div>
				<div class="modal-footer">
					<input type="submit" class="btn btn-primary" value="Create one!" />
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</form:form>

</body>
</html>