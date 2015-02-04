/**
 * @author Oleksandr Bandurka JavaScript for page with groups
 */

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
		table.fnReloadAjax("need-act-groups");
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
											function html_element(aData, status) {
												var parent_element = $("<a/>",
														{
															"href" : "#"
														})
														.click(
																aData,
																function(e) {
																	aData.status = status;
																	e
																			.preventDefault();
																	$
																			.ajax({
																				url : 'request-update',
																				'dataType' : 'json',
																				beforeSend : function() {
																				},
																				contentType : "application/json",
																				"type" : "POST",
																				data : JSON
																						.stringify(aData),
																				success : function(
																						response) {
																					$(
																							'td:eq(4)',
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
														text : "Action",
														"type" : "button",
														"data-toggle" : "dropdown",
														"class" : "btn btn-default dropdown-toggle",
													});

											function form_element(aData) {
												var reject = html_element(
														aData, "REJECTED");
												var approve = html_element(
														aData, "APPROVED");
												var refuse = html_element(
														aData, "REFUSED");
												var send = html_element(aData,
														"PENDING");
												reject.html("Reject");
												approve.html("Approve");
												refuse.html("Refuse");
												send.html("Send Again");

												var final_element = div
														.append(button);
												final_element
														.append(ul
																.append(li
																		.append(
																				send)
																		.append(
																				reject)
																		.append(
																				approve)
																		.append(
																				refuse)));
												return final_element;
											}
											var currentDate = new Date();
											if (aData.endDate > currentDate
													.getMilliseconds()) {
												row = $('td:eq(6)', nRow).html(
														form_element(aData));
											}
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
												// {
												// "mData" : "creatorFNandLN",
												// },
												{
													"sWidth" : "10%",
													"mData" : "createdAt",
													"mRender" : function(data,
															type, full) {
														return new Date(data)
																.toLocaleString();
													}
												} ]
									});
					table
							.on(
									'draw',
									function() {
										if (table.fnSettings().sAjaxSource == "all-groups") {
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
