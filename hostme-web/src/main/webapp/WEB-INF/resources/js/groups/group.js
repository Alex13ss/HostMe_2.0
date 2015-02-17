/**
 * @author Oleksandr Bandurka JavaScript for group page
 */

function getUrlParameter(sParam) {
	var sPageURL = window.location.search.substring(1);
	var sURLVariables = sPageURL.split('&');
	for (var i = 0; i < sURLVariables.length; i++) {
		var sParameterName = sURLVariables[i].split('=');
		if (sParameterName[0] == sParam) {
			return sParameterName[1];
		}
	}
}

$(document).ready(
		function() {
			$('img').on('click', function() {
				var src = $(this).attr('src');
				var img = '<img src="' + src + '" class="img-responsive"/>';
				$('#imgModal').modal();
				$('#imgModal').on('shown.bs.modal', function() {
					$('#imgModal .modal-body').html(img);
				});
				$('#imgModal').on('hidden.bs.modal', function() {
					$('#imgModal .modal-body').html('');
				});
			});
			$("#groupStatusChanger > li > a").click(function() {
				$.ajax({
					url : 'group-status-update',
					dataType : 'json',
					contentType : "application/json",
					"type" : "POST",
					data : JSON.stringify({
						id : getUrlParameter('id'),
						status : $(this).attr("id")
					}),
				});
			});

			$(".triggerRemove").click(
					function(e) {
						e.preventDefault();
						$("#modalRemove .removeBtn").attr("href",
								$(this).attr("href"));
						$("#modalRemove").modal();
					});

			$(".groupForm").validate(
					{
						rules : {
							groupName : {
								required : true,
								minlength : 3,
								maxlength : 42
							},
							groupDescription : {
								required : true,
								minlength : 5,
								maxlength : 255
							}
						},
						highlight : function(element) {
							$(element).closest('.form-group').removeClass(
									'has-success').addClass('has-error');
						},
						unhighlight : function(element) {
							$(element).closest('.form-group').removeClass(
									'has-error').addClass('has-success');
						}
					});

		});
