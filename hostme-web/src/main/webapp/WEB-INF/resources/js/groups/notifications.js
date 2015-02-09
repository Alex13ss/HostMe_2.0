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
			$("#timeline").html('');
			if (result.length > 0) {
				$("#timeline").html("");
				showNotifications(result);
			} else {
				timeline.appendChild(haveNoNotifications());
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
	div.style.backgroundColor = "#E0F0FF"

	var small = document.createElement("SPAN");
	small.className = "time";

	var i2 = document.createElement("I");
	i2.className = "fa fa-clock-o";

	var time = document.createTextNode(' ' + item.notificationTime);

	var a = document.createElement("A");
	a.href = "group?id=" + item.groupId;

	var h3 = document.createElement("H3");
	h3.className = "timeline-header";

	var span = document.createElement("SPAN");
	span.className = "fa fa-fw fa-times";
	span.onclick = removeNotify;
	span.style.cssFloat = "right";
	span.style.color = "blue";

	var spanId = document.createElement("SPAN");
	spanId.className = "notification " + item.notifyId;
	spanId.style.display = "none";

	span.appendChild(spanId);

	div.appendChild(span);

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

function haveNoNotifications() {
	var li = document.createElement("LI");

	var div = document.createElement("DIV");
	div.className = "timeline-item";
	div.style.backgroundColor = "#E0F0FF"

	var h3 = document.createElement("H3");
	h3.className = "timeline-body";
	var text = document.createTextNode('Here is no notifications for you! :)');
	h3.appendChild(text);

	div.appendChild(h3);
	li.appendChild(div);

	return li;
}

function removeNotify() {
	var id = $(this).find('.notification').attr('class').replace(
			'notification ', '');
	removeNotification(id);
}

function removeNotification(id) {
	$.ajax({
		url : "delete-notification",
		type : 'GET',
		data : {
			id : id
		},
		dataType : "json",
		beforeSend : function() {
			$("#timeline").html('');
		},
		success : function(result) {
			$("#timeline").html('');
			if (result.length > 0) {
				showNotifications(result);
			} else {
				timeline.appendChild(haveNoNotifications());
			}
		}
	});
}

var loader = $(
		"<img/>",
		{
			src : "resources/images/ajax-loader-128px.gif",
			style : "width:128px;  display: block; margin-left: auto; margin-right: auto;"
		});
