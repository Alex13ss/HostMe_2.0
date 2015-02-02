var allResults = null;

$(document).ready(
		
		function() {
			var timer = null;
			$('#moderators').keypress(function() {
				clearTimeout(timer);
				timer = setTimeout(function() {
					Search();
				}, 500);
				
				$('#moderatorsResult').slimScroll({
				    height: '250px'
				  });
			});
		});

function Search() {
	
	var inputData = document.getElementById("moderators").value;

	 $("#moderatorsResult").html("Pending...");
	
	$.ajax({
		url: "findUser.json", 
		type : 'GET',
		data : {
			input : inputData
		},
		dataType : "json",
		success: function(result) {
			if (result.length > 0) {
				allResults = result;
				showModerators(result);
				$(".moderator").click(function(){
					  var moderatorId = $(this).attr('class').replace('moderator ', '');
					  $("#moderatorsResult")
					  addModerator(moderatorId);
					});
			}
	     }
	});
}

function showModerators(result) {
	$("#moderatorsResult").html("");

	var moderators = document.getElementById("moderatorsResult");
	for (var i = 0; i < result.length; i++) {
		var div = document.createElement("DIV");
		div.className =  "moderator " + i;
		var btn = document.createElement("DIV");
		var t = document.createTextNode(result[i].name);
		btn.appendChild(t); 
		btn.className = "btn btn-default";
		div.appendChild(btn);
		moderators.appendChild(div);
    }
}

function addModerator(index) {
	var moderatorLogins = document.getElementById("moderatorLogins");
	var addedLogins = moderatorLogins.getElementsByTagName("input");
	var exists = false;
	for (var i = 0; i < addedLogins.length; i++) {
		if (addedLogins[i].getAttribute("value") == allResults[index].id) {
			exists = true;			
		}
	}
	if (!exists) {
		
		var btn = document.createElement("DIV");
		var t = document.createTextNode(allResults[index].name);
		btn.appendChild(t); 
		btn.className = "btn btn-success";
		
		var login = document.createElement("INPUT");
		var nextId = getNextModeratorId();
		login.type = "hidden";
		login.setAttribute("id", "moderatorLogins" + nextId);
		login.setAttribute("name", "moderatorLogins[" + nextId + "]");
		login.setAttribute("value", allResults[index].id);
		
		moderatorLogins.appendChild(btn);
		moderatorLogins.appendChild(login);
	}
}

function getNextModeratorId()
{
	var moderatorLogins = document.getElementById("moderatorLogins");
	return moderatorLogins.getElementsByTagName("input").length; 


}