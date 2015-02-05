var routeDto = {
    name: "",
    description: "",
    originId: "",
    destinationId: "",
    waypointsId: []
};
var userPlacesIndex = 1;
var userBookedPlacesIndex = 1;
var popularPlacesIndex = 1;
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
    $popularPlacesUi = $("#popularPlaces");
    var $originDropUi = $("#originPlaceDrop");
    var $waypointsDropUi = $("#waypointsPlacesDrop");
    var $destinationDropUi = $("#destinationPlaceDrop");
    var $name = $("#name");
    var $description = $("#description");
    var $createRouteBtn = $("#createRoute");
    var $savingStatus = $("#savingStatus");
    $userPlaceNumber = $("#userPlaceNumber");
    $popularPlaceNumber = $("#popularPlaceNumber");
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
                getPlaces(userPlacesUrl, $userPlacesUi,
                    userPlacesIndex, $userPlaceNumber.val());
                getPlaces(userBookedPlacesUlr, $userBookedPlacesUi,
                    userBookedPlacesIndex, $userPlaceNumber.val());
            } else {
                getPlaces(popularPlacesUrl, $popularPlacesUi,
                    popularPlacesIndex, $popularPlaceNumber.val());
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
        url: url + "?page=0&size=5",
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
                drawPlaces($ui, userPlaces, 5, userPlacesIndex)
            } else if ($ui === $userBookedPlacesUi) {
                fillPlaces(data, bookedPlaces);
                drawPlaces($ui, bookedPlaces, 5, userBookedPlacesIndex)
            } else {
                fillPlaces(data, popularPlaces);
                drawPlaces($ui, popularPlaces, 5, popularPlacesIndex)
            }
            initDrag();
        }
    });
}

function fillPlaces(data, target) {
    for (var i = 0; i < data.length; i++) {
        target.push(data[i]);
    }
}

function drawPlaces($ui, data, elSize, pgInex) {
    $ui.html("");
    for (var i = pgInex * elSize - elSize; i < pgInex * elSize; i++) {
        if (data[i] != undefined) {
            $ui.append("<div class='dragPlace'>" +
            '<a href = "place?placeId=' + data[i].id + '">'
            + data[i].name + "</a>"
            + "</div>");
            $ui.children().last().data("Id", data[i].id);
            $ui.children().last().data("Address", data[i].address);
        }
    }
    if ($ui === $userBookedPlacesUi &&
        $userBookedPlacesUi.children().length == 0) {
        $userBookedPlacesUi.append(ifBookedPlacesEmpty());
    } else if ($ui === $userPlacesUi &&
        $userPlacesUi.children().length == 0) {
        $userPlacesUi.append(ifUserPlacesEmpty());
    } else {
        $ui.append("<div class='prev col-sm-6 btn btn-primary'>"
        + "<<"
        + "</div>");
        $ui.append("<div class='next col-sm-6 btn btn-primary'>"
        + ">>"
        + "</div>");
    }
    $(".prev").click(function(event) {
        event.stopImmediatePropagation();
        if (checkDecAvlbPlaces($(event.target).parents().attr('id'))) {
            $(event.target).attr("disabled", false);

        } else {
            $(event.target).attr("disabled", true);
        }
    });
    $(".next").click(function(event) {
        event.stopImmediatePropagation();
        if (checkIncAvlbPlaces($(event.target).parents().attr('id'))) {
            $(event.target).attr("disabled", false);

        } else {
            $(event.target).attr("disabled", true);
        }
    });
}

function checkDecAvlbPlaces(uiId) {
    if (uiId == "userBookedPlaces") {
        return userBookedPlacesIndex > 1;
    } else if (uiId == "userPlaces") {
        return userPlacesIndex > 1;
    } else {
        return popularPlacesIndex > 1;
    }
}

function checkIncAvlbPlaces(uiId) {
    if (uiId == "userBookedPlaces") {
        if (userPlaces.length >= (userPlacesIndex + 1) * $userPlaceNumber.val()) {
            alert("have")
        } else {
            getPlaces(userBookedPlacesUlr, $userBookedPlacesUi,
                userBookedPlacesIndex, $userPlaceNumber.val());
            alert(bookedPlaces.length);
        }
    } else if (uiId == "userPlaces") {

    } else {
        if (popularPlaces.length >= (popularPlaces + 1) * $popularPlaceNumber.val()) {
            alert("have");
        } else {
            getPlaces(popularPlacesUrl, $popularPlacesUi,
                popularPlacesIndex, $popularPlaceNumber.val());
            popularPlacesIndex++;
            alert(popularPlaces.length);
        }
    }
}

//TODO Booked Spec response
function ifBookedPlacesEmpty() {
    return "<div>"
        + "<a href='megaSearch'>"
        + "<div class='btn btn-primary' style='width: 100%'>"
        + "Search places!"
        + "</div>"
        + "</a>"
        + "</div>"
}

//TODO User Spec response
function ifUserPlacesEmpty() {
    return "<div>"
        + "<a href='sightseeings'>"
        + "<div class='btn btn-primary' style='width: 100%'>"
        + "Create Sight!"
        + "</div>"
        + "</a>"
        + "</div>"
        + "<div>"
        + "<a href='events'>"
        + "<div class='btn btn-primary' style='width: 100%'>"
        + "Create Event!"
        + "</div>"
        + "</a>"
        + "</div>"
        + "<div>"
        + "<a href='hosting-creation'>"
        + "<div class='btn btn-primary' style='width: 100%'>"
        + "Create Hosting!"
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
