var searchValue = {
    request: "",
    type: ""
};
var timer;
var $underSearch;
var $search;
var $type;
$(document).ready(function() {
    $search = $("#search");
    $underSearch = $("#searchReq");
    $type= $("#type");
    $search.keypress(function() {
        if (inputLength($search, $underSearch)) {
            clearTimeout(timer);
            timer = setTimeout(search,1000);
        }
    });
    $type.change(function() {
        if (inputLength($search, $underSearch)) {
            search();
        }
    })
});

function search() {
    searchValue.request = $search.val();
    searchValue.type = $type.val();
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
                fillData(result);
            }
        }
    });
}

function fillData(data) {
    for (var i = 0; i < data.length; i++) {
        $underSearch.append("<div class='dragPlace'>" +
        '<a href = ' + data[i].link + '>'
        + data[i].name + "</a>"
        + "</div>")
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