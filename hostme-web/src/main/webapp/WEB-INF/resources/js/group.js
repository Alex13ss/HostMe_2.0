/**
 * JavaScript for page with groups
 * Created by Oleksandr Bandurka
 */

$(document).ready(function() {
	$(".triggerRemove").click(function(e) {
		e.preventDefault();
		$("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
		$("#modalRemove").modal();
	});
});
