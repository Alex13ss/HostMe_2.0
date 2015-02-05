
$(document).ready(
		function() {
			$(".conversation_create_label").click(
					function(e) {
						e.preventDefault();
						$("#createConversation").modal();
					});
		});

function deleteConversationModal(element) {
	
	var conversationId = element.className.replace("deleteConversation ","");
	$("#modalRemoveConversation").modal();
	document.getElementById("removeConfirmButton").href= "conversationDelete/" +  conversationId;
}