var searchObj = {
    request: "",
    type: "",
    haveMoreData: false,
    date: "",
    sightseeingType: ""
};
var priceCategories = [];
var sightType = [];
var timer;
var bufInput = "";
var $searchBox;
var $search;
var $searchType;
var $searchCity;
var $searchResult;
var $btnBookPlace;
var $searchOptions;
var $showSearchOptions;
$(document).ready(function() {
    $searchBox = $("#searchBox");
    $searchOptions = $("#searchOptions");
    $searchOptions.hide();
    $showSearchOptions = $("#showSearchOptions");
    $search = $("#search");
    $search.hide();
    $searchResult = $("#searchResult");
    $searchType = $("#searchType");
    $searchCity = $("#searchCity");
    initSearchType();
    initPriceCat();
    initSightType();
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
            callSearchWithDelay($searchCity.val());
        }}
    ).keyup(function() {
            callSearchWithDelay($searchCity.val());
        });
    $search.keyup(function() {
        callSearchWithDelay($search.val());
    });
    $searchType.change(function() {
        if ($searchOptions.is(":visible")) {
            setAdvancedOptions();
        }
        if ($searchType.val() == "USER") {
            $searchCity.hide();
            $search.show();
            callSearchWithDelay($search.val());
        } else {
            $searchCity.show();
            $search.hide();
            callSearchWithDelay($searchCity.val());
        }
    });
    $showSearchOptions.click(function() {
        if ($searchOptions.is(":visible")) {
            $searchOptions.hide();
        } else {
            $searchOptions.show();
            setAdvancedOptions();
        }
    })
});

function setAdvancedOptions() {
    if ($searchType.val() == "EVENT") {
        $searchOptions.html("");
        $searchOptions.append("<div>"
        + "<input id='pickDate'/>"
        + "</div>");
        var $datePick = $searchOptions.find("#pickDate");
        $datePick.datepicker({
            minDate: new Date(),
            onSelect: function() {
                searchObj.date = $datePick.datepicker("getDate").getTime();
            }
        });
    } else if ($searchType.val() == "SIGHT") {
        $searchOptions.html("");
        $searchOptions.append("<select id='sightType'>");
        var $sightType = $searchOptions.find("#sightType");
        for (var i = 0; i < sightType.length; i++) {
            $sightType.append("<option value=" + sightType[i] + ">" + sightType[i] + "</option>");
        }
        searchObj.sightType = $sightType.val();
        $sightType.change(function() {
            searchObj.sightType = $sightType.val();
        });
    } else if ($searchType.val() == "HOSTING") {
        $searchOptions.html("");

    } else if ($searchType.val() == "ROUTE") {
        $searchOptions.html("");

    } else if ($searchType.val() == "GROUPS") {
        $searchOptions.html("");

    } else if ($searchType.val() == "USER") {
        $searchOptions.html("");

    }
}

function callSearchWithDelay(input) {
    clearTimeout(timer);
    timer = setTimeout(search, 500, input);
}

function search(input) {
    if (checkForSameInput(input) && inputLength(input)) {
        $searchResult.html("");
        searchObj.request = input;
        searchObj.type = $searchType.val();
        $.ajax({
            url: "superMegaSearch",
            type: "POST",
            data: JSON.stringify(searchObj),
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
                    detectSearchDataType(result);
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
    } else if (input.length <= 2){
        $searchResult.html("");
        $searchResult.append("<div class='col-md-offset-5'>"
        + "Need more symbols"
        + "</div>");
    } else if (input.length > 2) {
        $searchResult.append("<div class='ion-loading-c col-md-offset-6'/>");
        return true;
    }
}

function detectSearchDataType(data) {
    if ($searchType.val() == "USER") {
        fillUserData(data)
    } else if ($searchType.val() == "ROUTE") {
        fillRouteData(data)
    } else if ($searchType.val() == "EVENT" ||
        $searchType.val() == "SIGHT" ||
        $searchType.val() == "HOSTING") {
        fillPlaceData(data)
    } else if ($searchType.val() == "GROUPS") {
        fillGroupData(data);
    }
}

function fillPlaceData(data) {
    for (var i = 0; i < data.length; i++) {
        $searchResult.append("<div class='placeResult col-md-5'>"
        + "<div>" + "<a href = place?placeId=" + data[i].id + ">" + data[i].name + "</a>" + "</div>"
        + "<div>" + "price" + "</div>"
        + "<div>" + data[i].rating + "</div>"
        + "<div>" + data[i].description + "</div>"
        + "<div>" + "<button class='btn btn-primary bookPlace'>" + "Book" + "</button>" + "</div>"
        + "</div>");
        $("#searchResult").children().last().find(".bookPlace").data("placeId", data[i].id);
    }
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

function fillGroupData(data) {
    for (var i = 0; i < data.length; i++) {
        $searchResult.append("<div class='userResult'>" +
        "<a href = group?id=" + data[i].id+'>'
        + data[i].name + "</a>"
        + "</div>");
    }
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

function initPriceCat() {
    $.ajax({
        url: "getPriceCategories",
        dataType: "json",
        success: function(priceCat) {
            for (var i = 0; i < priceCat.length; i++) {
                priceCategories.push(priceCat[i].priceCategory);
            }
        }
    })
}

function initSightType() {
    $.ajax({
        url: "getAllTypes",
        dataType: "json",
        success: function(sT) {
            for (var i = 0; i < sT.length; i++) {
                sightType.push(sT[i]);
            }
        }
    })
}