$(document)
												.ready(
														function() {
															$('#reservation')
																	.daterangepicker(
																			null,
																			function(
																					start,
																					end) {
																				document
																						.getElementById("startDate").value = start
																						.format('YYYY-MM-DD HH:mm:ss');
																				document
																						.getElementById("endDate").value = end
																						.format('YYYY-MM-DD HH:mm:ss');
																			});
														});