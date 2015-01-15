jQuery(document).ready(function($) {
	$(".clickableRow").click(function() {
		window.document.location = $(this).attr("href");
	});
});

function toggleFrequencySelectBox() {
		$('#typeSelect').val('');
		$('#typeSelect').prop('disabled', true);
}
