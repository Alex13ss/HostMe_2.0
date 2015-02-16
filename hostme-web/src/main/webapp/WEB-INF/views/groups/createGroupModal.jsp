<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="resources/js/groups/group.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/groups.css">

<div class="modal fade" id="groupModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="label.modCreateGroup" />
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
						<form:textarea id="group-dscrptn-textarea" path="groupDescription"
							cssClass="form-control" rows="5" />
						<form:errors path="groupDescription" />
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<input type="submit" class="btn btn-primary"
					value="<spring:message code="label.createGroup" />" />
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="label.close" />
				</button>
			</div>
		</div>
	</div>
</div>