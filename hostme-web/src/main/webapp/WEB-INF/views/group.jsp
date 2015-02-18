<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>

<script type="text/javascript" src="resources/js/groups/group.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/groups.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/conversations.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="label.group" /></title>
</head>

<section class="content-header">
	<h1>
		<spring:message code="label.group" />
		<small><spring:message code="label.sgpSlogan" /></small>
	</h1>
</section>

<!-- Main content -->
<section class="content">

	<div class="box box-primary">

		<div class="box-header">
			<div class="box-title col-md-8">
				<i class="fa fa-fw fa-users"></i>
				<c:out value="${group.groupName}" />
			</div>
			<div class="box-title col-md-4">
				<div align="right">
					<sec:authorize access="hasRole('USER')">
						<c:if test="${isInterested eq false}">
							<a href="<spring:url value="/group/subscribe/${group.id}" />"
								class="btn btn-default btn-sm"><i class="fa fa-fw fa-check"></i>
								<spring:message code="label.subscribeGroup" /> </a>
						</c:if>
						<c:if test="${isInterested eq true}">
							<a href="<spring:url value="/group/unsubscribe/${group.id}" />"
								class="btn btn-default btn-sm"><i class="fa fa-fw fa-times"></i>
								<spring:message code="label.unsubscribeGroup" /> </a>
						</c:if>
					</sec:authorize>
					<sec:authorize access="hasRole('MODERATOR')">
						<button type="button"
							class="btn btn-default btn-sm dropdown-toggle"
							data-toggle="dropdown">
							<i class="fa fa-fw fa-gear"></i>
							<spring:message code="label.moderatingGroup" />
							<i class="fa fa-caret-down"></i>
						</button>
						<ul id="groupStatusChanger" class="dropdown-menu"
							style="margin-top: -10px; left: 23px;">
							<li><a id="APPROVED" href="#"><spring:message
										code="label.statusApproved" /></a> <a id="PENDING" href="#"><spring:message
										code="label.statusPending" /></a> <a id="REFUSED" href="#"><spring:message
										code="label.statusRefuse" /></a></li>
						</ul>
						<button type="button" class="btn btn-default btn-sm"
							data-toggle="modal" data-target="#groupEditModal">
							<i class="fa fa-fw fa-edit"></i>
							<spring:message code="label.cofigureGroup" />
						</button>
						<a href="<spring:url value="/group/remove/${group.id}" />"
							class="btn btn-default btn-sm triggerRemove"><i
							class="fa fa-fw fa-trash-o"></i> <spring:message
								code="label.delete" /> </a>
					</sec:authorize>
					<c:if test="${isCreator eq true}">
						<button type="button" class="btn btn-default btn-sm"
							data-toggle="modal" data-target="#groupEditModal">
							<i class="fa fa-fw fa-edit"></i>
							<spring:message code="label.cofigureGroup" />
						</button>
						<a href="<spring:url value="/group/remove/${group.id}" />"
							class="btn btn-default btn-sm triggerRemove"><i
							class="fa fa-fw fa-trash-o"></i> <spring:message
								code="label.delete" /> </a>
					</c:if>
				</div>
			</div>
		</div>
		<!-- /.box-header -->

		<div class="box-body">

			<c:if test="${groupCreated eq true}">
				<div class="alert alert-success" style="margin-right: 15px;"
					align="center">
					<h3>
						<spring:message code="label.sgpWelcome" />
					</h3>
				</div>
			</c:if>
			<c:if test="${groupEdited eq true}">
				<div class="alert alert-warning" style="margin-right: 15px;"
					align="center">
					<h3>
						<spring:message code="label.sgpConfig" />
					</h3>
				</div>
			</c:if>

			<div class="row">
				<div class="col-md-3">
					<div class="panel box box-success">
						<div class="panel-body">

							<c:if test="${empty group.images}">
								<img src="resources/images/group-default.jpg">
							</c:if>
							<div id="im">
								<c:forEach begin="0" end="0" var="image" items="${group.images}">
									<img src="${image_url}${image.link}" />
								</c:forEach>
							</div>
							<div class="modal fade" id="imgModal" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-body"></div>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
							</div>

							<!-- Photo add form -->
							<c:if test="${empty group.images}">
								<c:if test="${isCreator eq true}">
									<form:form class="btn-xs" method="post" action="add-group-img"
										modelAttribute="group" enctype="multipart/form-data">
										<input type="hidden" value="${group.id}" name="id" />
										<div class="form-group" style="margin-bottom: -13px;">
											<label for="exampleInputFile"> </label> <br> <input
												type="file" name="file" class="multi" multiple
												accept="gif|jpg|png" data-maxfile="10000"
												data-maxsize="50000" /> <br>
											<button type="submit" class="btn btn-xs btn-primary">
												<spring:message code="label.savePhotos" />
											</button>
										</div>
									</form:form>
								</c:if>
							</c:if>
							<!-- http://plugins.krajee.com/file-input/demo -->

						</div>
					</div>
				</div>
				<div class="col-md-5">
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
							<h3 class="panel-title">
								<spring:message code="label.groupPubInfo" />
							</h3>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-5" align="right">
									<spring:message code="label.createdAt" />
									:
								</div>
								<div class="col-md-7">
									<fmt:formatDate value="${group.createdAt}"
										pattern="yyyy-MM-dd HH:mm:ss" />
								</div>
							</div>
							<div class="row">
								<div class="col-md-5" align="right">
									<spring:message code="label.createdBy" />
									:
								</div>
								<div class="col-md-7">
									<c:out
										value="${group.creatorUser.firstName} 
									${group.creatorUser.lastName}" />
								</div>
							</div>
							<c:if test="${!empty group.lastEditedAt}">
								<div class="row">
									<div class="col-md-5" align="right">
										<spring:message code="label.lastConfig" />
										:
									</div>
									<div class="col-md-7">
										<fmt:formatDate value="${group.lastEditedAt}"
											pattern="yyyy-MM-dd HH:mm:ss" />
									</div>
								</div>
							</c:if>
							<c:if test="${!empty group.lastEditor}">
								<div class="row">
									<div class="col-md-5" align="right">
										<spring:message code="label.configBy" />
										:
									</div>
									<div class="col-md-7">
										<c:out
											value="${group.lastEditor.firstName} 
									${group.lastEditor.lastName}" />
									</div>
								</div>
							</c:if>
							<div class="row">
								<div class="col-md-5" align="right">
									<spring:message code="label.subscribers" />
									:
								</div>
								<div class="col-md-7">
									<c:out value="${subscribers}" />
								</div>
							</div>
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
							<h4 class="modal-title" id="myModalLabel">
								<spring:message code="label.removeMod" />
							</h4>
						</div>
						<div class="modal-body">
							<div class="callout callout-danger" id="alert" align="center">
								<font size="5"><spring:message code="label.removeMsg" /></font>
							</div>
						</div>
						<div class="modal-footer">
							<a href="" class="btn btn-primary removeBtn"><spring:message
									code="label.delete" /></a>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">
								<spring:message code="label.cancel" />
							</button>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<c:set var="groupId" value="${group.id}" />
					<c:url var="conversationsUrl" value="/conversations">
						<c:param name="group_id" value="${groupId}" />
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
					<h4 class="modal-title" id="myModalLabel">
						<spring:message code="label.configMod" />
					</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label for="groupName" class="col-sm-2 control-label"> <spring:message
								code="label.name" />
						</label>
						<div class="col-sm-10">
							<form:input path="groupName" cssClass="form-control" />
							<form:errors path="groupName" />
						</div>
					</div>

					<div class="form-group">
						<label for="groupDescription" class="col-sm-2 control-label">
							<spring:message code="label.modDescription" />
						</label>
						<div class="col-sm-10">
							<form:textarea id="group-dscrptn-textarea"
								path="groupDescription" cssClass="form-control" rows="5" />
							<form:errors path="groupDescription" />
						</div>
					</div>

					<div style="display: none;">
						<div class="form-group">
							<label for="createdAt" class="col-sm-2 control-label"> </label>
							<div class="col-sm-10">
								<form:input readonly="true" path="createdAt"
									cssClass="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="creator" class="col-sm-2 control-label"> </label>
							<div class="col-sm-10">
								<form:input readonly="true" path="creatorUser.userId"
									cssClass="form-control" />
							</div>
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<input type="submit" class="btn btn-primary"
						value="<spring:message code="label.save" />" />
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<spring:message code="label.close" />
					</button>
				</div>
			</div>
		</div>
	</div>
</form:form>
</html>