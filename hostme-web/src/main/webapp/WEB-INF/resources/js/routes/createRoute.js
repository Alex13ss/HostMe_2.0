var routeDto = {
    name: "",
    description: "",
    originId: "",
    destinationId: "",
    waypointsId: []
    };
var userPlacesIndex = 0;
var popularPlacesIndex = 0;
var userPlaces = [];
var bookedPlaces = [];
var popularPlaces = [];
var userPlacesUrl = "getUserPlaces";
var userBookedPlacesUlr = "getUserBookedPlaces";
var popularPlacesUrl = "getPopularPlaces";
var haveOriginPlace = false;
var haveDestinationPlace = false;
var $userPlacesUi;
var $userBookedPlacesUi;
var $popularPlacesUi;
var $userPlaceNumber;
var $popularPlaceNumber;
$(document).ready(function() {
    $userPlacesUi = $("#userPlaces");
    $userBookedPlacesUi = $("#userBookedPlaces");
    $popularPlacesUi = $("#allPlaces");
    var $originDropUi = $("#originPlaceDrop");
    var $waypointsDropUi = $("#waypointsPlacesDrop");
    var $destinationDropUi = $("#destinationPlaceDrop");
    var $name = $("#name");
    var $description = $("#description");
    var $createRouteBtn = $("#createRoute");
    var $savingStatus = $("#savingStatus");
    var $userTabs = $("#userTabs");
    $userPlaceNumber = $("#userPlaceNumber");
    $popularPlaceNumber = $("#popularPlaceNumber");
    $userTabs.tabs();
    initListSize($userPlaceNumber);
    initListSize($popularPlaceNumber);
    initDropPlaces($userPlacesUi);
    initDropPlaces($popularPlacesUi);
    $originDropUi.droppable({
        out: function() {
            haveOriginPlace = false;
            $originDropUi.append(
                "<div class='hint'>" + "Picture" + "</div>");
        },
        drop: function(event, ui) {
            $originDropUi.find(".hint").remove();
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
    }).sortable({
        items: $(".dragPlace"),
        sort: function() {
            //TODO imp Sort
        }
    });
    $destinationDropUi.droppable({
        out: function() {
            haveDestinationPlace = false;
        },
        drop: function(event, ui) {
            $destinationDropUi.find(".hint").remove();
            haveDestinationPlace = true;
            addDestination = ui.draggable.data("Address");
            routeDto.destinationId = ui.draggable.data("Id");
            tryDrawDestination();
        }
    });
    $createRouteBtn.click(function() {
        if (validateRouteCreation($name, $description)) {
            routeDto.name = $name.val();
            routeDto.description = $description.val();
            $.ajax({
                url: "createRoute",
                type: "POST",
                dataType: "json",
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

function initListSize($ui) {
    $.ajax({
        url: "getPlaceDispNumber",
        dataType: "json",
        success: function(data) {
            for(var i = 0; i < data.length; i++) {
                $ui.append("<option>"
                    + data[i]
                    + "</option>"
                )
            }
            if ($ui === $userPlaceNumber) {
                getPlaces(userPlacesUrl, $userPlacesUi, userPlacesIndex, $userPlaceNumber.val());
                getPlaces(userBookedPlacesUlr, $userBookedPlacesUi, userPlacesIndex, $userPlaceNumber.val());
            } else {
                getPlaces(popularPlacesUrl, $popularPlacesUi, popularPlacesIndex, $popularPlaceNumber.val());
            }
        }
    })
}

function initDropPlaces($ui) {
    $ui.droppable();
}

function getPlaces(url, $ui, pageIndex, placeSize) {
    var obj = {
        pageIndex: pageIndex,
        placeSize: placeSize};
    $.ajax({
        url: url,
        dataType: "json",
        type: "POST",
        data: JSON.stringify(obj),
        contentType : 'application/json; charset=utf-8',
        beforeSend: function() {
            $ui.append("<div class='ion-loading-c'/>");
        },
        success: function(data) {
            $ui.find(".ion-loading-c").remove();
            if ($ui === $userPlacesUi) {
                fillPlaces(data, userPlaces);
                drawPlaces($ui, userPlaces)
            } else if ($ui === $userBookedPlacesUi) {
                fillPlaces(data, bookedPlaces);
                drawPlaces($ui, bookedPlaces)
            } else {
                fillPlaces(data, popularPlaces);
                drawPlaces($ui, popularPlaces)
            }
            initDrag();
            if ($ui === $userBookedPlacesUi &&
                    $userBookedPlacesUi.children().length == 0) {
                $userBookedPlacesUi.append(ifBookedPlacesEmpty());
            } else if ($ui === $userPlacesUi &&
                $userPlacesUi.children().length == 0) {
                $userPlacesUi.append(ifUserPlacesEmpty());
            }
        }
    });
}

function fillPlaces(data, target) {
    for (var i = 0; i < data.length; i++) {
        target.push(data[i]);
    }
}

function drawPlaces($ui, data) {
    for (var i = 0; i < data.length; i++) {
        $ui.append("<div class='dragPlace'>" +
        '<a href = "place?placeId=' + data[i].id + '">'
        + data[i].name + "</a>"
        + "</div>");
        $ui.children().last().data("Id", data[i].id);
        $ui.children().last().data("Address", data[i].address);
    }
}

//TODO Booked Spec response
function ifBookedPlacesEmpty() {
    return "<div>"
        + "<a href='megaSearch'>"
        + "<div class='btn btn-primary'>"
        + "Press"
        + "</div>"
        + "</a>"
        + "</div>"
}

//TODO User Spec response
function ifUserPlacesEmpty() {
    return "<div>"
        + "<a href='profile'>"
        + "<div class='btn btn-primary'>"
        + "Press"
        + "</div>"
        + "</a>"
        + "</div>"
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
