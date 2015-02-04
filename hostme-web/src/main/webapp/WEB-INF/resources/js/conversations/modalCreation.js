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

	 //$("#moderatorsResult").html("Pending...");
	
	$.ajax({
		url: "findUser.json", 
		type : 'GET',
		data : {
			input : inputData
		},
		dataType : "json",
		beforeSend : function() {
			$("#moderatorsResult").html(loader);

		},
		success: function(result) {
			if (result.length > 0) {
				allResults = result;
				showModerators(result);
				$(".moderator").click(function(){
					  var moderatorId = $(this).attr('class').replace('moderator ', '');
					  $("#moderatorsResult")
					  addModerator(moderatorId);
					});
			} else {
				$("#moderatorsResult").html("Failed to load users");
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
		
		var btn = document.createElement("BUTTON");
		btn.type = "button";
		btn.className = "btn btn-success removeModer";
		btn.onclick = removeModerator;
		var t = document.createTextNode(allResults[index].name);
		btn.appendChild(t); 
		
		
		var login = document.createElement("INPUT");
		var nextId = getNextModeratorId();
		login.type = "hidden";
		login.setAttribute("id", "moderatorLogins" + nextId);
		login.setAttribute("name", "moderatorLogins[" + nextId + "]");
		login.setAttribute("value", allResults[index].id);
		btn.appendChild(login);
		
		var remove = document.createElement("SPAN");
		remove.className = "fa fa-fw fa-times";
		remove.setAttribute("aria-hidden", true);
		
		btn.appendChild(remove);
		
		moderatorLogins.appendChild(btn);
	}
}

function removeModerator() {
	this.parentElement.removeChild(this);
	//rebuildIndexed
	var moderatorLogins = document.getElementById("moderatorLogins");
	var elements = moderatorLogins.getElementsByTagName("input");
	for (var i = 0; i < elements.length; i++) {
		elements[i].setAttribute("id", "moderatorLogins" + i);
		elements[i].setAttribute("name", "moderatorLogins[" + i + "]");
	}
}

function getNextModeratorId()
{
	var moderatorLogins = document.getElementById("moderatorLogins");
	return moderatorLogins.getElementsByTagName("input").length; 
}

var loader = $(
		"<img/>",
		{
			src : "resources/images/ajax-loader-128px.gif",
			style : "width:128px;  display: block; margin-left: auto; margin-right: auto;"
		});