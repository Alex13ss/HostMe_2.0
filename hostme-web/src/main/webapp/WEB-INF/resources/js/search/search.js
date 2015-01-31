var searchValue = {
    request: "",
    type: ""
};
var timer;
var $underSearch;
var $search;
var $searchType;
var $searchResult;
var $btnShowLoc;
var $btnBookPlace;
$(document).ready(function() {
    $search = $("#search");
    $underSearch = $("#searchReq");
    $searchResult = $("#searchResult");
    $searchType = $("#searchType");
    initSearchType();
    $search.keypress(function() {
        if (inputLength($search, $underSearch)) {
            clearTimeout(timer);
            timer = setTimeout(search,1000);
        }
    });
    $searchType.change(function() {
        if (inputLength($search, $underSearch)) {
            search();
        }
    });
});

function search() {
    searchValue.request = $search.val();
    searchValue.type = $searchType.val();
    $.ajax({
        url: "superMegaSearch",
        type: "POST",
        data: JSON.stringify(searchValue),
        dataType: "json",
        contentType : 'application/json; charset=utf-8',
        beforeSend: function() {
        },
        success: function(result) {
            $underSearch.html("");
            if (result.length == 0) {
                $underSearch.html("Nothing found!");
            } else {
                detectData(result);
            }
        }
    });
}

function detectData(data) {
    if ($searchType.val() == "USER") {
        fillUserData(data)
    } else if ($searchType.val() == "ROUTE") {
        fillRouteData(data)
    } else if ($searchType.val() == "EVENT" ||
                $searchType.val() == "SIGHT") {
        fillPlaceData(data)
    } else {
        for (var i = 0; i < data.length; i++) {
            $underSearch.append("<div>" +
            '<a href = ' + data[i].link + '>'
            + data[i].name + "</a>"
            + "</div>");
        }
    }
}

function fillPlaceData(data) {
    for (var i = 0; i < data.length; i++) {
        $searchResult.append("<div class='placeResult'>"
            + "<div class='col-lg-3'>" + "<a href = place?placeId=" + data[i].id + ">" + data[i].name + "</a>" + "</div>"
            + "<div class='col-lg-1'>" + "price" + "</div>"
            + "<div class='col-lg-1'>" + data[i].rating + "</div>"
            + "<div class='col-lg-4'>" + data[i].description + "</div>"
            + "<div class='col-lg-1'>" + data[i].sightseeingType + "</div>"
            + "<div class='col-lg-1'>" + "<button class='btn btn-primary showLocation'>" + "Map" + "</button>" + "</div>"
            + "<div class='col-lg-1'>" + "<button class='btn btn-primary bookPlace'>" + "Book" + "</button>" + "</div>"
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

function inputLength(input, $underSearch) {
    if (input.val().length < 2){
        $underSearch.html("Need more symbols");
    } else {
        $underSearch.html("Loading");
        return true;
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