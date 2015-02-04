/**
 * @author Oleksandr Bandurka JavaScript for page with notifications
 */

$(document).ready(function() {
	loadNotificationsAjax();
});

function loadNotificationsAjax() {
	$.ajax({
		url : "all-notifications",
		type : 'GET',
		dataType : "json",
		beforeSend : function() {
			$("#timeline").html(loader);
		},
		success : function(result) {
			if (result.length > 0) {
				$("#timeline").html("");
				showNotifications(result);
			} else {
				$("#timeline").html("Failed to load notifications");
			}
		}
	});
}

function showNotifications(notifications) {
	var timeline = document.getElementById("timeline");
	for (var i = 0; i < notifications.length; i++) {
		timeline.appendChild(getNotificationDateLabel(notifications[i]));
		timeline.appendChild(getNotificationItem(notifications[i]));
	}
}

function getNotificationDateLabel(item) {
	var li = document.createElement("LI");
	li.className = "time-label";

	var small = document.createElement("SPAN");
	small.className = "bg-green";
	var date = document.createTextNode(item.notificationDate);

	small.appendChild(date);

	li.appendChild(small);

	return li;
}

function getNotificationItem(item) {
	var li = document.createElement("LI");

	var i = document.createElement("I");
	i.className = "fa fa-comments bg-blue";

	var div = document.createElement("DIV");
	div.className = "timeline-item";

	var small = document.createElement("SPAN");
	small.className = "time";

	var i2 = document.createElement("I");
	i2.className = "fa fa-clock-o";
	var time = document.createTextNode(' ' + item.notificationTime);

	var a = document.createElement("A");
	a.href = "group?id=" + item.groupId;

	var h3 = document.createElement("H3");
	h3.className = "timeline-header";

	var text = document.createTextNode(item.notifyMessage);
	h3.appendChild(text);
	a.appendChild(h3);

	div.appendChild(a);
	div.appendChild(i2);
	div.appendChild(time);

	li.appendChild(i);
	li.appendChild(div);

	return li;
}

var loader = $(
		"<img/>",
		{
			src : "resources/images/ajax-loader-128px.gif",
			style : "width:128px;  display: block; margin-left: auto; margin-right: auto;"
		});
