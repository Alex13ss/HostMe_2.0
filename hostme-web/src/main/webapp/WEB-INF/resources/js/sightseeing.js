var table;

function allSightseeings(element) {
	if (element.className != 'active') {
		table.fnClearTable();
		table.fnReloadAjax("all-sightseeings");

	}
}

function favouriteSightseeings(element) {
	if (element.className != 'active') {
		table.fnClearTable();
		table.fnReloadAjax("favourite-sightseeings");

	}
}

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

$(document)
		.ready(
				function() {
					table = $("table.table-bordered")
							.dataTable(
									{
										"sAjaxDataProp" : "",
										"fnInitComplete" : function(settings,
												json) {
										},
										"fnRowCallback" : function(nRow, aData,
												iDisplayIndex,
												iDisplayIndexFull) {
											var ul = $("<ul/>", {
												'class' : "dropdown-menu"
											});
											var li = $("<li/>");
											var div = $("<div/>", {
												"class" : "input-group-btn"
											});
										},

										"bProcessing" : false,
										"bServerSide" : false,
										"sAjaxSource" : "all-sightseeings",
										"aoColumns" : [
												{
													"mData" : function(data,
															type, full) {
														return '<a href=sightseeing?id='
																+ data.id
																+ '>'
																+ data.name
																+ '</a>';
													}

												},
												{
													"mData" : "description",
												},
												{
													"mData" : function(data,
															type, full) {
														return data.city.city
																+ ", "
																+ data.address;
													}
												},
												{
													"mData" : "priceCategory",
													"mRender" : function(data,
															type, full) {
														return data.priceCategory;
													}
												}, {
													"mData" : "website"
												}, {
													"mData" : "sightseeingType"
												}, ]
									});
					table
							.on(
									'draw',
									function() {
										if (table.fnSettings().sAjaxSource == "all-sightseeings") {
											$(
													'.dropdown-menu>li>a:contains("Reject"),a:contains("Approve")')
													.hide();
										} else {
											$(
													'.dropdown-menu>li>a:contains("Refuse"),a:contains("Send Again")')
													.hide();
										}
									});
				});