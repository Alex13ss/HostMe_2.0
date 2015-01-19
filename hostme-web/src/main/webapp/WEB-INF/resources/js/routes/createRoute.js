var placeDto = [{
        name: "Name"
    }];
var userPlacesUrl = "getUserPlaces";
var popularPlacesUrl = "getPopularPlaces";
$(document).ready(function() {
    var $userPlacesUi = $("#userPlaces");
    var $popularPlacesUi = $("#allPlaces");
    var $originDropUi = $("#originPlaceDrop");
    var $waypointsDropUi = $("#waypointsPlacesDrop");
    var $destinationDropUi = $("#destinationPlaceDrop");
    initDragElements(userPlacesUrl, $userPlacesUi);
    initDragElements(popularPlacesUrl, $popularPlacesUi);
    $(".places").droppable({
        drop: function(event, ui) {

        }
    });
    $originDropUi.droppable({
        drop: function(event, ui) {
            $("#originPlaceDrop").droppable("disable");
            alert(ui.draggable.data("Test"));
        }
    });
    var waypointsCounter = 0;
    var waypointsMAX = 8;
    $waypointsDropUi.droppable({
        drop: function(event, ui) {
            if (waypointsCounter == waypointsMAX) {
                $("#waypointsPlacesDrop").droppable("disable");
            } else {
                waypointsCounter++;
            }
        }
    });
    $destinationDropUi.droppable({
        drop: function(event, ui) {
            $("#destinationPlaceDrop").droppable("disable");
        }
    });
});

function initDragElements(url, $ui) {
    $.ajax({
        url: url,
        dataType: "json",
        type: "POST",
        data: JSON.stringify(placeDto),
        contentType: 'application/json',
        beforeSend: function() {
            $ui.append("LOADING!");
        },
        success: function(result) {
            $ui.html("");
            if (result.length == 0) {
                $ui.append("No data found")
            } else {
                for (var i = 0; i < result.length; i++) {
                    $ui.append("<div class='dragPlace'>" + result[i].name + "</div>");
                    $("#allPlaces div").last().data("Test", result[i].name);
                }

                initDrag();
            }
        }
    });
}

function initDrag() {
    $(".dragPlace").draggable({
        snap: ".dropArea",
        snapMode: "inner",
        revert: "invalid",
        cursor: "move"
    });
}