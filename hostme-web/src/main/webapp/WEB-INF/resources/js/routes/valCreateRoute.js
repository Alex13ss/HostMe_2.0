$(document).ready(
    function() {
        jQuery.validator.setDefaults({
            errorPlacement : function(error, element) {
                if (element.parent().hasClass('input-prepend')
                    || element.parent().hasClass('input-append')) {
                    error.insertAfter(element.parent());
                } else {
                    error.insertAfter(element);
                }
            },
            errorElement : "small",
            highlight : function(element) {
                $(element).parent().addClass('has-error');
            },
            success : function(element) {
                $(element).closest('.control-group').removeClass('error');
                element.parents().eq(0).removeClass('has-error');
                element.parents().eq(0).addClass('has-success');
            }
        });
        $('#routeCreationForm').validate({
            rules : {
                name : {
                    minlength : 3,
                    required : true
                },
                description : {
                    minlength : 3,
                    required : true,
                    maxlength : 500
                }
            }
        });
    });

