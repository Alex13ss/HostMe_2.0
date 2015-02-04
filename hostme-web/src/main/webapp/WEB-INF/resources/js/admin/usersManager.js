var table;

function allUsers(element) {
	if (element.className != 'active') {
		table.fnClearTable();
		table.fnReloadAjax("all-users");

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
										"sAjaxSource" : "all-users",
										"aoColumns" : [

												{
													"mData" : "login"
												},
												{
													"mData" : function(data,
															type, full) {
														return data.firstName
																+ " "
																+ data.lastName;
													}
												},
												{
													"mData" : "userState"
												},
												{
													"mData" : function(data,
															type, full) {
														return data.role.role;
													}
												},
												{
													"mData" : function(data,
															type, full) {
														return '<a href="resetPass/'
																+ data.userId
																+ '" class="text-red"/>'
																+ 'Reset'
																+ '<span class="fa fa-refresh"></span></a>'
													}
												},
												{
													"mData" : function(data,
															type, full) {
														return '<a href="deleteUser/'
																+ data.userId
																+ '" class="text-red"/>'
																+ 'Delete'
																+ '<span class="fa fa-trash-o"></span></a>'
													}
												}]
									});
					table
							.on(
									'draw',
									function() {
										if (table.fnSettings().sAjaxSource == "all-users") {
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
