<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="createConversation">
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- Header -->
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">
					<spring:message code="conversations.createNew" />
				</h4>
			</div>
			
			<c:url value="/conversationCreate" var="guestBookLink" />
			<form id="conversation" action="${guestBookLink}" method="post" >
			<!-- Body -->
			<div class="modal-body">
						<input type="hidden" name="groupId" value="${group.id}">
					<div class="form-group">
						<label for="title"><spring:message code="conversations.createTitle" /></label>
						<input type="text" id="title" name="title" class="form-control">
					</div>
					<div class="form-group">
						<label for="title"><spring:message code="conversations.moderators" /></label>
						<select  id="moderatorLogins" name="moderatorLogins" class="form-control"></select>
					</div>
					<div class="form-group">
						<label for="title"><spring:message code="conversations.message" /></label>
						<textarea id="message" name="message" class="form-control" rows="3"></textarea>
					</div>
			</div>
			<!-- Footer -->
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="submit" class="btn btn-primary">Create</button>
			</div>
			</form>			
		</div>
	</div>
</div>