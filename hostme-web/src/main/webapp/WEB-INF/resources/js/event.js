var table, row;
var test, test1, test2;
var action_list;
var all_events_data;
var request_to_me_data;
var request_from_me_url = "request-sent-history";
var request_to_me_url = "request-obtain-history";

function allEvents(element) {
	if (element.className != 'active') {
		table.fnClearTable();
		table.fnReloadAjax("all-events");

	}
}
function myEvents(element) {
	if (element.className != 'active') {
		table.fnClearTable();
		table.fnReloadAjax("my-events");

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
										"sAjaxSource" : "all-events",
										"aoColumns" : [
												{
													"mData" : function(data,
															type, full) {
														return '<a href=event?id='
																+ data.id
																+ '>'
																+ data.name
																+ '</a>';
													}

												},
												{
													"mData" : "startDate",
													"mRender" : function(data,
															type, full) {
														return new Date(data)
																.toLocaleString()
																.split(" ")[0];
													}
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
												},
												{
													"mData" : "website"
												},
												{
													"mData" : function(data,
															type, full) {
														return data.owner.firstName
																+ " "
																+ data.owner.lastName;
													}
												} ]
									});
					table
							.on(
									'draw',
									function() {
										if (table.fnSettings().sAjaxSource == "all-events") {
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
