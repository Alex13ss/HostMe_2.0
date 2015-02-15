$(document).ready(
		
		function() {
			
			$('#chat-box').slimScroll({
			    height: window.screen.availHeight / 2,
			});
			
			$('#userMsg').keypress(function(e){
				  if(e.which == 13){
					  $("#sendMsg").click();
					  return false;
				  }
				});
		
		});

function loadPostsAjax(conversationId) {
	
	$.ajax({
		url: "findPosts.json", 
		type : 'GET',
		data : {
			conversationId : conversationId
		},
		dataType : "json",
		beforeSend : function() {
			$("#chat-box").html(loader);
		},
		success: function(result) {
			if (result.length > 0) {
				showPosts(result);
			} else {
				$("#chat-box").html("This conversation has no posts");
			}
	     }
	});
}

function showPosts(posts) {
	$("#chat-box").html("");
	for (var i = 0; i < posts.length; i++) {
		createChatItem(posts[i]);
	}
	var scrollTo_int = $('#chat-box').prop('scrollHeight') + 'px';
	$('#chat-box').slimScroll({scrollTo : scrollTo_int });
}

function createChatItem(postDto) {
	
	var item = document.createElement("DIV");
	item.className = "item";
	//here will be image of user, maybe
	var image = document.createElement("IMG");
	image.src = postDto.imageUrl;
	image.className = "online";
	
	var message = document.createElement("P");
	message.className = "message";
	var name = document.createElement("A");
	name.className = "name";
	name.href = "hoster?hosterId=" + postDto.authorId;
	
	var small = document.createElement("SMALL");
	small.className = "'text-muted pull-right";
	var i = document.createElement("I"); 
	i.className = "fa fa-clock-o";
	var time = document.createTextNode(postDto.postDate + ' '+ postDto.postTime);
	small.appendChild(i);
	small.appendChild(time);
	
	var tname = document.createTextNode(postDto.author);
	name.appendChild(small);
	name.appendChild(tname);
	message.appendChild(name);
	
	var t = document.createTextNode(postDto.content);
	
	var removeLabel = null;
	if (postDto.removable) {
		removeLabel = document.createElement("A");
		removeLabel.className = "deletePost " + postDto.id;
		removeLabel.onclick = removePost;
		removeLabel.href = "#";
		
		var span = document.createElement("SPAN");
		span.className = "fa fa-fw fa-times";
		span.style.cssFloat = "right";
		span.setAttribute("id", "deletePost");
		
		removeLabel.appendChild(span);
		message.appendChild(removeLabel);
	}
	message.appendChild(t);
	
	item.appendChild(image);
	item.appendChild(message);
	
	//here will be attachments, maybe
	
	var chat = document.getElementById("chat-box");

	chat.appendChild(item);
}

function removePost() {
	
	var postId = $(this).attr('class').replace('deletePost ', '');
	if (confirm('Do you really want to delete?')) {
		deleteMessage(postId);
    } else {
        return false;
    }
}

function sendMessage(conversationId, message) {
	if (message.length < 1) return false;
	$("#userMsg").val("");
	
	$.ajax({
		url: "sendPost", 
		type : 'GET',
		data : {
			conversationId : conversationId,
			message : message
		},
		dataType : "json",
		beforeSend : function() {
			$("#chat-box").html(loader);
		},
		success: function(result) {
			if (result.length > 0) {
				showPosts(result);
			} else {
				$("#chat-box").html("This conversation has no posts");
			}
	     }
	});
	
}

function deleteMessage(postId) {
	
	$.ajax({
		url: "deletePost", 
		type : 'GET',
		data : {
			postId : postId
		},
		dataType : "json",
		beforeSend : function() {
			$("#chat-box").html(loader);
		},
		success: function(result) {
			if (result.length > 0) {
				showPosts(result);
			} else {
				$("#chat-box").html("This conversation has no posts");
			}
	     }
	});
	
}

var loader = $(
		"<img/>",
		{
			src : "resources/images/ajax-loader-128px.gif",
			style : "width:128px;  display: block; margin-left: auto; margin-right: auto;"
		});