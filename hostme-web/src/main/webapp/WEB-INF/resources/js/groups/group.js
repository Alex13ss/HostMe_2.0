/**
 * @author Oleksandr Bandurka JavaScript for group page
 */

$(document).ready(
		function() {
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
								minlength : 3
							},
							groupDescription : {
								required : true,
								minlength : 5
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
