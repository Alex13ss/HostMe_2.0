function getUrlParameter(sParam) {
	var sPageURL = window.location.search.substring(1);
	var sURLVariables = sPageURL.split('&');
	for (var i = 0; i < sURLVariables.length; i++) {
		var sParameterName = sURLVariables[i].split('=');
		if (sParameterName[0] == sParam) {
			return sParameterName[1];
		}
	}
}

$(document).ready(function() {
	$("#eventStatusChanger > li > a").click(function() {
		$.ajax({
			url : 'event-status-update',
			dataType : 'json',
			contentType : "application/json",
			"type" : "POST",
			data : JSON.stringify({
				id : getUrlParameter('id'),
				status : $(this).attr("id")
				
			}),
		});

	});
});







