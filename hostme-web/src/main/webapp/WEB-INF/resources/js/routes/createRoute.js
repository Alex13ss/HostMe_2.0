var routeDto = {
    name: "",
    description: "",
    originId: "",
    destinationId: "",
    waypointsId: []
    };
var userPlacesUrl = "getUserPlaces";
var popularPlacesUrl = "getPopularPlaces";
var haveOriginPlace = false;
var haveDestinationPlace = false;
$(document).ready(function() {
    var $userPlacesUi = $("#userPlaces");
    var $popularPlacesUi = $("#allPlaces");
    var $originDropUi = $("#originPlaceDrop");
    var $waypointsDropUi = $("#waypointsPlacesDrop");
    var $destinationDropUi = $("#destinationPlaceDrop");
    var $name = $("#name");
    var $description = $("#description");
    var $createRouteBtn = $("#createRoute");
    var $savingStatus = $("#savingStatus");
    initDragPlaces(userPlacesUrl, $userPlacesUi);
    initDragPlaces(popularPlacesUrl, $popularPlacesUi);
    initDropPlaces($userPlacesUi);
    initDropPlaces($popularPlacesUi);
    $originDropUi.droppable({
        out: function() {
            haveOriginPlace = false;
        },
        drop: function(event, ui) {
            haveOriginPlace = true;
            addOrigin = ui.draggable.data("Address");
            routeDto.originId = ui.draggable.data("Id");
            ui.draggable.data("dropPlace", $originDropUi);
            tryDrawDestination();
        }
    });
    var waypointsCounter = 0;
    var waypointsMAX = 8;
    $waypointsDropUi.droppable({
        drop: function(event, ui) {
            routeDto.waypointsId.push(ui.draggable.data("Id"));
            waypts.push({
                location: ui.draggable.data("Address")
            });
            tryDrawDestination();
        }
    });
    $destinationDropUi.droppable({
        out: function() {
            haveDestinationPlace = false;
        },
        drop: function(event, ui) {
            haveDestinationPlace = true;
            addDestination = ui.draggable.data("Address");
            routeDto.destinationId = ui.draggable.data("Id");
            tryDrawDestination();
        }
    });
    $createRouteBtn.click(function() {
        if (validateRouteCreation($name, $description)) {
            var buttonName = $createRouteBtn.val();
            routeDto.name = $name.val();
            routeDto.description = $description.val();
            $.ajax({
                url: "/createRoute",
                dataType: "json",
                type: "POST",
                data: JSON.stringify(routeDto),
                contentType: 'application/json',
                beforeSend: function () {
                    $savingStatus.html("Saving!");
                    $createRouteBtn.attr("disabled", true);
                },
                success: function () {
                    $savingStatus.html("Saved!");
                    $createRouteBtn.attr("disabled", false);
                }
            })
        } else {
            alert("You have empty fields!");
        }
    });
    $name.change(function() {
        checkName($name);
    });
    $description.change(function() {
        checkDescription($description);
    });
});

function initDropPlaces($ui) {
    $ui.droppable();
}

function initDragPlaces(url, $ui) {
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
                    $ui.append("<div class='dragPlace'>" +
                        '<a href = "'+ result[i].link + '">'
                        + result[i].name + "</a>"
                    + "</div>");
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

function tryDrawDestination() {
    if (haveDestinationPlace && haveDestinationPlace) {
        drawDestination()
    }
}
