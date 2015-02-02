var table;
var size;
var page;
var selectedTablePage = 1;
var eventsType = "all-events";
var currentEventType = "my-events";
var order = {
	by : "name",
	type : "ASC"
};

function getEventsParameters() {
	return eventsType + "?size=" + size + "&page=" + selectedTablePage
			+ "&orderType=" + order.type + "&orderBy=" + order.by;
}

function eventsAjaxCallback() {
	setupPaging();
	calendarUpdate();

}

function calendarUpdate() {
	if (eventsType != "all-events") {
//		$("#calendar").fullCalendar('removeEvents');
//		$('#calendar').fullCalendar('rerenderEvents' );
		tableEvents = table.fnGetData();
		var calendarEvents = [];
		for (eventNum in tableEvents) {

			calendarEvents.push({
				title : tableEvents[eventNum].name,
				start : tableEvents[eventNum].startDate,
				end : tableEvents[eventNum].endDate,
				url : 'event?id=' + tableEvents[eventNum].id,
			});
		}

		$('#calendar').fullCalendar({
			allDayDefault : 'false',
			eventLimit : 6,
			events : calendarEvents,
		});
	}
}

function showEvents() {
	if (eventsType == "all-events") {
		$("#calendar").fullCalendar('destroy');
	}
	table.fnClearTable();
	table.fnReloadAjax(getEventsParameters(), eventsAjaxCallback);
}

function setupPaging() {
	$
			.ajax({
				type : 'GET',
				dataType : "json",
				data : {
					size : size,
					sender : eventsType
				},
				url : 'paging',
				success : function(numberOfPages) {
					$("#table_pages").empty();
					$("#table_pages").append(
							'<li class="previousPage"><a>«</a></li>');
					for (number = 1; number <= numberOfPages; number++) {
						$("#table_pages").append(
								'<li class="pageNumberButton"><a>' + number
										+ '</a></li>');
					}
					$("#table_pages").append(
							'<li class="nextPage"><a>»</a></li>');

					$("#table_pages > li")
							.click(
									function(element) {
										if ($(this).attr("class") == "previousPage"
												&& selectedTablePage != 1) {
											selectedTablePage--;
											showEvents();
										} else if ($(this).attr("class") == "nextPage"
												&& selectedTablePage != numberOfPages) {
											selectedTablePage++;
											showEvents();
										} else if ($(this).attr("class") == "pageNumberButton") {
											selectedTablePage = $(this).text();
											showEvents();
										}
									});

				}
			});
}

jQuery()
		.ready(
				function() {
					var tabCountry = [];
					$.getJSON('getAllCountries.json', function(data) {
						$.each(data, function(index, val) {
							tabCountry[index] = val;
						});
					});
					$('#country')
							.change(
									function(event) {
										$country = $(this).val();
										var $htmlOption = '<option value="0">Select city</option>';
										for (var i = 0; i < tabCountry.length; i++) {
											if ($country === tabCountry[i].country) {
												for (var j = 0; j < tabCountry[i].city.length; j++) {
													var town = tabCountry[i].city[j].city;
													$htmlOption += '<option value="'
															+ town
															+ '">'
															+ town
															+ '</option>';
													console.log($htmlOption);
												}
											}
										}
										$('#city').html($htmlOption);
									});
				});

$(document)
		.ready(
				function() {

					size = $("#request_size").val();

					table = $("table.table-bordered")
							.dataTable(
									{
										"sAjaxDataProp" : "",
										"fnInitComplete" : function(settings,
												json) {
											eventsAjaxCallback();
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
										"bPaginate" : false,
										"bFilter" : false,
										"bInfo" : false,
										"bSort" : false,
										"sAjaxSource" : eventsType + "?size="
												+ size + "&page="
												+ selectedTablePage
												+ "&orderType=" + order.type
												+ "&orderBy=" + order.by,
										"aoColumns" : [
												{
													"mData" : function(data,
															type, full) {
														return '<a href=event?id='
																+ data.id
																+ '>'
																+ data.name
																+ '</a>';
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
										if (table.fnSettings().sAjaxSource
												.indexOf("all-events") > -1) {
											$(
													'.dropdown-menu>li>a:contains("Reject"),a:contains("Approve")')
													.hide();
										} else {
											$(
													'.dropdown-menu>li>a:contains("Refuse"),a:contains("Send Again")')
													.hide();
										}
									});

					$("#request_size ").change(function() {
						size = $(this).val();
						selectedTablePage = 1;
						showEvents();

					});

					$("#eventsTypesNav > li").click(function() {
						eventsType = $(this).attr("id");
						showEvents();

					});

					$("#eventsTableHeader > th").addClass(
							"custom_sorting_enabled");

					$("#eventsTableHeader > th").click(function() {
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
						showEvents();
					});

				});
