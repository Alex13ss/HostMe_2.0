var table;
var size;
var page;
var selectedTablePage = 1;
var eventsType;
var eventStatus;
var userRole = "MODERATOR";
var order = {
    by: "name",
    type: "ASC"
};

function getEventsParameters() {
    return eventsType + "?size=" + size + "&page=" + selectedTablePage + "&orderType=" + order.type + "&orderBy=" + order.by;
}

function eventsAjaxCallback() {
    setupPaging();
    calendarUpdate();
}

function checkRole() {
    if (userRole == "MODERATOR") {
        return true;
    } else {
        return false;
    }
}

function calendarUpdate() {
    if (eventsType != "all-events") {
        tableEvents = table.fnGetData();
        var calendarEvents = [];
        for (eventNum in tableEvents) {

            calendarEvents.push({
                title: tableEvents[eventNum].name,
                start: tableEvents[eventNum].startDate,
                end: tableEvents[eventNum].endDate,
                url: 'event?id=' + tableEvents[eventNum].id,
            });
        }

        $('#calendar').fullCalendar({
            allDayDefault: 'false',
            eventLimit: 6,
            events: calendarEvents,
        });
    }
}

function showEvents() {
    $("#calendar").fullCalendar('destroy');
    table.fnClearTable();
    table.fnReloadAjax(getEventsParameters(), eventsAjaxCallback);
}

function setupPaging() {
    $
        .ajax({
            type: 'GET',
            dataType: "json",
            data: {
                size: size,
                sender: eventsType
            },
            url: 'paging',
            success: function(numberOfPages) {
                $("#table_pages").empty();
                $("#table_pages").append(
                    '<li class="previousPage"><a>«</a></li>');
                for (number = 1; number <= numberOfPages; number++) {
                    $("#table_pages").append(
                        '<li class="pageNumberButton"><a>' + number + '</a></li>');
                }
                $("#table_pages").append(
                    '<li class="nextPage"><a>»</a></li>');

                $("#table_pages > li")
                    .click(
                        function(element) {
                            if ($(this).attr("class") == "previousPage" && selectedTablePage != 1) {
                                selectedTablePage--;
                                showEvents();
                            } else if ($(this).attr("class") == "nextPage" && selectedTablePage != numberOfPages) {
                                selectedTablePage++;
                                showEvents();
                            } else if ($(this).attr("class") == "pageNumberButton") {
                                selectedTablePage = $(this).text();
                                showEvents();
                            }
                        });

            }
        });
}

jQuery()
    .ready(
        function() {
            var tabCountry = [];
            $.getJSON('getAllCountries.json', function(data) {
                $.each(data, function(index, val) {
                    tabCountry[index] = val;
                });
            });
            $('#country')
                .change(
                    function(event) {
                        $country = $(this).val();
                        var $htmlOption = '<option value="0">Select city</option>';
                        for (var i = 0; i < tabCountry.length; i++) {
                            if ($country === tabCountry[i].country) {
                                for (var j = 0; j < tabCountry[i].city.length; j++) {
                                    var town = tabCountry[i].city[j].city;
                                    $htmlOption += '<option value="' + town + '">' + town + '</option>';
                                    console.log($htmlOption);
                                }
                            }
                        }
                        $('#city').html($htmlOption);
                    });
        });

