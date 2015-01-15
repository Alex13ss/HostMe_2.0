var directionsDisplay = new google.maps.DirectionsRenderer();
var directionsService = new google.maps.DirectionsService();
var map;

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