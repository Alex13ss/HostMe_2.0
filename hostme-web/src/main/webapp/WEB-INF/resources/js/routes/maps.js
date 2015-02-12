var directionsDisplay = new google.maps.DirectionsRenderer();
var directionsService = new google.maps.DirectionsService();
var map;
var addOrigin;
var addDestination;
var waypts = [];
var distance = "";
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
        waypoints:waypts,
        travelMode: google.maps.TravelMode.DRIVING
    };
    directionsService.route(request, function (result, status) {
        if (status == google.maps.DirectionsStatus.OK) {
            this.directionsDisplay.setDirections(result);
            var route = result.routes[0];
            for(var i = 0; i < route.legs.length; i++) {
                distance += route.legs[i].distance.value;
            }
        }
    });
}