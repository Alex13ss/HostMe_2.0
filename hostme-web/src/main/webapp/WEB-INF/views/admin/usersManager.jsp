<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<c:url value="/resources/js/jquery.dataTables.js"/>"></script>
<script src="<c:url value="/resources/js/admin/usersManager.js"/>"></script>
<script src="<c:url value="/resources/js/fnAjaxReload.js"/>"></script>
<script src="<c:url value="/resources/js/dataTables.bootstrap.js"/>"></script>
<link rel="stylesheet" type="text/css"
	href="resources/css/dataTables.bootstrap.css" />

<title><spring:message code="admin.usersManager" /></title>
</head>
<body class="wysihtml5-supported">
	<section class="content-header">
		<h1>
			<spring:message code="admin.usersManager" />
		</h1>
	</section>

	<section class="content">
				<c:if test="${userBaned eq true}">
				<div class="alert alert-warning" style="margin-right: 15px;"
					align="center">
					<h3>
						<spring:message code="admin.banConf" />
					</h3>
				</div>
			</c:if>
				<c:if test="${userUnBaned eq true}">
				<div class="alert alert-warning" style="margin-right: 15px;"
					align="center">
					<h3>
						<spring:message code="admin.unbanConf" />
					</h3>
				</div>
			</c:if>
			<c:if test="${passReset eq true}">
				<div class="alert alert-warning" style="margin-right: 15px;"
					align="center">
					<h3>
						<spring:message code="admin.resetConf" />
					</h3>
				</div>
			</c:if>
		<div class="box box-primary">
			<!-- /.box-header -->
			<ul id="usersTypesNav" class="nav nav-tabs">
				<li id="all-users" class="active" onclick="allUsers(this)"><a
					href="#" data-toggle="tab"><spring:message code="admin.users" /></a></li>
			</ul>
			<div class="box-body table-responsive">
			<select id="request_size" class="dataTableDropDown">
					<option value="10" selected="selected">10</option>
					<option value="25">25</option>
					<option value="50">50</option>
					<option value="100">100</option>
				</select>
				<table id="request_table_obtain"
					class="table table-bordered table-striped">
					<thead>
						<tr id="usersTableHeader">
							<th style="width: 15%"><spring:message code="user.login" /></th>
							<th style="width: 20%"><spring:message code="user.name" /></th>
							<th style="width: 10%"><spring:message code="user.role" /></th>
							<th style="width: 15%"><spring:message code="user.state" /></th>
							<th style="width: 15%"><spring:message code="user.ban" /></th>
							<th style="width: 10%"><spring:message code="user.resetPass" /></th>
							<th style="width: 15%"><spring:message code="user.action" /></th>
						</tr>
					</thead>

				</table>
				<div class="box-footer clearfix">
					<ul id="table_pages"
						class="pagination pagination-sm no-margin pull-right">
					</ul>
				</div>

			</div>
			<div class="modal fade" id="modalBan" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">
								<spring:message code="admin.banMod" />
							</h4>
						</div>
						<div class="modal-body">
							<div class="callout callout-danger" id="alert" align="center">
								<font size="5"><spring:message code="admin.banMsg" /></font>
							</div>
						</div>
						<div class="modal-footer">
							<a href="" class="btn btn-primary banBtn"><spring:message
									code="user.ban" /></a>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">
								<spring:message code="label.cancel" />
							</button>
						</div>
					</div>
				</div> 
			</div>
			<div class="modal fade" id="modalReset" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">
								<spring:message code="admin.resetMod" />
							</h4>
						</div>
						<div class="modal-body">
							<div class="callout callout-danger" id="alert" align="center">
								<font size="5"><spring:message code="admin.resetMsg" /></font>
							</div>
						</div>
						<div class="modal-footer">
							<a href="" class="btn btn-primary resetBtn"><spring:message
									code="admin.reset" /></a>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">
								<spring:message code="label.cancel" />
							</button>
						</div>
					</div>
				</div> 
			</div>
		</div>

	</section>
</body>
</html>