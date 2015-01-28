$(document).ready(function () {
   addOrigin = $("#originAddress").text();
   addDestination = $("#destinationAddress").text();
   var addresses = $(".waypointAddress");
   for (var i = 0; i < addresses.length; i++) {
      waypts.push({
         location: $(addresses[i]).text()
      });
   }
   drawDestination();
});
