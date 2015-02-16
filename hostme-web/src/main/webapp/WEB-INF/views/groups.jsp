<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script type="text/javascript" src="resources/js/groups/groups.js"></script>
<link rel="stylesheet" type="text/css"
	href="resources/css/dataTables.bootstrap.css" />
<script type="text/javascript" src="resources/js/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="resources/js/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="resources/js/fnAjaxReload.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title><spring:message code="label.groups" /></title>
</head>

<section class="content-header">
	<h1>
		<spring:message code="label.groups" />
		<small><spring:message code="label.smallSlogan" /></small>
	</h1>
	<security:authorize access="hasRole('USER')">
		<c:set var="role" value="USER" />
	</security:authorize>
	<security:authorize access="hasRole('MODERATOR')">
		<c:set var="role" value="MODERATOR" />
	</security:authorize>
	<div id="UserRole" style="display: none;">${role}</div>
</section>

<!-- Main content -->
<section class="content">
	<div class="box box-primary">

		<div class="box-header">
			<div class="box-title col-md-9">
				<i class="fa fa-fw fa-list-alt"></i>
				<spring:message code="label.tableSlogan" />
			</div>

			<sec:authorize access="hasRole('USER')">
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
						<h3>
							<spring:message code="label.hibernateBadInput" />
						</h3>
					</div>
				</c:if>
				<div class="col-md-offset-2">
					<div class="col-md-9 alert alert-info" align="center">
						<spring:message code="label.tableFlashSlogan" />
					</div>
				</div>
			</sec:authorize>
		</div>
		<!-- /.box-header -->

		<ul id="groups-tabs" class="nav nav-tabs">
			<sec:authorize access="hasRole('USER')">
				<li id="approved-groups"><a href="#" data-toggle="tab"><spring:message
							code="label.allGroups" /></a></li>
				<li id="my-groups"><a href="#" data-toggle="tab"><spring:message
							code="label.myGroups" /></a></li>
				<li id="interesting-groups"><a href="#" data-toggle="tab"><spring:message
							code="label.subscribedGroups" /></a></li>
			</sec:authorize>
			<sec:authorize access="hasRole('MODERATOR')">
				<li id="all-groups"><a href="#" data-toggle="tab"><spring:message
							code="label.allGroups" /></a></li>
				<li id="pending-groups"><a href="#" data-toggle="tab"><spring:message
							code="label.needActionsGroups" /></a></li>
			</sec:authorize>
		</ul>

		<div class="box-body table-responsive">
			<select id="request_size" class="dataTableDropDown">
				<option value="10" selected="selected">10</option>
				<option value="25">25</option>
				<option value="50">50</option>
				<option value="100">100</option>
			</select>
			<table id="groups-table" class="table table-bordered table-hover">
				<thead>
					<tr id="groups-table-header">
						<th><spring:message code="label.thGroupImg" /></th>
						<th headers="groupName"><spring:message
								code="label.thGroupName" /></th>
						<th headers="createdAt"><spring:message
								code="label.thGroupPubl" /></th>
						<sec:authorize access="hasRole('MODERATOR')">
							<th headers="status"><spring:message
									code="label.thGroupStatus" /></th>
							<th><spring:message code="label.thGroupActions" /></th>
						</sec:authorize>
					</tr>
				</thead>
			</table>
			<div class="box-footer clearfix">
				<ul id="table_pages"
					class="pagination pagination-sm no-margin pull-right">
				</ul>
			</div>
		</div>
		<!-- /.box-body -->

	</div>
	<!-- /.box -->
</section>

<form:form modelAttribute="group" cssClass="form-horizontal groupForm">
	<%@ include file="groups/createGroupModal.jsp"%>
</form:form>

<div id="changeStatus" style="display: none">
	<spring:message code="label.changeStatus" />
</div>

</body>
</html>