var table;
var size;
var page;
var selectedTablePage = 1;
var sightseeingsType = "all-sightseeings";
var order = {
	by : "name",
	type : "ASC"
};

function setupPaging() {
	$
			.ajax({
				type : 'GET',
				dataType : "json",
				data : {
					size : size,
					sender : sightseeingsType
				},
				url : 'pagingSightseeings',
				success : function(numberOfPages) {
					$("#table_pages").empty();
					$("#table_pages").append(
							'<li class="previousPage"><a href="#">«</a></li>');
					for (number = 1; number <= numberOfPages; number++) {
						$("#table_pages").append(
								'<li class="pageNumberButton"><a href="#">' + number
										+ '</a></li>');
					}
					$("#table_pages").append(
							'<li class="nextPage"><a href="#">»</a></li>');

					$("#table_pages > li")
							.click(
									function(element) {
										if ($(this).attr("class") == "previousPage"
												&& selectedTablePage != 1) {
											selectedTablePage--;
											showSightseeings();
										} else if ($(this).attr("class") == "nextPage"
												&& selectedTablePage != numberOfPages) {
											selectedTablePage++;
											showSightseeings();
										} else if ($(this).attr("class") == "pageNumberButton") {
											selectedTablePage = $(this).text();
											showSightseeings();
										}
									});
				}
			});
}
function getSightseeingsParameters() {
	return sightseeingsType + "?size=" + size + "&page=" + selectedTablePage
			+ "&orderType=" + order.type + "&orderBy=" + order.by;
}

function sightseeingsAjaxCallback() {
	setupPaging();
}

function showSightseeings() {
	table.fnReloadAjax(getSightseeingsParameters(), sightseeingsAjaxCallback);
}

jQuery()
		.ready(
				function() {
					var priceCategories = [];
					$
							.getJSON(
									'getPriceCategories',
									function(data) {
										$.each(data, function(index, val) {
											priceCategories[index] = val;
										});
										var $priceOption = '<option value="0">Select price category</option>';
										for (var i = 0; i < priceCategories.length; i++) {
											var price = priceCategories[i].priceCategory;
											$priceOption += '<option value="'
													+ price + '">' + price
													+ '</option>';
										}
										;
										$('#price').html($priceOption);
									});
				});
jQuery()
		.ready(
				function() {
					var types = [];
					$
							.getJSON(
									'getAllTypes',
									function(data) {
										$.each(data, function(index, val) {
											types[index] = val;
										});
										var $typeOption = '<option value="0">Select sightseeing type</option>';
										for (var i = 0; i < types.length; i++) {
											var type = types[i];
											$typeOption += '<option value="'
													+ type + '">' + type
													+ '</option>';
										}
										;
										$('#sstype').html($typeOption);
									});
				});
jQuery()
		.ready(
				function() {
					var tabCountry = [];
					$
							.getJSON(
									'getAllCountries',
									function(data) {
										$.each(data, function(index, val) {
											tabCountry[index] = val;
										});
										var $countryOption = '<option value="0">Select country</option>';
										for (var i = 0; i < tabCountry.length; i++) {
											var country = tabCountry[i].country;
											$countryOption += '<option value="'
													+ country + '">' + country
													+ '</option>';
										}
										;
										$('#country').html($countryOption);
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
											sightseeingsAjaxCallback();
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
										"sAjaxSource" : sightseeingsType
												+ "?size=" + size + "&page="
												+ selectedTablePage
												+ "&orderType=" + order.type
												+ "&orderBy=" + order.by,
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
												} ]
									});
					table
							.on(
									'draw',
									function() {
										if (table.fnSettings().sAjaxSource
												.indexOf("all-sightseeings") > -1) {
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
						showSightseeings();
					});

					$("#sightseeingsTypesNav > li").click(function() {
						sightseeingsType = $(this).attr("id");
						showSightseeings();
					});

					$("#sightseeingsTableHeader > th").addClass(
							"custom_sorting_enabled");

					$("#sightseeingsTableHeader > th").click(function() {
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
						console.log(order.type);
						showSightseeings();
					});

				});

