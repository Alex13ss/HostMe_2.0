var table;
var ADMIN = {
	"roleId" : 1,
	"role" : "ADMIN"
};
var MODERATOR = {
	"roleId" : 4,
	"role" : "MODERATOR"
};
var USER = {
	"roleId" : 3,
	"role" : "USER"
};

function allUsers(element) {
	if (element.className != 'active') {
		table.fnClearTable();
		table.fnReloadAjax("all-users");

	}
}
function banConfirm(e) {

				$("#modalBan .banBtn").attr("href",
						"banUser/"+e);
			$("#modalBan").modal();
}
function resetConfirm(e) {

	$("#modalReset .resetBtn").attr("href",
			"resetPass/"+e);
$("#modalReset").modal();
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

											function html_element(aData, role) {
												var parent_element = $("<a/>",
														{
															"href" : "#"
														})
														.click(
																aData,
																function(e) {
																	aData.role = role;
																	console
																			.log(JSON
																					.stringify(aData));
																	e
																			.preventDefault();
																	$
																			.ajax({
																				url : 'user-update',
																				dataType : 'json',
																				beforeSend : function() {
																				},
																				contentType : "application/json",
																				"type" : "POST",
																				data : JSON
																						.stringify(aData),
																				success : function(
																						response) {
																					$(
																							'td:eq(2)',
																							nRow)
																							.html(response.role.role.charAt(0).toUpperCase() 
																									+ role.role.substr(1).toLowerCase());
																				}
																			});
																});
												return parent_element;
											}
											;

											var button = $(
													"<button/>",
													{
														text : "Switch Role",
														"type" : "button",
														"data-toggle" : "dropdown",
														"class" : "btn btn-default btn-block",
													});

											function form_element(aData) {

												var admin = html_element(aData,
														ADMIN);
												var moderator = html_element(
														aData, MODERATOR);
												var user = html_element(aData,
														USER);
												admin.html("Admin");
												moderator.html("Moderator");
												user.html("User");
												var final_element = div
														.append(button);
												final_element
														.append(ul
																.append(li
																		.append(
																				admin)
																		.append(
																				moderator)
																		.append(
																				user)));

												return final_element;
											}

											row1 = $('td:eq(6)', nRow).html(
													form_element(aData));

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
													"mData" : function(data,
															type, full) {
														return data.role.role.charAt(0).toUpperCase() 
														+ data.role.role.substr(1).toLowerCase();
													}
												},
												{
													"mData" : function(data,
															type, full) {
														return data.userState.charAt(0).toUpperCase() 
														+ data.userState.substr(1).toLowerCase();
													}
												},
												{
													"mData" : function(data,
															type, full) {
														if (data.userState=="ACTIVE"){
															return '<a  href="banUser/'
															+ data.userId
															+ '" class="triggerBan" onclick="event.preventDefault();banConfirm('
															+ data.userId
															+')"/>'
															+'Ban'
															+ '</a>'
														}
														else {
															return '<a  href="banUser/'
															+ data.userId
															+ '" class="triggerBan" onclick="event.preventDefault();banConfirm('
															+ data.userId
															+')"/>'
															+'Unban'
															+ '</a>'
														}
								
																 
													}
												},
												{
													"mData" : function(data,
															type, full) {
														return '<a href="resetPass/'
																+ data.userId
																+ '" class="triggerReset" onclick="event.preventDefault();resetConfirm('
																+ data.userId
																+')"/>'
																+ 'Reset'
																+ '</a>'
													}
												},

												{
													"mData" : "userId"

												} ]
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
