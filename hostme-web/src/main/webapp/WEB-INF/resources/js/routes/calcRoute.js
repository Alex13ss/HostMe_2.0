$(document).ready(function() {
$("#googleCalculator").click(function (directionsDisplay) {
    var start = "Lviv";
    var end = "Kiev";
    var request = {
        origin: start,
        destination: end,
        travelMode: google.maps.TravelMode.DRIVING
    };
    directionsService.route(request, function (result, status) {
        if (status == google.maps.DirectionsStatus.OK) {
            this.directionsDisplay.setDirections(result);
        }
    });
})
});