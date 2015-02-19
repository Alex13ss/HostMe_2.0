var table;
var size;
var page;
var selectedTablePage = 1;
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
var order = {
	    by: "login",
	    type: "ASC"
	};

function usersAjaxCallback() {
    setupPaging();
}

function getUsersParameters() {
    return usersType + "?size=" + size + "&page=" + selectedTablePage + "&orderType=" + order.type + "&orderBy=" + order.by;
}
function showUsers() {
    table.fnReloadAjax(getUsersParameters(), usersAjaxCallback);
}
function allUsers(element) {
	if (element.className != 'active') {
		table.fnReloadAjax(getUsersParameters(), usersAjaxCallback);
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

function setupPaging() {
    $.ajax({
        type: 'GET',
        dataType: "json",
        data: {
            size: size,
            sender: usersType
        },
        url: 'pagingUsers',
        success: function(numberOfPages) {
            $("#table_pages").empty();
            $("#table_pages").append(
                '<li class="previousPage"><a>«</a></li>');
            for (number = 1; number <= numberOfPages; number++) {
                $("#table_pages").append(
                    '<li class="pageNumberButton"><a>' + number + '</a></li>');
            }
            $("#table_pages").append(
                '<li class="nextPage"><a>»</a></li>');

            $("#table_pages > li")
                .click(
                    function(element) {
                        if ($(this).attr("class") == "previousPage" && selectedTablePage != 1) {
                            selectedTablePage--;
                            showEvents();
                        } else if ($(this).attr("class") == "nextPage" && selectedTablePage != numberOfPages) {
                            selectedTablePage++;
                            showEvents();
                        } else if ($(this).attr("class") == "pageNumberButton") {
                            selectedTablePage = $(this).text();
                            showUsers();
                        }
                    });

        }
    });
}

$(document)
		.ready(
				function() {
					$("#usersTypesNav li:first-child").addClass("active");
		            usersType = $("#usersTypesNav > li.active").attr("id");
		            size = $("#request_size").val();
					table = $("table.table-bordered")
							.dataTable(
									{
										"sAjaxDataProp" : "",
										"fnInitComplete" : function(settings,
						                        json) {
					                        usersAjaxCallback();
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

										"bProcessing": false,
					                    "bServerSide": false,
					                    "bPaginate": false,
					                    "bFilter": false,
					                    "bInfo": false,
					                    "bSort": false,
										"sAjaxSource" : usersType + "?size=" + size + "&page=" + selectedTablePage + "&orderType=" + order.type + "&orderBy=" + order.by,
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
					$("#request_size ").change(function() {
		                size = $(this).val();
		                selectedTablePage = 1;
		                showUsers();


		            });

		            $("#usersTypesNav > li").click(function() {
		                usersType = $(this).attr("id");
		                showUsers();

		            });

		            $("#usersTableHeader > th").addClass(
		                "custom_sorting_enabled");
		            
		            $("#usersTableHeader > th").click(function() {
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
		                showUsers();
		            });
					
				});
