<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div id="group_conversations_block">

	<a class="conversations_header_link" href="<c:out value="${conversationsUrl}"/>">
			<div class="conversations_header">
				<spring:message code="conversations.name" />
				<span class="badge conversation_badge">${conversationsSize}</span> <span
					class="badge conversation_badge  conversation_create_label">
					<spring:message code="conversations.create" />
				</span>
			</div>
		</a>

		<div id="conversations_body">
			<c:forEach var="conversationDto" items="${conversationDtos}">
				
				<c:url var="conversationUrl" value="/conversation">
					<c:param name="id" value="${conversationDto.conversation.id}" />
				</c:url>
				
				<a href="<c:out value="${conversationUrl}"/>">
					<div class="conversation">
						<div class="conversation_title">${conversationDto.conversation.name}</div>
						<div class="last_post">Останнє повідомлення від ${conversationDto.lastPost.author.login}. ${conversationDto.lastPostDate } о ${conversationDto.lastPostTime}</div>
					</div>
				</a>
			</c:forEach>
		</div>
		
	</div>