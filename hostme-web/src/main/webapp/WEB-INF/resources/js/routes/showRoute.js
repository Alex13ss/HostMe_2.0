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
    $('#likeRoute').click(function() {
        $.ajax({
            url: "likeRoute",
            type: "PUT",
            data: route_id,
            success: function (result) {
                alert(result);
            }
        });
    });
});
