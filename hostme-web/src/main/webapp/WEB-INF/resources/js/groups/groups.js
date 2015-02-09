/**
 * @author Oleksandr Bandurka JavaScript for page with groups
 */

var userRole = "MODERATOR";

function checkRole() {
	if (userRole == "MODERATOR") {
		return true;
	} else {
		return false;
	}
}

function groupsAjaxCallback() {
	setupPaging();
}

function allGroups(element) {
	if (element.className != 'active') {
		table.fnClearTable();
		table.fnReloadAjax("all-groups");
	}
}

function myGroups(element) {
	if (element.className != 'active') {
		table.fnClearTable();
		table.fnReloadAjax("my-groups");
	}
}

function interestingGroups(element) {
	if (element.className != 'active') {
		table.fnClearTable();
		table.fnReloadAjax("interesting-groups");
	}
}

function needActionsGroups(element) {
	if (element.className != 'active') {
		table.fnClearTable();
		table.fnReloadAjax("pending-groups");
	}
}

$(document)
		.ready(
				function() {
					userRole= $('#UserRole').text();
					table = $("table.table-bordered")
							.dataTable(
									{
										"sAjaxDataProp" : "",
										"fnInitComplete" : function(settings,
												json) {
											groupsAjaxCallback();
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
											function html_element(aData, status) {
												var parent_element = $("<a/>",
														{
															"href" : "#"
														})
														.click(
																aData,
																function(e) {
																	aData.status = status;
																	console
																			.log(JSON
																					.stringify(aData));
																	e
																			.preventDefault();
																	$
																			.ajax({
																				url : 'group-status-update',
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
																							'td:eq(3)',
																							nRow)
																							.html(
																									response.status);
																				}
																			});
																});
												return parent_element;
											}
											;

											var button = $(
													"<button/>",
													{
														text : "Change status",
														"type" : "button",
														"data-toggle" : "dropdown",
														"class" : "btn btn-default dropdown-toggle",
													});

											function form_element(aData) {
												var approve = html_element(
														aData, "APPROVED");
												var pending = html_element(
														aData, "PENDING");
												var refuse = html_element(
														aData, "REFUSED");
												approve.html("Approve");
												pending.html("Pending");
												refuse.html("Refuse");

												var final_element = div
														.append(button);
												final_element
														.append(ul
																.append(li
																		.append(
																				approve)
																		.append(
																				pending)
																		.append(
																				refuse)));
												return final_element;
											}

											row = $('td:eq(4)', nRow).html(
													form_element(aData));

										},

										"bJQueryUI" : true,
										"bProcessing" : false,
										"bServerSide" : false,
										"bAutoWidth" : false,
										"sAjaxSource" : "all-groups",
										"aoColumns" : [
												{
													"sWidth" : "13%",
													"mData" : function(data,
															type, row) {
														return "<img src='resources/images/group-default.jpg'/>";
													}
												},
												{
													"mData" : function(data,
															type, full) {
														return '<a href=group?id='
																+ data.id
																+ '>'
																+ data.groupName
																+ '</a>'
																+ '<br>'
																+ data.groupDescription;
													}
												},
												{
													"sWidth" : "10%",
													"mData" : "createdAt",
													"mRender" : function(data,
															type, full) {
														return new Date(data)
																.toLocaleString();
													}
												}, {
													"bVisible" :checkRole(),
													"sWidth" : "10%",
													"mData" : "status"
												}, {
													"bVisible" :checkRole(),
													"sWidth" : "10%",
													"mData" : "id"
												} ]
									});

				});
