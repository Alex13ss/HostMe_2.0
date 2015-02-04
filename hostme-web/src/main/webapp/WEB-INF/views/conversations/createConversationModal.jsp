<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="resources/js/conversations/modalCreation.js"></script>
<script src="resources/js/plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script>

<div class="modal fade" id="createConversation">
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- Header -->
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">
					<spring:message code="conversations.form" />
				</h4>
			</div>
			
			<c:url value="/conversationCreate" var="guestBookLink" />
			<form id="conversation" action="${guestBookLink}" method="post" >
			<!-- Body -->
			<div class="modal-body">
						<input type="hidden" name="groupId" id="groupId" value="${groupId}">
					<div class="form-group">
						<label for="title"><spring:message code="conversations.createTitle" /></label>
						<input type="text" id="title" name="title" class="form-control">
					</div>
					<div class="form-group">
						<label for="title"><spring:message code="conversations.moderators" /></label>
						<div id="moderatorLogins" class="form-group">
							
						</div>
						<input type="text"  id="moderators" name="moderators" class="form-control">
					</div>
					<div id="moderatorsResult" name="moderatorsResult">
						
					</div>
					
					<div class="form-group" id="messageClass">
						<label for="title"><spring:message code="conversations.message" /></label>
						<textarea id="message" name="message" class="form-control" rows="3"></textarea>
					</div>
			</div>
			<!-- Footer -->
			<div class="modal-footer">
				<button type="submit" class="btn btn-primary" id="submitButton">Create</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

			</div>
			</form>			
		</div>
	</div>
</div>