<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript" src="resources/js/conversations/conversations.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.js"></script>
<script type="text/javascript">
$(document).ready(
		function() {
			$( ".editConversation" ).click(function() {
				getConversation(this);
			});
			
			$( ".deleteConversation" ).click(function() {
				deleteConversationModal(this);
			});
			
			$( ".conversation_create_label" ).click(function() {
				cleanConversationData();
			});
			
			
		});
</script>

<div class="panel panel-info">
	<div class="panel-heading conversations_header">
		<a href="<c:out value="${conversationsUrl}"/>">
			<div class="panel-title">
				<spring:message code="conversations.title" />
				<span class="badge conversation_badge">${conversationsSize}</span> 
				<span class="badge conversation_badge  conversation_create_label" >
					<spring:message code="conversations.create" />
				</span>
			</div>
		</a>
	</div>


	<div class="panel-body">
		<c:forEach var="conversationDto" items="${conversationDtos}">

			<c:url var="conversationUrl" value="/conversation">
				<c:param name="id" value="${conversationDto.conversation.id}" />
			</c:url>

			<a href="<c:out value="${conversationUrl}"/>">
				<div class="conversation">
					<div class="conversation_title">${conversationDto.conversation.title}
						<c:if test="${conversationDto.isEditable eq true}">
							<a href="#" class="deleteConversation ${conversationDto.conversation.id}"><span id="deleteConversation" class="fa fa-fw fa-times" style="float:right;"></span></a>
							<a href="#" class="editConversation ${conversationDto.conversation.id}"><span id="editConversation" class="fa fa-fw fa-pencil" style="float:right;"></span></a>
						</c:if>
					</div>
					<div class="last_post"><spring:message code="conversations.lastMsg" />
						${conversationDto.lastPost.author.login}.
						${conversationDto.lastPostDate } <spring:message code="conversations.lastMsgTime" /> ${conversationDto.lastPostTime}
					</div>
				</div>
			</a>
		</c:forEach>
	</div>
	
	<%@ include file="createConversationModal.jsp" %>
	
	<%@ include file="deleteConversationModal.jsp" %>	
	
</div>
