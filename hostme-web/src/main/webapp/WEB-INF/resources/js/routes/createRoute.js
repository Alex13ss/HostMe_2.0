var routeDto = {
    name: "",
    description: "",
    originId: "",
    destinationId: "",
    waypointsId: []
    };
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
    $originDropUi.droppable({
        drop: function(event, ui) {
            $("#originPlaceDrop").droppable("disable");
            addOrigin = ui.draggable.data("Address");
            routeDto.originId = ui.draggable.data("Id");
            drawDestination();
        }
    });
    var waypointsCounter = 0;
    var waypointsMAX = 8;
    $waypointsDropUi.droppable({
        drop: function(event, ui) {
            if (waypointsCounter != waypointsMAX) {
                waypointsCounter++;
            } else {
                $("#waypointsPlacesDrop").droppable("disable");
            }
        }
    });
    $destinationDropUi.droppable({
        drop: function(event, ui) {
            $("#destinationPlaceDrop").droppable("disable");
            addDestination = ui.draggable.data("Address");
            routeDto.destinationId = ui.draggable.data("Id");
            drawDestination();
        }
    });
    $("#createRoute").click(function() {
        routeDto.name = $("#name").val();
        routeDto.description = $("#description").val();
        $.ajax({
            url: "/createRoute",
            dataType: "json",
            type: "POST",
            data: JSON.stringify(routeDto),
            contentType: 'application/json',
            beforeSend: function() {

            },
            success: function() {

            }
        })
    });
});

function initDragElements(url, $ui) {
    $.ajax({
        url: url,
        dataType: "json",
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
                    $ui.children().last().data("Id", result[i].id);
                    $ui.children().last().data("Address", result[i].address);
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
        cursor: "move",
        stack: "#originPlaceDrop"
    });
}