var table;
function allUsers(element) {
	if (element.className != 'active') {
		table.fnClearTable();
		table.fnReloadAjax("all-systemProperties");

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
										"sAjaxSource" : "all-systemProperties",
										"aoColumns" : [

												{
													"mData" : "propKey"
													
												},

												{
													"mData" : "value"
												
												},
												{
													"mData" : function(data,
															type, full) {
														return '<a href="systemproperty?id='
																+ data.propertyId
																+ '" class="text-blue"/>'
																+ '<i class="fa fa-fw fa-edit"></i>'
																+ '</a>'
													}
												
												} 
												]
									});
					table
							.on(
									'draw',
									function() {
										if (table.fnSettings().sAjaxSource == "all-systemProperties") {
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