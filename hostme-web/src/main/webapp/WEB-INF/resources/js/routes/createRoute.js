$(document).ready(function() {
    $.getJSON("/createRoute-getPlaces",
        function (myData) {
            //TODO gen droppable elements
    });
    $( ".drag" ).draggable({
        revert: "invalid",
        snap: ".dropArea",
        snapMode: "inner"
    });
    $( ".dropArea" ).droppable({
        accept: ".drag",
        drop: function( event, ui ) {

            $( this )
                .addClass( "ui-state-highlight" )
                .find( "p" )
                .html( "Dropped!" );
        }
    });
});