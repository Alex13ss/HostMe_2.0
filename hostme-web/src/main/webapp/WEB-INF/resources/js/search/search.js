var searchObj = {
    request: "",
    type: "",
    haveMoreData: false,
    dateFrom: "",
    dateTo: "",
    sightseeingType: ""
};
var priceCategories = [];
var sightType = [];
var timer;
var bufInput = "";
var bufType;
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
        setAdvOptDisplay();
        if ($searchOptions.is(":visible")) {
            setAdvancedOptions();
        }
        if ($searchType.val() == "USER" ||
            $searchType.val() == "GROUPS") {
            $searchCity.hide();
            $search.show();
            callSearchWithDelay($search.val());
        } else {
            $search.hide();
            $searchCity.show();
            callSearchWithDelay($searchCity.val());
        }
    });
    $showSearchOptions.click(function() {
        if ($searchOptions.is(":visible")) {
            $searchOptions.hide();
            searchObj.haveMoreData = false;
        } else {
            $searchOptions.show();
            searchObj.haveMoreData = true;
            setAdvancedOptions();
        }
    })
});

function setAdvOptDisplay() {
    if ($searchType.val() == "USER" ||
        $searchType.val() == "GROUPS" ||
        $searchType.val() == "ROUTE") {
        $showSearchOptions.hide();
    } else {
        $showSearchOptions.show();
    }

}

function setAdvancedOptions() {
    if ($searchType.val() == "EVENT") {
        $searchOptions.html("");
        $searchOptions.append("<div>"
        + "After: "
        + "<input id='pickDateFrom'/>"
        + " Before: "
        + "<input id='pickDateTo'/>"
        + "</div>");
        var $datePickFrom = $searchOptions.find("#pickDateFrom");
        var $datePickTo = $searchOptions.find("#pickDateTo");
        $datePickFrom.datepicker({
            minDate: new Date(),
            onSelect: function() {
                searchObj.dateFrom = $datePickFrom.datepicker("getDate").getTime();
            },
            onClose: function(selectedDate) {
                $datePickTo.datepicker("option", "minDate", selectedDate);
            }   
        });
        $datePickTo.datepicker({
            minDate: new Date(),
            onSelect: function() {
                searchObj.dateTo = $datePickTo.datepicker("getDate").getTime();
            },
            onClose: function(selectedDate) {
                $datePickFrom.datepicker("option", "maxDate", selectedDate);
            }
        });
    } else if ($searchType.val() == "SIGHT") {
        $searchOptions.html("");
        $searchOptions.append("<select id='sightType' class='btn btn-default dropdown-toggle'>");
        var $sightType = $searchOptions.find("#sightType");
        for (var i = 0; i < sightType.length; i++) {
            $sightType.append("<option value=" + sightType[i] + ">" + convertEnumName(sightType[i]) + "</option>");
        }
        searchObj.sightseeingType = $sightType.val();
        $sightType.change(function() {
            searchObj.sightseeingType = $sightType.val();
        });
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
    if ((isInputChanged(input) || isSearchTypeChanged())
            && inputLength(input)) {
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

function isInputChanged(input) {
    if (input != bufInput) {
        bufInput = input;
        return true;
    } else {
        return false;
    }
}

function isSearchTypeChanged() {
    if ($searchType.val() != bufType) {
        bufType = $searchType.val();
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
        $searchType.val() == "SIGHT") {
        fillPlaceData(data)
    } else if ($searchType.val() == "GROUPS") {
        fillGroupData(data);
    }
}

function fillPlaceData(data) {
    for (var i = 0; i < data.length; i++) {
        $searchResult.append("<div class='placeResult col-md-5'>");
        $searchResult.children().last().append("<img class='placeResultImg' src='"
            + data[i].imgLink + "'>");
        $searchResult.children().last().append("<div class='placeResultBox'>");
        $searchResult.children().last().find(".placeResultBox").append("<div class='col-md-9 name'>"
            + "<a href = place?placeId=" + data[i].id + ">"
                + data[i].name
            + "</a>" + "</div>");
        $searchResult.children().last().find(".placeResultBox").append("<div class='col-md-3'>"
                + "<button class='btn btn-primary btn-xs bookPlace'>"
            + "Book" + "</button>");
        if (data[i].address != null) {
            $searchResult.children().last().find(".placeResultBox").append("<div class='col-md-12'>"
            + data[i].address
            +"</div>");
        }
        if (data[i].pcStr != null) {
            $searchResult.children().last().find(".placeResultBox").append("<div class='col-md-3'>"
                + "<i class='fa fa-dollar'/> "
                + data[i].pcStr
            +"</div>");
        }
        if (data[i].rating != null) {
            $searchResult.children().last().find(".placeResultBox").append("<div class='col-md-2'>"
                + "<i class='fa fa-thumbs-o-up'/> "
                + data[i].rating
            + "</div>");
        }
        if (data[i].website != null) {
            $searchResult.children().last().find(".placeResultBox").append("<div><a href='http://"
                + data[i].website + "'>"
            + "<i class='fa fa-globe'/>" +"</div>");
        }
        $searchResult.children().last().append("</div>");
        $searchResult.children().last().append("</div>");
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
        $searchResult.append("<div class='groupResult'>" +
        "<a href = group?id=" + data[i].id+'>'
            + data[i].groupName + "</a>"
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
        $searchResult.append("<div class='routeResult'>");
        $searchResult.children().last().append('<a href =route?routeId='+ data[i].id + '>'
            + data[i].name + "</a>");
        $searchResult.append("</div>");
    }
}

function initSearchType(){
    $.ajax({
        url: "searchType",
        dataType: "json",
        success: function(searchTypes) {
            for (var i = 0; i < searchTypes.length; i++) {
                $searchType.append("<option value=" + searchTypes[i] + ">" + convertEnumName(searchTypes[i]) + "</option>");
            }
            bufType = $searchType.val();
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

function convertEnumName(name) {
    var result = name.charAt(0);
    result += name.substring(1).toLowerCase();
    return result;
}