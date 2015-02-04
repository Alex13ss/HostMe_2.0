
$(document).ready(
		function() {
			$(".conversation_create_label").click(
					function(e) {
						e.preventDefault();
						$("#createConversation").modal();
					});
			
			$("#editConversation").click(
					function(e) {
						//code to get conversation data with ajax and fill modal form wih it
						e.preventDefault();
						$("#createConversation").modal();
					});
		});
