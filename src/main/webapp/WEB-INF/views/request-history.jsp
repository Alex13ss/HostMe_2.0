<%@ page language="java" contentType="text/html; charset=Utf-8"
	pageEncoding="Utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="resources/css/dataTables.bootstrap.css" rel="stylesheet"
	type="text/css" />
<script src="resources/js/jquery.dataTables.js" type="text/javascript"></script>
<script src="resources/js/request-history.js" type="text/javascript"></script>
<script src="resources/js/fnAjaxReload.js" type="text/javascript"></script>

<script src="resources/js/dataTables.bootstrap.js"
	type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=Utf-8">
<title>Request history</title>
</head>
<body>

	<section class="content-header">
		<h1>
			Request settings<small>Some configuration</small>
		</h1>

	</section>

	<!-- Main content -->
	<section class="content">
		<div class="row">


			<div class="box">
				<div class="box-header">
					<h3 class="box-title">
						<i class="fa fa-th"></i> Manage your request, it's important
					</h3>
					<div class="col-lg-offset-11" style="margin-top: 10px;" ><button class="btn btn-default btn-sm"><i class="fa fa-fw fa-refresh"></i> fa-refresh</button></div>
				</div>
				<!-- /.box-header -->
				<ul class="nav nav-tabs">
					<li class="active" onclick="request_obtain_old()"><a href="#" data-toggle="tab"
						>Request from me</a></li>
					<li class=""><a href="#" data-toggle="tab" onclick="initializeRequestToMe()">Request from me</a></li>
				</ul>
				
				<div class="box-body table-responsive">
					<table id="request_table_obtain"
						class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>From</th>
								<th>Till</th>
								<th>Notes</th>
								<th>Author</th>
								<th>Start Date</th>
								<th>Hosting</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Other browsers</td>
								<td>All others</td>
								<td>-</td>
								<td>-</td>
								<td>U</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<th>From</th>
								<th>Till</th>
								<th>Notes</th>
								<th>Author</th>
								<th>Start Date</th>
								<th>Hosting</th>
								<th>Action</th>
							</tr>
						</tfoot>
					</table>




				</div>

			</div>
			<!-- /.box-body -->
		</div>
		<!-- /.box -->

	</section>
	<!-- /.content -->

</body>
</html>