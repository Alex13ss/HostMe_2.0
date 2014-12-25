var table,row;
var test, test1, test2;
var action_list;
var request_from_me_data;
var request_to_me_data;
var request_from_me_url = "request-sent-history";
var request_to_me_url = "request-obtain-history";


function refresh(){
	if($("#request_from_me").attr('class')!='active'){
		table.fnReloadAjax();
	}else{
		table.fnReloadAjax("request-sent-history");
	}
	
	
}



function initialize_request_from_me(element) {
	if (element.className != 'active') {
		table.fnClearTable();
		table.fnAddData(request_from_me_data);
		
	}

}
function initialize_request_to_me(element) {
	if (element.className != 'active' && request_to_me_data == undefined) {
		request_from_me_data = table.fnGetData();
		table.fnReloadAjax('request-obtain-history');

		} else if (request_to_me_data != undefined) {
		table.fnClearTable();
		table.fnAddData(request_to_me_data);
	}

}

$(document)
		.ready(
				function() {
					

					table = $("table.table-bordered")
							.dataTable(
									{

										"sAjaxDataProp" : "",
										/*
										 * "fnServerData": function ( sSource,
										 * aoData, fnCallback, oSettings ) {
										 * oSettings.jqXHR = $.ajax( {
										 * "dataType": 'json', "type": "GET",
										 * "url": sSource, "data": aoData,
										 * "success": function(aoData){
										 * action_list=Object.keys(aoData);
										 * aoData=aoData[action_list];
										 * oSettings.aoData=aoData; test=aoData;
										 * action_list= action_list[0];
										 * action_list=action_list.replace(/\[|]/g,"");
										 * action_list= action_list.split(", "); } } ); },
										 */

										
										  "fnInitComplete": function(settings,
										  json) { 
											  
												
										  },
										  
										  
// "fnDrawCallback": function(
// oSettings ) {
//	 if(oSettings.sAjaxSource="request-sent-history "){
//		 var a=$('.dropdown-menu>li>a:contains("Reject"):contains("Approve")');
//			console.log(a);
//			console.log(oSettings.sAjaxSource);
//	 }else{
//		 $('.dropdown-menu>li>a:contains("Refuse"):contains("Send again")').hide();
//	 };
//
// },
										 

										"fnRowCallback" : function(nRow, aData,
												iDisplayIndex,
												iDisplayIndexFull) {
											var ul = $("<ul/>", {
												'class' : "dropdown-menu"
											});
											var li = $("<li/>"

											);
											var div = $("<div/>", {
												"class" : "input-group-btn"
											});
											function html_element(aData,status){
												var parent_element=$("<a/>", {
											
												"href" : "#"
											}).click(aData,function(e){
												aData.status=status;
												e.preventDefault();
												$.ajax({
													url : 'request-update',
													'dataType': 'json',
													beforeSend : function() {
														

													},
													contentType: "application/json",
													"type": "POST",
													
													data : JSON.stringify(aData),

													success : function(response) {
														$('td:eq(4)', nRow).html(response.status);
													
														
													}
												
											});
									});
												return parent_element;
										};
												

											var button = $(
													"<button/>",
													{
														text : "Action",
														"type":"button",
														"data-toggle" : "dropdown",
														"class" : "btn btn-default dropdown-toggle",

													});
											
											function form_element(aData){
												var reject=html_element(aData,"REJECTED");
												var approve=html_element(aData,"APPROVED");
												var refuse=html_element(aData,"REFUSED");
												var send=html_element(aData,"PENDING");
												reject.html("Reject");
												approve.html("Approve");
												refuse.html("Refuse");
												send.html("Send Again");
												
												var final_element =div.append(button);
													final_element.append(
															ul.append(li.append(send)
																	.append(reject)
																	.append(approve)
																	.append(refuse)));
													
													return final_element;
											}
											var currentDate = new Date();
											if (aData.endDate > currentDate
													.getMilliseconds()) {
												row=$('td:eq(6)', nRow)
														.html(form_element(aData));
												
											}

										},

										"bProcessing" : false,
										"bServerSide" : false,
										"sAjaxSource" : "request-sent-history",
										"aoColumns" : [
												{
													"mData" : "beginDate",
													"mRender" : function(data,
															type, full) {
														return new Date(data)
																.toLocaleString()
																.split(" ")[0];
													}
												},
												{
													"mData" : "endDate",
													"mRender" : function(data,
															type, full) {
														return new Date(data)
																.toLocaleString()
																.split(" ")[0];
													}
												},
												{
													"mData" : "notes",
												},
												{
													"mData" : "hosting",
													"mRender" : function(data,
															type, full) {
														return '<a href=${pageContext.request.contextPath}/hoster?hosterId='
																+ data.owner.userId
																+ '>'
																+ data.owner.firstName
																+ ' '
																+ data.owner.lastName
																+ '</a>';
													}
												},
												{
													"mData" : "status"
												},
												{
													"mData" : "hosting",
													"mRender" : function(data,
															type, full) {
														return '<a href=hosting?hostingId='
																+ data.hostingId
																+ '>'
																+ data.address
																+ '</a>';
													}
												}, {
													"mData" : "requestId"

												}

										]
									});

					table.on( 'draw', function () {
						if(table.fnSettings().sAjaxSource=="request-sent-history"){
							$('.dropdown-menu>li>a:contains("Reject"),a:contains("Approve")').hide();
						 }else{
							 $('.dropdown-menu>li>a:contains("Refuse"),a:contains("Send Again")').hide();
					}	
					}
					 );
				});



function change_request_status(id, element) {
	$.ajax({
		url : 'response',
		beforeSend : function() {
			console.log('start');
			element.style.display = "none";
			loader.insertAfter(element);

		},
		data : {
			'beginDate' : beginDate,
			'endDate' : endDate,
			hostingId : id,
		},

		success : function(response) {
			element.nextSibling.style.display = "none";
			if (response == "Request send") {
				successRequest.insertAfter(element);

			} else {
				failRequest.insertAfter(element);

			}
		},

	});
	

}