var searchValue = {
    request: "",
    type: ""
};
var timer;
var bufInput = "";
var $searchBox;
var $search;
var $searchType;
var $searchCity;
var $searchResult;
var $btnShowLoc;
var $btnBookPlace;
$(document).ready(function() {
    $searchBox = $("#searchBox");
    $search = $("#search");
    $search.hide();
    $searchResult = $("#searchResult");
    $searchType = $("#searchType");
    $searchCity = $("#searchCity");
    initSearchType();
    $searchCity.autocomplete({
        minLength: 2,
        source: function(request, responce) {
            $.ajax({
             url: "getCities",
             dataType: "json",
             type: "POST",
             data: request.term,
             success: function(data) {
                 var result = [];
                 for (var i = 0; i < data.length; i++) {
                     result.push(data[i].city);
                 }
                 responce(result);
             }
            })},
        select: function() {
            search($searchCity.val());
        }}
    ).keyup(function() {
            search($searchCity.val())
        });
    $search.keyup(function() {
        clearTimeout(timer);
        timer = setTimeout(search, 1000);
    });
    $searchType.change(function() {
        if ($searchType.val() == "USER") {
            $searchCity.hide();
            $search.show();
            search($search.val());
        } else {
            $searchCity.show();
            $search.hide();
            search($searchCity.val());
        }
    });
});

function search(input) {
    if (checkForSameInput(input) && inputLength(input)) {
        $searchResult.html("");
        searchValue.request = input;
        searchValue.type = $searchType.val();
        $.ajax({
            url: "superMegaSearch",
            type: "POST",
            data: JSON.stringify(searchValue),
            dataType: "json",
            contentType: 'application/json; charset=utf-8',
            beforeSend: function () {
            },
            success: function (result) {
                $searchResult.html("");
                if (result.length == 0) {
                    $searchResult.append("<div class='col-md-offset-5'>"
                    + "Noting found!"
                    + "</div>");
                } else {
                    detectData(result);
                }
            }
        });
    }
}

function checkForSameInput(input) {
    if (input != bufInput) {
        bufInput = input;
        return true;
    } else {
        return false;
    }
}

function inputLength(input) {
    if (input.length == 0) {
        return false;
    } else if (input.length == 1 || input.length < 2){
        $searchResult.append("<div class='col-md-offset-5'>"
        + "Need more symbols"
        + "</div>");
    } else if (input.length >= 2) {
        $searchResult.append("<div class='ion-loading-c col-md-offset-6'/>");
        return true;
    }
}

function detectData(data) {
    if ($searchType.val() == "USER") {
        fillUserData(data)
    } else if ($searchType.val() == "ROUTE") {
        fillRouteData(data)
    } else if ($searchType.val() == "EVENT" ||
                $searchType.val() == "SIGHT" ||
                $searchType.val() == "HOSTING") {
        fillPlaceData(data)
    }
}

function fillPlaceData(data) {
    for (var i = 0; i < data.length; i++) {
        $searchResult.append("<div class='placeResult'>"
            + "<div class='col-sm-3'>" + "<a href = place?placeId=" + data[i].id + ">" + data[i].name + "</a>" + "</div>"
            + "<div class='col-sm-1'>" + "price" + "</div>"
            + "<div class='col-sm-1'>" + data[i].rating + "</div>"
            + "<div class='col-sm-4'>" + data[i].description + "</div>"
            + "<div class='col-sm-1'>" + data[i].sightseeingType + "</div>"
            + "<div class='col-sm-1'>" + "<button class='btn btn-primary showLocation'>" + "Map" + "</button>" + "</div>"
            + "<div class='col-sm-1'>" + "<button class='btn btn-primary bookPlace'>" + "Book" + "</button>" + "</div>"
        + "</div>");
        $("#searchResult").children().last().find(".bookPlace").data("placeId", data[i].id);
    }
    $btnShowLoc = $(".showLocation");
    $btnBookPlace = $(".bookPlace");
    $btnBookPlace.click(function(event) {
        $.ajax({
            url: "addBookedPlace",
            method: "POST",
            data: JSON.stringify($(event.target).data("placeId")),
            success: function() {
                $(event.target).attr("disabled", true);
            }
        })
    });
}

function fillUserData(data) {
    for (var i = 0; i < data.length; i++) {
        $searchResult.append("<div class='userResult'>" +
            "<a href = hoster?hosterId=" + data[i].id+'>'
            + data[i].name + "</a>"
        + "</div>");
    }
}

function fillRouteData(data) {
    for (var i = 0; i < data.length; i++) {
        $searchResult.append("<div class='routeResult'>" +
        '<a href =route?routeId='+ data[i].id + '>'
        + data[i].name + "</a>"
        + "</div>");
    }
}

function initSearchType(){
    $.ajax({
        url: "searchType",
        dataType: "json",
        success: function(searchTypes) {
            for (var i = 0; i < searchTypes.length; i++) {
                $searchType.append("<option value=" + searchTypes[i] + ">" + searchTypes[i] + "</option>");
            }
        }
    })
}