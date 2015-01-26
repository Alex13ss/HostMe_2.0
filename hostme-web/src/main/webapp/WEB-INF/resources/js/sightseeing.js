var table;

function allSightseeings(element) {
	if (element.className != 'active') {
		table.fnClearTable();
		table.fnReloadAjax("all-sightseeings");

	}
}

function favouriteEvents(element) {
	if (element.className != 'active') {
		table.fnClearTable();
		table.fnReloadAjax("favourite-sightseeings");

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
												},]
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