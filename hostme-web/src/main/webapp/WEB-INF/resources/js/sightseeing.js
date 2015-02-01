var table;

function allSightseeings(element) {
	if (element.className != 'active') {
		table.fnClearTable();
		table.fnReloadAjax("all-sightseeings");

	}
}
function favouriteSightseeings(element) {
	if (element.className != 'active') {
		table.fnClearTable();
		table.fnReloadAjax("favourite-sightseeings");

	}
}

jQuery().ready(
		function() {
			var priceCategories = [];
			$.getJSON('getPriceCategories', function(data) {
				$.each(data, function(index, val) {
					priceCategories[index] = val;
				});
				var $priceOption = '<option value="0">Select price category</option>';
				for (var i = 0; i < priceCategories.length; i++) {
					var price = priceCategories[i].priceCategory;
					$priceOption += '<option value="' + price + '">'
							+ price + '</option>';
				};
				$('#price').html($priceOption);
			});
		});
jQuery().ready(
		function() {
			var types = [];
			$.getJSON('getAllTypes', function(data) {
				$.each(data, function(index, val) {
					types[index] = val;
				});
				var $typeOption = '<option value="0">Select sightseeing type</option>';
				for (var i = 0; i < types.length; i++) {
					var type = types[i];
					$typeOption += '<option value="' + type + '">'
							+ type + '</option>';
				};
				$('#sstype').html($typeOption);
			});
		});
jQuery()
		.ready(
				function() {
					var tabCountry = [];
					$.getJSON('getAllCountries', function(data) {
						$.each(data, function(index, val) {
							tabCountry[index] = val;
						});
						var $countryOption = '<option value="0">Select country</option>';
					for (var i = 0; i < tabCountry.length; i++) {
						var country = tabCountry[i].country;
						$countryOption += '<option value="' + country + '">'
								+ country + '</option>';
					};
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
												},
												{
													"mData" : "website"
												},
												{
													"mData" : "sightseeingType"
												},
												{
													"mData" : function(data,
															type, full) {
														return '<a href="sightseeing/delete/'
																+ data.id
																+ '" class="text-red"/>'
																+ 'Delete'
																+ '<span class="fa fa-trash-o"></span></a>'
													}
												} ]
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