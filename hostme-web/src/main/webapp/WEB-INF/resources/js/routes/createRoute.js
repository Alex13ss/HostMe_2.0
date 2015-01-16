$(document).ready(function() {
    $.getJSON("/createRoute-getPlaces",
        function (myData) {
            //TODO gen droppable elements
    });
    $( ".drag" ).draggable();
    $( "#droppablePlace" ).droppable({
        drop: function (event, ui) {
            $(this)
                .addClass("ui-state-highlight")
                .find("p")
                .html("Dropped!");
        }
    })
});