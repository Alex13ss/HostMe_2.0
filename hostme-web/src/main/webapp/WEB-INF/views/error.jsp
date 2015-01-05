<%@ page language="java" contentType="text/html; charset=Utf-8"
	pageEncoding="Utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Utf-8">
<title>ERROR</title>
</head>
<body>
	<section class="content-header">
		<h1>404 Error Page</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
			<li><a href="#">Examples</a></li>
			<li class="active">404 error</li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">

		<div class="error-page">
			<h2 class="headline text-info">404</h2>
			<div class="error-content">
				<h3>
					<i class="fa fa-warning text-yellow"></i> Oops! Page not found.
				</h3>
				<p>
					We could not find the page you were looking for. Meanwhile, you may
					<a href='../../index.html'>return to dashboard</a> or try using the
					search form.
				</p>
				<img src="resources/images/error404.jpg" alt="User Image">
				<form:form method="get" action="error-search" class='search-form'>
					<div class='input-group'>
						<input type="text" name="search" class='form-control'
							placeholder="Search" />
						<div class="input-group-btn">
							<button type="submit" name="submit" class="btn btn-primary">
								<i class="fa fa-search"></i>
							</button>
						</div>
					</div>
					<!-- /.input-group -->
				</form:form>
			</div>
			<!-- /.error-content -->
		</div>
		<!-- /.error-page -->

	</section>
	<!-- /.content -->


</body>
</html>