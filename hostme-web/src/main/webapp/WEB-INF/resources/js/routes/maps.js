var directionsDisplay = new google.maps.DirectionsRenderer();
var directionsService = new google.maps.DirectionsService();
var map;
var addOrigin;
var addDestination;
$(document).ready(function() {
    function initialize() {
        var chicago = new google.maps.LatLng(41.850033, -87.6500523);
        var mapOptions = {
            center: chicago,
            zoom: 8
        };
        var map = new google.maps.Map(document.getElementById('map-canvas'),
            mapOptions);
        directionsDisplay.setMap(map);
    }
    google.maps.event.addDomListener(window, 'load', initialize);
});

function drawDestination() {
    var request = {
        origin: addOrigin,
        destination: addDestination,
        travelMode: google.maps.TravelMode.DRIVING
    };
    directionsService.route(request, function (result, status) {
        if (status == google.maps.DirectionsStatus.OK) {
            this.directionsDisplay.setDirections(result);
        }
    });
}