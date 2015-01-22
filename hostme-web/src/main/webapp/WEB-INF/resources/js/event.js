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
											var li = $("<li/>"
											);
											var div = $("<div/>", {
												"class" : "input-group-btn"
											});
										},

										"bProcessing" : false,
										"bServerSide" : false,
										"sAjaxSource" : "all-events",
										"aoColumns" : [
												{
													"mData":function(data,type,full){
														return '<a href=event?id='
														+ data.id
														+ '>'
														+ data.name
														+'</a>';
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
