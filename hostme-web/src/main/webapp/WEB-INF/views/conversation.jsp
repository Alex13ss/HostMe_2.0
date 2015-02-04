<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<title>Conversation: ${conversation.title}</title>

<section class="content-header">
	<h1>
		Conversation
	</h1>
</section>

<script type="text/javascript" src="resources/js/conversations/conversation.js"></script>
<script src="resources/js/plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(
		function() {
			loadPostsAjax(<c:out value="${conversation.id}"/>);
			
			$("#sendMsg").click(function(){
				  sendMessage(<c:out value="${conversation.id}"/>,$("#userMsg").val());
				});
		});
</script>

<!-- Chat box -->
<section class="content">
              <div class="box box-success">
                <div class="box-header">
                  <i class="fa fa-comments-o"></i>
                  <h3 class="box-title">${conversation.title}</h3>
                </div>
                <div class="box-body chat" id="chat-box">
                  <div class="item">
                    <img src="dist/img/user4-128x128.jpg" alt="user image" class="online"/>
                    <p class="message">
                      <a href="#" class="name">
                        <small class="text-muted pull-right"><i class="fa fa-clock-o"></i>12:48</small>
                        Vsayl Baziv
                      </a>
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
                  </div>
                </div><!-- /.chat -->
                <div class="box-footer">
                  <div class="input-group">
                    <input class="form-control" id="userMsg" placeholder="Type message..."/>
                    <div class="input-group-btn">
                      <button class="btn btn-success" id="sendMsg" style="height: 34px;" ><i class="fa fa-plus"></i></button>
                    </div>
                  </div>
                </div>
              </div>
</section>