$(document)
    .ready(
        function() {

            console.log($("#eventsTypesNav li:first-child").attr("id"));
            $("#eventsTypesNav li:first-child").addClass("active");
            eventsType = $("#eventsTypesNav > li.active").attr("id");

            size = $("#request_size").val();
            userRole = $('#UserRole').text();

            table = $("table.table-bordered")
                .dataTable({
                    "sAjaxDataProp": "",
                    "fnInitComplete": function(settings,
                        json) {
                        eventsAjaxCallback();
                    },
                    "fnRowCallback": function(nRow, aData,
                        iDisplayIndex,
                        iDisplayIndexFull) {
                        var ul = $("<ul/>", {
                            'class': "dropdown-menu"
                        });
                        var li = $("<li/>");
                        var div = $("<div/>", {
                            "class": "input-group-btn"
                        });

                        function html_element(aData, status) {
                            var parent_element = $("<a/>", {
                                    "href": "#"
                                })
                                .click(
                                    aData,
                                    function(e) {
                                        aData.status = status;
                                        e
                                            .preventDefault();
                                        $
                                            .ajax({
                                                url: 'event-update',
                                                dataType: 'json',
                                                beforeSend: function() {},
                                                contentType: "application/json",
                                                "type": "POST",
                                                data: JSON
                                                    .stringify(aData),
                                                success: function(
                                                    response) {
                                                    $('td:eq(6)', nRow).html(response.status.charAt(0).toUpperCase() +
                                                        status.substr(1).toLowerCase());
                                                }
                                            });
                                    });
                            return parent_element;
                        };

                        var button = $(
                            "<button/>", {
                                text: "Change status",
                                "type": "button",
                                "data-toggle": "dropdown",
                                "class": "btn btn-default dropdown-toggle",
                            });

                        function form_element(aData) {
                            var approve = html_element(
                                aData, "APPROVED");
                            var pending = html_element(
                                aData, "PENDING");
                            var refuse = html_element(
                                aData, "REFUSED");
                            approve.html("Approve");
                            pending.html("Pending");
                            refuse.html("Refuse");

                            var final_element = div
                                .append(button);
                            final_element
                                .append(ul
                                    .append(li
                                        .append(
                                            approve)
                                        .append(
                                            pending)
                                        .append(
                                            refuse)));
                            return final_element;
                        }

                        row = $('td:eq(7)', nRow).html(
                            form_element(aData));

                    },

                    "bProcessing": false,
                    "bServerSide": false,
                    "bPaginate": false,
                    "bFilter": false,
                    "bInfo": false,
                    "bSort": false,
                    "sAjaxSource": eventsType + "?size=" + size + "&page=" + selectedTablePage + "&orderType=" + order.type + "&orderBy=" + order.by,
                    "aoColumns": [{
                        "mData": function(data,
                            type, full) {
                            return '<a href=event?id=' + data.id + '>' + data.name + '</a>';
                        }

                    }, {
                        "mData": "startDate",
                        "mRender": function(data,
                            type, full) {
                            return new Date(data)
                                .toLocaleString()
                                .split(" ")[0];
                        }
                    }, {
                        "mData": function(data,
                            type, full) {
                            return data.city.city + ", " + data.address;
                        }
                    }, {
                        "mData": "priceCategory",
                        "mRender": function(data,
                            type, full) {
                            return data.priceCategory;
                        }
                    }, {
                        "mData": "website"
                    }, {
                        "mData": function(data,
                            type, full) {
                            return data.owner.firstName + " " + data.owner.lastName;
                        }
                    }, {
                        "bVisible": checkRole(),
                        "mData": function(data,
                            type, full) {
                            return data.status.charAt(0).toUpperCase() + data.status.substr(1).toLowerCase();
                        }
                    }, {
                        "bVisible": checkRole(),
                        "mData": "id"

                    }]
                });

            $("#request_size ").change(function() {
                size = $(this).val();
                selectedTablePage = 1;
                showEvents();
                

            });

            $("#eventsTypesNav > li").click(function() {
                eventsType = $(this).attr("id");
                selectedTablePage = 1;
                showEvents();

            });

            $("#eventsTableHeader > th").addClass(
                "custom_sorting_enabled");

            $("#eventsTableHeader > th").click(function() {
                currentOrderBy = order.by;
                order.by = $(this).attr("headers");
                if (currentOrderBy == order.by) {
                    if (order.type == "DESC") {
                        order.type = "ASC";
                    } else {
                        order.type = "DESC";
                    }
                } else {
                    order.type = "ASC";
                }
                showEvents();
            });

        });
jQuery()
    .ready(
        function() {
            var tabCountry = [];
            $
                .getJSON(
                    'getAllCountries',
                    function(data) {
                        $.each(data, function(index, val) {
                            tabCountry[index] = val;
                        });
                        var $countryOption = '<option value="0">Select country</option>';
                        for (var i = 0; i < tabCountry.length; i++) {
                            var country = tabCountry[i].country;
                            $countryOption += '<option value="' + country + '">' + country + '</option>';
                        };
                        $('#country').html($countryOption);
                    });
            $('#country')
                .change(
                    function(event) {
                        $country = $(this).val();
                        var $htmlOption = '<option value="0">Select city</option>';
                        for (var i = 0; i < tabCountry.length; i++) {
                            if ($country === tabCountry[i].country) {
                                for (var j = 0; j < tabCountry[i].city.length; j++) {
                                    var town = tabCountry[i].city[j].city;
                                    $htmlOption += '<option value="' + town + '">' + town + '</option>';
                                }
                            }
                        }
                        $('#city').html($htmlOption);
                    });
        });