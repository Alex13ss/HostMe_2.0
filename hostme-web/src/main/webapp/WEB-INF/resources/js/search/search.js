var searchValue = {
    request: "",
    type: ""
};
var timer;
var $underSearch;
var $search;
var $searchType;
var $resultName;
var $test;
$(document).ready(function() {
    $search = $("#search");
    $underSearch = $("#searchReq");
    $resultName = $("#searchResult > #name");
    $test = $("#searchResult > #secondName");
    $searchType= $("#searchType");
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
    })
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
    } else {
        for (var i = 0; i < data.length; i++) {
            $underSearch.append("<div>" +
            '<a href = ' + data[i].link + '>'
            + data[i].name + "</a>"
            + "</div>");
        }
    }
}

function fillUserData(data) {
    for (var i = 0; i < data.length; i++) {
        $underSearch.append("<div>" +
        '<a href = hoster?hosterId=' + data[i].id+'>'
        + data[i].name + "</a>"
        + "</div>");
    }
}

function fillRouteData(data) {
    for (var i = 0; i < data.length; i++) {
        $underSearch.append("<div>" +
        '<a href = ' + data[i].link + '>'
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
        url: "/searchType",
        dataType: "json",
        success: function(searchTypes) {
            for (var i = 0; i < searchTypes.length; i++) {
                $searchType.append("<option value=" + searchTypes[i] + ">" + searchTypes[i] + "</option>");
            }
        }
    })
}