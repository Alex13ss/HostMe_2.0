var searchValue = {
    request: "",
    type: ""
};
$(document).ready(function() {
    var $search = $("#search");
    var $underSearch = $("#searchReq");
    $search.keypress(function() {
        if (inputLength($search, $underSearch)) {
            searchValue.request = $search.val();
            $.ajax({
                url: "superMegaSearch",
                type: "POST",
                data: JSON.stringify(searchValue),
                dataType: "json",
                contentType : 'application/json; charset=utf-8',
                beforeSend: function() {

                },
                success: function(result) {

                }
            });
        }
    });
});

function inputLength(input, $underSearch) {
    if (input.val().length < 3){
        $underSearch.html("need more symbols");
    } else {
        $underSearch.html("");
        return true;
    }
}