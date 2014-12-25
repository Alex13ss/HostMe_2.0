$(function () {
    $('#reservationtime').daterangepicker({
        timePicker: true,
        timePickerIncrement: 30,
        format: 'MM/DD/YYYY h:mm A'
    });

});

$(document).ready(function () {
    var page;
    var maxPage;
    $("#search").click(function () {
        if ($("#searchTable").is(":hidden")) {
            $("#searchTable").toggle();
        }
        if ($("#nextPage").is(":hidden")) {
            $("#nextPage").toggle();
        }
        if ($("#previousPage").is(":hidden")) {
            $("#previousPage").toggle();
        }
    });
    $("#tdHost").click(function () {
        window.location = "http://localhost:8080/hosting?hostingId=${host.hostingId}";
    });
    $('#myForm').submit(function (e) {
        page = 0;
        var country = $('#country').val();
        var region = $('#region').val();
        var count = $('#count').val();
        var pets = $('#pets').is(":checked");
        var family = $('#family').is(":checked");
        var smoking = $('#smoking').is(":checked");
        var children = $('#children').is(":checked");
        var gender = "UNSPECIFIED";
        var dateRange = $('#reservationtime').val().split(' - ');
        var beginDate = Date.parse(dateRange[0]);
        var endDate = Date.parse(dateRange[1]);
        if (isNaN(beginDate)) {
            beginDate = "";
            endDate = "";
        }
        if ($('#female').is(":checked")) {
            gender = "FEMALE";
        }
        if ($('#male').is(":checked")) {
            gender = "MALE";
        }
        var url = $('#myForm').attr('action') + "?country=" + country + "&region=" + region + "&maxNumberOfGuests="
            + count + "&pets=" + pets + "&family=" + family + "&smoking=" + smoking + "&children=" + children
            + "&gender=" + gender + "&page=" + page + "&beginDate=" + beginDate + "&endDate=" + endDate;
        $.ajax({url: url, success: function (result) {
        	
            $('#searchTable tr').each(function (i, row) {
                if (i != 0) {
                    $(row).remove();
                }
            });
            var json = JSON.parse(result);
            console.log(json);
            for (var i = 1; i < json.length; i++) {
                var obj = json[i];
                var urlHost = "hosting?hostingId=" + obj.hostingId;
                var urlUser = "hoster?hosterId=" + obj.userId;
                var row = "<TR>";
                var buttonClass = "btn btn-primary";
                var oncl = "sendRequest(" + obj.hostingId + ", this)";
                var buttonId = "a";
                row += "<TD>" + obj.country + "</TD>";
                row += "<TD>" + obj.region + "</TD>";
                row += "<TD>" + obj.city + "</TD>";
                row += "<TD>" + "<a href=" + urlHost + ">" + obj.address + "</A></TD>";
                row += "<TD>" + "<a href=" + urlUser + ">" +obj.firstName+" "+obj.lastName + "</A></TD>";
                row += "<TD>" + "<button id=\"" + buttonId + "\" class=\"" + buttonClass + "\" onclick=\"" + oncl + "\">Send Request</button>" + "</TD>";
                row += "</TR>"
                $('#searchTable').append(row);
            }
            var currentPage = page + 1;
            maxPage = json[0];
            document.getElementById("currentPage").innerHTML = currentPage + " page of " + maxPage;
        }});
        return false;
    });
    $('#myFormPageNext').submit(function (e) {
        if (page != maxPage - 1) {
            page = page + 1;
        }
        var country = $('#country').val();
        var region = $('#region').val();
        var count = $('#count').val();
        var pets = $('#pets').is(":checked");
        var family = $('#family').is(":checked");
        var smoking = $('#smoking').is(":checked");
        var children = $('#children').is(":checked");
        var gender = "UNSPECIFIED";
        var dateRange = $('#reservationtime').val().split(' - ');
        var beginDate = Date.parse(dateRange[0]);
        var endDate = Date.parse(dateRange[1]);
        if (isNaN(beginDate)) {
            beginDate = "";
            endDate = "";
        }
        if ($('#female').is(":checked")) {
            gender = "FEMALE";
        }
        if ($('#male').is(":checked")) {
            gender = "MALE";
        }
        var url = $('#myForm').attr('action') + "?country=" + country + "&region=" + region + "&maxNumberOfGuests="
            + count + "&pets=" + pets + "&family=" + family + "&smoking=" + smoking + "&children=" + children
            + "&gender=" + gender + "&page=" + page + "&beginDate=" + beginDate + "&endDate=" + endDate;
        $.ajax({url: url, success: function (result) {
            $('#searchTable tr').each(function (i, row) {
                if (i != 0) {
                    $(row).remove();
                }
            });
            var json = JSON.parse(result);
            for (var i = 1; i < json.length; i++) {
                var obj = json[i];
                var urlHost = "hosting?hostingId=" + obj.hostingId;
                var urlUser = "hoster?hosterId=" + obj.userId;
                var row = "<TR>";
                var buttonClass = "btn btn-primary";
                var oncl = "sendRequest(" + obj.hostingId + ", this)";
                var buttonId = "a";
                row += "<TD>" + obj.country + "</TD>";
                row += "<TD>" + obj.region + "</TD>";
                row += "<TD>" + obj.city + "</TD>";
                row += "<TD>" + "<a href=" + urlHost + ">" + obj.address + "</A></TD>";
                row += "<TD>" + "<a href=" + urlUser + ">" + obj.firstName+" "+obj.lastName + "</A></TD>";
                row += "<TD>" + "<button id=\"" + buttonId + "\" class=\"" + buttonClass + "\" onclick=\"" + oncl + "\">Send Request</button>" + "</TD>";
                row += "</TR>"
                $('#searchTable').append(row);
            }
            var currentPage = page + 1;
            maxPage = json[0];
            document.getElementById("currentPage").innerHTML = currentPage + " page of " + maxPage;
        }});
        return false;
    });
    $('#myFormPagePrevious').submit(function (e) {
        if (page != 0) {
            page = page - 1;
        }
        var country = $('#country').val();
        var region = $('#region').val();
        var count = $('#count').val();
        var pets = $('#pets').is(":checked");
        var family = $('#family').is(":checked");
        var smoking = $('#smoking').is(":checked");
        var children = $('#children').is(":checked");
        var gender = "UNSPECIFIED";
        var dateRange = $('#reservationtime').val().split(' - ');
        var beginDate = Date.parse(dateRange[0]);
        var endDate = Date.parse(dateRange[1]);
        if (isNaN(beginDate)) {
            beginDate = "";
            endDate = "";
        }
        if ($('#female').is(":checked")) {
            gender = "FEMALE";
        }
        if ($('#male').is(":checked")) {
            gender = "MALE";
        }
        var url = $('#myForm').attr('action') + "?country=" + country + "&region=" + region + "&maxNumberOfGuests="
            + count + "&pets=" + pets + "&family=" + family + "&smoking=" + smoking + "&children=" + children
            + "&gender=" + gender + "&page=" + page + "&beginDate=" + beginDate + "&endDate=" + endDate;
        $.ajax({url: url, success: function (result) {
            $('#searchTable tr').each(function (i, row) {
                if (i != 0) {
                    $(row).remove();
                }
            });
            var json = JSON.parse(result);
            for (var i = 1; i < json.length; i++) {
                var obj = json[i];
                var urlHost = "hosting?hostingId=" + obj.hostingId;
                var urlUser = "hoster?hosterId=" + obj.userId;
                var row = "<TR>";
                var buttonClass = "btn btn-primary";
                var oncl = "sendRequest(" + obj.hostingId + ", this)";
                var buttonId = "a";
                row += "<TD>" + obj.country + "</TD>";
                row += "<TD>" + obj.region + "</TD>";
                row += "<TD>" + obj.city + "</TD>";
                row += "<TD>" + "<a href=" + urlHost + ">" + obj.address + "</A></TD>";
                row += "<TD>" + "<a href=" + urlUser + ">" + obj.firstName+" "+obj.lastName + "</A></TD>";
                row += "<TD>" + "<button id=\"" + buttonId + "\" class=\"" + buttonClass + "\" onclick=\"" + oncl + "\">Send Request</button>" + "</TD>";
                row += "</TR>"
                $('#searchTable').append(row);
            }
            var currentPage = page + 1;
            maxPage = json[0];
            document.getElementById("currentPage").innerHTML = currentPage + " page of " + maxPage;
        }});
        return false;
    });
});
