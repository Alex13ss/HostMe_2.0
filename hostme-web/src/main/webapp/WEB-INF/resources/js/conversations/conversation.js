$(document).ready(
		
		function() {
			
			$('#chat-box').slimScroll({
			    height: window.screen.availHeight / 2
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
				$("#chat-box").html("Failed to load posts");
			}
	     }
	});
}

function showPosts(posts) {
	$("#chat-box").html("");
	for (var i = 0; i < posts.length; i++) {
		createChatItem(posts[i]);
	}
}

function createChatItem(postDto) {
	
	var item = document.createElement("DIV");
	item.className = "item";
	//here will be image of user, maybe
	var image = document.createElement("IMG");
	image.src = "dist/img/user4-128x128.jpg";
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
	var time = document.createTextNode(postDto.postTime);
	small.appendChild(i);
	small.appendChild(time);
	
	var tname = document.createTextNode(postDto.author);
	name.appendChild(small);
	name.appendChild(tname);
	message.appendChild(name);
	
	var t = document.createTextNode(postDto.content);
	message.appendChild(t);
	
	item.appendChild(image);
	item.appendChild(message);
	
	//here will be attachments, maybe
	
	var chat = document.getElementById("chat-box");

	chat.appendChild(item);
}

function sendMessage(conversationId, message) {
	$("#userMsg").html("");
	
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
				$("#chat-box").html("Failed to load posts");
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