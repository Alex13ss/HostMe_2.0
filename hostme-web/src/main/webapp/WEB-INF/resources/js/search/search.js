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
            for (var i = 0; i < result.length; i++) {
                $underSearch.append("<div>" + result[i].name + "</div>")
            }
        }
    });
}

function inputLength(input, $underSearch) {
    if (input.val().length < 3){
        $underSearch.html("Need more symbols");
    } else {
        $underSearch.html("Loading");
        return true;
    }
}