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
					<i class="fa fa-th" style="margin-right: 10px;"> </i>Choose group
					and have a fun and useful conversations!
				</h3>

				<div class="col-md-2" style="margin-top: 10px;">
					<button onclick="refresh()" class="btn btn-default btn-sm">
						<i class="fa fa-fw fa-refresh"></i> Refresh list
					</button>
				</div>

			</div>
			<!-- /.box-header -->
			<div class="box-body">
				<table id="request_table_obtain"
					class="table table-bordered table-striped">
				</table>

				<c:forEach var="group" items="${groups}">
					<c:out value="${group.groupName}" />
					<c:url var="groupUrl" value="/group">
						<c:param name="id" value="${group.id}" />
					</c:url>
					<a href="<c:out value="${groupUrl}"/>">Go Here!</a>
					<br>
				</c:forEach>

			</div>
			<!-- /.box-body -->
		</div>
		<!-- /.box -->

	</section>

</body>
</html>