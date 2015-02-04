
$(document).ready(
		function() {
			$(".conversation_create_label").click(
					function(e) {
						e.preventDefault();
						$("#createConversation").modal();
					});
		});