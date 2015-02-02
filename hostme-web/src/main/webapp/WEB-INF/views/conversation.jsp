<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" src="resources/js/conversations/conversation.js"></script>
<script src="resources/js/plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script>

<!-- Chat box -->
              <div class="box box-success">
                <div class="box-header">
                  <i class="fa fa-comments-o"></i>
                  <h3 class="box-title">${conversation.title}</h3>
                </div>
                <div class="box-body chat" id="chat-box">
                <c:forEach var="post" items="${conversation.posts}">
                  <!-- chat item -->
                  <div class="item">
                    <img src="dist/img/user4-128x128.jpg" alt="user image" class="online"/>
                    <p class="message">
                    	<c:url var="hosterUrl" value="/hoster">
							<c:param name="hosterId" value="${post.author.userId}" />
						</c:url>
                      <a href="<c:out value="${hosterUrl}"/>" class="name">
                        <small class="text-muted pull-right"><i class="fa fa-clock-o"></i> ${post.postedAt.time}</small>
                        ${post.author.firstName}&nbsp;${post.author.lastName}
                      </a>
                      ${post.content}
                    </p>
                    <!--div class="attachment">
                      <h4>Attachments:</h4>
                      <p class="filename">
                        Theme-thumbnail-image.jpg
                      </p>
                      <div class="pull-right">
                        <button class="btn btn-primary btn-sm btn-flat">Open</button>
                      </div>
                    </div--><!-- /.attachment -->
                  </div><!-- /.item -->
                  </c:forEach>
                </div><!-- /.chat -->
                <div class="box-footer">
                  <div class="input-group">
                    <input class="form-control" placeholder="Type message..."/>
                    <div class="input-group-btn">
                      <button class="btn btn-success"  style="height: 34px;" ><i class="fa fa-plus"></i></button>
                    </div>
                  </div>
                </div>
              </div><!-- /.box (chat box) -->
