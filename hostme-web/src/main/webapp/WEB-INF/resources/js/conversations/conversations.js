var validator = null;

$(document).ready(
		function() {
			$(".conversation_create_label").click(
					function(e) {
						e.preventDefault();
						$("#createConversation").modal();
					});
			validator = $("#conversation").validate(
					{
						rules : {
							title : {
								required : true,
								minlength : 3,
								maxlength : 50
							},
							message : {
								required : true,
								minlength : 5,
								maxlength : 255
							}
						}
					});
		});

function deleteConversationModal(element) {
	var conversationId = element.className.replace("deleteConversation ","");
	$("#modalRemoveConversation").modal();
	document.getElementById("removeConfirmButton").href= "conversationDelete/" +  conversationId;
}