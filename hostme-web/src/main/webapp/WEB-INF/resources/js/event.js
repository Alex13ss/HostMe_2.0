var table, row;
var test, test1, test2;
var action_list;
var all_events_data;
var request_to_me_data;
var request_from_me_url = "request-sent-history";
var request_to_me_url = "request-obtain-history";

function refresh() {
	if ($("#all-events").attr('class') != 'active') {
		table.fnReloadAjax();
	} else {
		table.fnReloadAjax("all-events");
	}

}

function allEvents(element) {
	if (element.className != 'active') {
		table.fnClearTable();
		table.fnAddData(all_events_data);

	}

}

$(document)
		.ready(
				function() {

					table = $("table.table-bordered").dataTable(
							{

								"sAjaxDataProp" : "",

								"fnInitComplete" : function(settings, json) {

								},

								"fnRowCallback" : function(nRow, aData,
										iDisplayIndex, iDisplayIndexFull) {
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
											"mData" : "id",

										},
										{
											"mData" : "description",

										},
										{
											"mData" : "startDate",
											"mRender" : function(data, type,
													full) {
												return new Date(data)
														.toLocaleString()
														.split(" ")[0];
											}
										},
										{
											"mData" : "endDate",
											"mRender" : function(data, type,
													full) {
												return new Date(data)
														.toLocaleString()
														.split(" ")[0];
											}
										},

										{
											"mData" : "priceCategory",
											"mRender" : function(data, type,
													full) {
												return data.priceCategory;
											}
										}, {
											"mData" : "website"
										}, {
											"mData" : "status"
										}, {
											"mData" : "comment"
										}

								]
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
