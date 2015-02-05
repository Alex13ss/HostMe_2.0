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

jQuery()
		.ready(
				function() {
					var tabCountry = [];
					$.getJSON('getAllCountries.json', function(data) {
						$.each(data, function(index, val) {
							tabCountry[index] = val;
						});
					});
					$('#country')
							.change(
									function(event) {
										$country = $(this).val();
										var $htmlOption = '<option value="0">Select city</option>';
										for (var i = 0; i < tabCountry.length; i++) {
											if ($country === tabCountry[i].country) {
												for (var j = 0; j < tabCountry[i].city.length; j++) {
													var town = tabCountry[i].city[j].city;
													$htmlOption += '<option value="'
															+ town
															+ '">'
															+ town
															+ '</option>';
													console.log($htmlOption);
												}
											}
										}
										$('#city').html($htmlOption);
									});
				});
