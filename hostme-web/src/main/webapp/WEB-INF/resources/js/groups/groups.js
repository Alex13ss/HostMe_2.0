/**
 * @author Oleksandr Bandurka JavaScript for page with groups
 */

var table;
var size;
var page;
var selectedTablePage = 1;
var groupsType;
var groupStatus;
var userRole = "MODERATOR";
var order = {
	by : "createdAt",
	type : "DESC"
};

function checkRole() {
	if (userRole == "MODERATOR") {
		return true;
	} else {
		return false;
	}
}

function setupPaging() {
	$
			.ajax({
				type : 'GET',
				dataType : "json",
				data : {
					size : size,
					sender : groupsType
				},
				url : 'groups-paging',
				success : function(numberOfPages) {
					$("#table_pages").empty();
					$("#table_pages").append(
							'<li class="previousPage"><a href="#">«</a></li>');
					for (number = 1; number <= numberOfPages; number++) {
						$("#table_pages").append(
								'<li class="pageNumberButton"><a href="#">'
										+ number + '</a></li>');
					}
					$("#table_pages").append(
							'<li class="nextPage"><a href="#">»</a></li>');

					$("#table_pages > li")
							.click(
									function(element) {
										if ($(this).attr("class") == "previousPage"
												&& selectedTablePage != 1) {
											selectedTablePage--;
											showGroups();
										} else if ($(this).attr("class") == "nextPage"
												&& selectedTablePage != numberOfPages) {
											selectedTablePage++;
											showGroups();
										} else if ($(this).attr("class") == "pageNumberButton") {
											selectedTablePage = $(this).text();
											showGroups();
										}
									});
				}
			});
}

function getGroupsParameters() {
	return groupsType + "?size=" + size + "&page=" + selectedTablePage
			+ "&orderType=" + order.type + "&orderBy=" + order.by;
}

function groupsAjaxCallback() {
	setupPaging();
}

function showGroups() {
	table.fnReloadAjax(getGroupsParameters(), groupsAjaxCallback);
}

function approvedGroups(element) {
	if (element.className != 'active') {
		table.fnClearTable();
		table.fnReloadAjax("approved-groups");
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
					$("#groups-tabs li:first-child").addClass("active");
					groupsType = $("#groups-tabs > li.active").attr("id");
					
					size = $("#request_size").val();
					userRole = $('#UserRole').text();
					
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
														.click(aData,
																function(e) {
																	aData.status = status;
																	console.log(JSON.stringify(aData));
																	e.preventDefault();
																	$.ajax({
																				url : 'group-status-update',
																				dataType : 'json',
																				beforeSend : function() {
																				},
																				contentType : "application/json",
																				"type" : "POST",
																				data : JSON.stringify(aData),
																				success : function(response) {
																					$('td:eq(3)',nRow).html(response.status);
																				}
																			});
																});
												return parent_element;
											};

											var button = $("<button/>",
													{
														text : "Change status",
														"type" : "button",
														"data-toggle" : "dropdown",
														"class" : "btn btn-default dropdown-toggle",
													});

											function form_element(aData) {
												var approve = html_element(aData, "APPROVED");
												var pending = html_element(aData, "PENDING");
												var refuse = html_element(aData, "REFUSED");
												approve.html("Approve");
												pending.html("Pending");
												refuse.html("Refuse");

												var final_element = div.append(button);
												final_element.append(ul.append(li.append(approve)
																		.append(pending)
																		.append(refuse)));
												return final_element;
											}
											row = $('td:eq(4)', nRow).html(form_element(aData));
										},

										"bProcessing" : false,
										"bServerSide" : false,
										"bPaginate" : false,
										"bFilter" : false,
										"bInfo" : false,
										"bSort" : false,
										"bAutoWidth" : false,
										"sAjaxSource" : groupsType
														+ "?size=" + size + "&page="
														+ selectedTablePage
														+ "&orderType=" + order.type
														+ "&orderBy=" + order.by,
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
													"bVisible" : checkRole(),
													"sWidth" : "10%",
													"mData" : "status"
												}, {
													"bVisible" : checkRole(),
													"sWidth" : "10%",
													"mData" : "id"
												} ]
									});
					
					$("#request_size ").change(function() {
						size = $(this).val();
						selectedTablePage = 1;
						showGroups();
					});

					$("#groups-tabs > li").click(function() {
						groupsType = $(this).attr("id");
						selectedTablePage = 1;
						showGroups();
					});

					$("#groups-table-header > th").addClass(
							"custom_sorting_enabled");

					$("#groups-table-header > th").click(function() {
						currentOrderBy = order.by;
						order.by = $(this).attr("headers");
						if (currentOrderBy == order.by) {
							if (order.type == "DESC") {
								order.type = "ASC";
							} else {
								order.type = "DESC";
							}
						} else {
							order.type = "ASC";
						}
						showGroups();
					});
					
				});
