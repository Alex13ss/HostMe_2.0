$(document).ready(function() {
    $.getJSON("/createRoute-getPlaces",
        function (myData) {
            var select = document.getElementById("placeFrom");
            for (var i = 0; i < myData.length; i++) {
                var opt = myData[i].name;
                var el = document.createElement("option");
                el.textContent = opt;
                el.value = opt;
                select.appendChild(el);
            }
    });
});