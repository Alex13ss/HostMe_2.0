<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>
<security:authorize access="isAuthenticated()">
	<aside class="left-side sidebar-offcanvas">
		<!-- sidebar: style can be found in sidebar.less -->
		<section class="sidebar">

			<!-- Sidebar user panel -->
			<div class="user-panel">

				<div class="pull-left image">
					<c:forEach var="image" items="${loggedUser.images}">
						<img src="${image_url}${image.link}" class="img-circle"
							alt="User Image" />
					</c:forEach>
				</div>

				<div class="pull-left info">

					<p>
						<a href="profile">${loggedUser.firstName}&thinsp;${loggedUser.lastName}</a>
					</p>

					<a href="#"><i class="fa fa-circle text-success"></i> <spring:message
							code="label.online" /></a>
				</div>

			</div>

			<!-- sidebar menu: : style can be found in sidebar.less -->
			<ul class="sidebar-menu">


				<security:authorize access="! isAuthenticated()">
					<li><a href="<c:url value='login' />"
						style="margin-left: 10px;"><i class="fa fa-key"></i> <spring:message
								code="label.login" /></a></li>
				</security:authorize>

				<security:authorize access="! isAuthenticated()">
					<li>
						<a href="#" data-toggle="modal"
						data-target="#registrationModal" style="margin-left: 10px;"><i
							class="fa fa-gear"></i> <spring:message code="label.register" />
						</a>
					</li>
				</security:authorize>

				<security:authorize access="isAuthenticated()">
					<li><a href='<c:url value="search"/>'
						style="margin-left: 10px;"><i class="fa fa-search"></i> <spring:message
								code="label.searchhosts" /></a></li>
				</security:authorize>

				<security:authorize access="isAuthenticated()">
					<li><a href='<c:url value="/megaSearch"/>'
						   style="margin-left: 10px;"><i class="fa fa-search"></i> <spring:message
							code="label.searchhosts" /></a></li>
				</security:authorize>

				<security:authorize access="hasRole('ADMIN')">
					<li>
						<a href='<c:url value="/usersManager"/>' style="margin-left: 10px;">
							<i class="fa fa-male"></i>
							<spring:message code="admin.usersManager" />
						</a>
					</li>
				</security:authorize>
				
				<security:authorize access="isAuthenticated()">
					<li><a href='<c:url value="profile"/>'
						style="margin-left: 10px;"><i class="fa fa-male"></i> <spring:message
								code="label.myprofile" /></a></li>
				</security:authorize>

				<security:authorize access="isAuthenticated()">
					<li><a href='<c:url value="/createRoute"/>' style="margin-left: 10px;">
							<i class="fa fa-arrows"></i>
							<spring:message code="label.routes" />
						</a>
					</li>

				</security:authorize>

				<security:authorize access="isAuthenticated()">
					<li><a href='<c:url value="groups"/>'
						style="margin-left: 10px;"><i class="fa fa-users"></i> <spring:message
								code="label.groups" /></a></li>
				</security:authorize>

				<security:authorize access="isAuthenticated()">
					<li><a href='<c:url value="events"/>'
						style="margin-left: 10px;"><i class="fa fa-calendar"></i> <spring:message
								code="label.events" /></a></li>
				</security:authorize>

				<security:authorize access="isAuthenticated()">
					<li><a href='<c:url value="sightseeings"/>'
						style="margin-left: 10px;"><i class="fa fa-camera-retro"></i> <spring:message
								code="label.sightseeings" /></a></li>
				</security:authorize>

				<security:authorize access="isAuthenticated()">
					<li><a href='<c:url value="request"/>'
						style="margin-left: 10px;"><i class="fa fa-question-circle"></i>
							<spring:message code="label.requesthistory" /></a></li>
				</security:authorize>

				<security:authorize access="isAuthenticated()">
					<li><a href='<c:url value="logout"/>'
						style="margin-left: 10px;"><i class="fa fa-sign-out"></i> <spring:message
								code="label.logout" /></a></li>
				</security:authorize>
			</ul>
		</section>
		<!-- /.sidebar -->
	</aside>
</security:authorize>
</html>