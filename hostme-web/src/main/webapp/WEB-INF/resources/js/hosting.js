function formDate(){
	var dateRange=$('#reservationtime').val().split(' - ');
	var beginDate=Date.parse(dateRange[0]);
	var endDate=Date.parse(dateRange[1]);
	$('#beginDate').val(beginDate);
	$('#endDate').val(endDate);
}
	
		$(function() {
			$('#reservationtime').daterangepicker({
				timePicker : true,
				timePickerIncrement : 30,
				format : 'MM/DD/YYYY h:mm A'
			});

			$(".textarea").wysihtml5();

		});