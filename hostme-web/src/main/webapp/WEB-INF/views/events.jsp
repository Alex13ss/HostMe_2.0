<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html style="min-height: 682px;">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/AdminLTE.css">
<link rel="stylesheet" type="text/css" href="resources/css/video.css">

<script src="resources/js/jquery.dataTables.js" type="text/javascript"></script>
<script src="resources/js/fnAjaxReload.js" type="text/javascript"></script>
<script src="resources/js/dataTables.bootstrap.js"
	type="text/javascript"></script>
<title>Events</title>
</head>
<body class="wysihtml5-supported">

	<!--  	<video id="bgvid" autoplay loop poster="resources/images/nature.jpg">
		<source src="resources/video/mp4/nature1.mp4" type="video/mp4">
	</video> -->


	<section class="content-header">
		<h1>
			<spring:message code="label.events" />
		</h1>
	</section>

	<!-- Main content -->
	<section class="content">
		<div class="row">

			<div class="box">
				<div class="box-header">

					<h3 class="box-title">
						<i class="fa fa-plus-square"></i> Events management

					</h3>

					<div id="mystyle" style="margin-top: 10px;">

						<button onclick="refresh()" class="btn btn-default btn-sm">
							<i class="fa fa-fw fa-plus-square-o"></i>
							<spring:message code="label.addEvent" />
						</button>

						<button onclick="refresh()" class="btn btn-default btn-sm">
							<i class="fa fa-fw fa-refresh"></i>
							<spring:message code="label.Refresh" />
						</button>
					</div>


				</div>

				<!-- /.box-header -->
				<ul class="nav nav-tabs">

					<li id="request_from_me" class="active"
						onclick="initialize_request_from_me(this)"><a href="#"
						data-toggle="tab"><spring:message code="label.events" /></a></li>
					<li class="" onclick="initialize_request_to_me(this)"><a
						href="#" data-toggle="tab"><spring:message
								code="label.Myevents" /></a></li>
					<li class="" onclick="initialize_request_to_me(this)"><a
						href="#" data-toggle="tab"><spring:message
								code="label.SignedEvents" /></a></li>
				</ul>

				<div class="box-body table-responsive">
					<table id="request_table_obtain"
						class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>Title</th>
								<th>Starts</th>
								<th>Ends</th>
								<th>Location</th>
								<th>Price</th>
								<th>Website</th>
								<th>Organizer</th>
								<th>Status</th>
								<th>Attendees</th>
								<th>Comments</th>
								<th>Status</th>
								<th>Photos</th>
							</tr>
						</thead>
					</table>
				</div>

			</div>
			<!-- /.box-body -->
		</div>
		<!-- /.box -->

	</section>
	<!-- /.content -->

	<section class="content">
		<div class="row">

			<div class="box">
				<div class="box-header">

					<div class="box-body table-responsive">
						<table id="request_table_obtain"
							class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>1</th>
									<th>2</th>
									<th>3</th>
									<th>4</th>
									<th>5</th>
									<th>6</th>
									<th>7</th>
								</tr>
								<tr>
									<th>8</th>
									<th>9</th>
									<th>10</th>
									<th>11</th>
									<th>12</th>
									<th>13</th>
									<th>14</th>
								</tr>
								<tr>
									<th>15</th>
									<th>16</th>
									<th>17</th>
									<th>18</th>
									<th>19</th>
									<th>20</th>
									<th>21</th>
								</tr>
								<tr>
									<th>22</th>
									<th>23</th>
									<th>24</th>
									<th>25</th>
									<th>26</th>
									<th>27</th>
									<th>28</th>
								</tr>
								<tr>
									<th>29</th>
									<th>30</th>
									<th>31</th>
							</thead>
						</table>
					</div>



				</div>
			</div>
		</div>
	</section>

	<section class="content">


		<div class="row">
			
			<!-- /.col -->
			<div class="col-md-9">
				<div class="box box-primary">
					<div class="box-body no-padding">
						<!-- THE CALENDAR -->
						<div id="calendar" class="fc fc-ltr">
							<table class="fc-header" style="width: 100%">
								<tbody>
									<tr>
										<td class="fc-header-left"><span
											class="fc-button fc-button-prev fc-state-default fc-corner-left"
											unselectable="on"><span
												class="fc-icon fc-icon-left-single-arrow"></span></span><span
											class="fc-button fc-button-next fc-state-default fc-corner-right"
											unselectable="on"><span
												class="fc-icon fc-icon-right-single-arrow"></span></span><span
											class="fc-header-space"></span><span
											class="fc-button fc-button-today fc-state-default fc-corner-left fc-corner-right fc-state-disabled"
											unselectable="on">today</span></td>
										<td class="fc-header-center"><span
											class="fc-header-title"><h2>January 2015</h2></span></td>
										<td class="fc-header-right"><span
											class="fc-button fc-button-month fc-state-default fc-corner-left fc-state-active"
											unselectable="on">month</span><span
											class="fc-button fc-button-agendaWeek fc-state-default"
											unselectable="on">week</span><span
											class="fc-button fc-button-agendaDay fc-state-default fc-corner-right"
											unselectable="on">day</span></td>
									</tr>
								</tbody>
							</table>
							<div class="fc-content">
								<div class="fc-view fc-view-month fc-grid" unselectable="on">
									<div class="fc-event-container"
										style="position: absolute; z-index: 8; top: 0; left: 0">
										<div
											class="fc-event fc-event-hori fc-event-draggable fc-event-start fc-event-end"
											style="position: absolute; left: 431px; border-color: rgb(245, 105, 84); width: 102px; top: 44px; background-color: rgb(245, 105, 84);">
											<div class="fc-event-inner">
												<span class="fc-event-time">12a</span><span
													class="fc-event-title">All Day Event</span>
											</div>
										</div>
										<div
											class="fc-event fc-event-hori fc-event-draggable fc-event-start fc-event-end"
											style="position: absolute; left: 217px; border-color: rgb(243, 156, 18); width: 316px; top: 134px; background-color: rgb(243, 156, 18);">
											<div class="fc-event-inner">
												<span class="fc-event-time">12a</span><span
													class="fc-event-title">Long Event</span>
											</div>
										</div>
										<div
											class="fc-event fc-event-hori fc-event-draggable fc-event-start fc-event-end"
											style="position: absolute; left: 2px; border-color: rgb(0, 115, 183); width: 103px; top: 223px; background-color: rgb(0, 115, 183);">
											<div class="fc-event-inner">
												<span class="fc-event-time">10:30a</span><span
													class="fc-event-title">Meeting</span>
											</div>
										</div>
										<div
											class="fc-event fc-event-hori fc-event-draggable fc-event-start fc-event-end"
											style="position: absolute; left: 2px; border-color: rgb(0, 192, 239); width: 103px; top: 243px; background-color: rgb(0, 192, 239);">
											<div class="fc-event-inner">
												<span class="fc-event-time">12p</span><span
													class="fc-event-title">Lunch</span>
											</div>
										</div>
										<div
											class="fc-event fc-event-hori fc-event-draggable fc-event-start fc-event-end"
											style="position: absolute; left: 110px; border-color: rgb(0, 166, 90); width: 102px; top: 223px; background-color: rgb(0, 166, 90);">
											<div class="fc-event-inner">
												<span class="fc-event-time">7p</span><span
													class="fc-event-title">Birthday Party</span>
											</div>
										</div>
										<a href="http://google.com/"
											class="fc-event fc-event-hori fc-event-draggable fc-event-start fc-event-end"
											style="position: absolute; left: 324px; border-color: rgb(60, 141, 188); width: 102px; top: 401px; background-color: rgb(60, 141, 188);"><div
												class="fc-event-inner">
												<span class="fc-event-time">12a</span><span
													class="fc-event-title">Click for Google</span>
											</div></a>
									</div>
									<table class="fc-border-separate" style="width: 100%"
										cellspacing="0">
										<thead>
											<tr class="fc-first fc-last">
												<th class="fc-day-header fc-sun fc-widget-header fc-first"
													style="width: 107px;">Sun</th>
												<th class="fc-day-header fc-mon fc-widget-header"
													style="width: 107px;">Mon</th>
												<th class="fc-day-header fc-tue fc-widget-header"
													style="width: 107px;">Tue</th>
												<th class="fc-day-header fc-wed fc-widget-header"
													style="width: 107px;">Wed</th>
												<th class="fc-day-header fc-thu fc-widget-header"
													style="width: 107px;">Thu</th>
												<th class="fc-day-header fc-fri fc-widget-header"
													style="width: 107px;">Fri</th>
												<th class="fc-day-header fc-sat fc-widget-header fc-last">Sat</th>
											</tr>
										</thead>
										<tbody>
											<tr class="fc-week fc-first">
												<td
													class="fc-day fc-sun fc-widget-content fc-other-month fc-past fc-first"
													data-date="2014-12-28"><div style="min-height: 89px;">
														<div class="fc-day-number">28</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 20px;">&nbsp;</div>
														</div>
													</div></td>
												<td
													class="fc-day fc-mon fc-widget-content fc-other-month fc-past"
													data-date="2014-12-29"><div>
														<div class="fc-day-number">29</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 20px;">&nbsp;</div>
														</div>
													</div></td>
												<td
													class="fc-day fc-tue fc-widget-content fc-other-month fc-past"
													data-date="2014-12-30"><div>
														<div class="fc-day-number">30</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 20px;">&nbsp;</div>
														</div>
													</div></td>
												<td
													class="fc-day fc-wed fc-widget-content fc-other-month fc-past"
													data-date="2014-12-31"><div>
														<div class="fc-day-number">31</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 20px;">&nbsp;</div>
														</div>
													</div></td>
												<td class="fc-day fc-thu fc-widget-content fc-past"
													data-date="2015-01-01"><div>
														<div class="fc-day-number">1</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 20px;">&nbsp;</div>
														</div>
													</div></td>
												<td class="fc-day fc-fri fc-widget-content fc-past"
													data-date="2015-01-02"><div>
														<div class="fc-day-number">2</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 20px;">&nbsp;</div>
														</div>
													</div></td>
												<td class="fc-day fc-sat fc-widget-content fc-past fc-last"
													data-date="2015-01-03"><div>
														<div class="fc-day-number">3</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 20px;">&nbsp;</div>
														</div>
													</div></td>
											</tr>
											<tr class="fc-week">
												<td class="fc-day fc-sun fc-widget-content fc-past fc-first"
													data-date="2015-01-04"><div style="min-height: 88px;">
														<div class="fc-day-number">4</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 20px;">&nbsp;</div>
														</div>
													</div></td>
												<td class="fc-day fc-mon fc-widget-content fc-past"
													data-date="2015-01-05"><div>
														<div class="fc-day-number">5</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 20px;">&nbsp;</div>
														</div>
													</div></td>
												<td class="fc-day fc-tue fc-widget-content fc-past"
													data-date="2015-01-06"><div>
														<div class="fc-day-number">6</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 20px;">&nbsp;</div>
														</div>
													</div></td>
												<td class="fc-day fc-wed fc-widget-content fc-past"
													data-date="2015-01-07"><div>
														<div class="fc-day-number">7</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 20px;">&nbsp;</div>
														</div>
													</div></td>
												<td class="fc-day fc-thu fc-widget-content fc-past"
													data-date="2015-01-08"><div>
														<div class="fc-day-number">8</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 20px;">&nbsp;</div>
														</div>
													</div></td>
												<td class="fc-day fc-fri fc-widget-content fc-past"
													data-date="2015-01-09"><div>
														<div class="fc-day-number">9</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 20px;">&nbsp;</div>
														</div>
													</div></td>
												<td class="fc-day fc-sat fc-widget-content fc-past fc-last"
													data-date="2015-01-10"><div>
														<div class="fc-day-number">10</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 20px;">&nbsp;</div>
														</div>
													</div></td>
											</tr>
											<tr class="fc-week">
												<td class="fc-day fc-sun fc-widget-content fc-past fc-first"
													data-date="2015-01-11"><div style="min-height: 88px;">
														<div class="fc-day-number">11</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 40px;">&nbsp;</div>
														</div>
													</div></td>
												<td
													class="fc-day fc-mon fc-widget-content fc-today fc-state-highlight"
													data-date="2015-01-12"><div>
														<div class="fc-day-number">12</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 40px;">&nbsp;</div>
														</div>
													</div></td>
												<td class="fc-day fc-tue fc-widget-content fc-future"
													data-date="2015-01-13"><div>
														<div class="fc-day-number">13</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 40px;">&nbsp;</div>
														</div>
													</div></td>
												<td class="fc-day fc-wed fc-widget-content fc-future"
													data-date="2015-01-14"><div>
														<div class="fc-day-number">14</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 40px;">&nbsp;</div>
														</div>
													</div></td>
												<td class="fc-day fc-thu fc-widget-content fc-future"
													data-date="2015-01-15"><div>
														<div class="fc-day-number">15</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 40px;">&nbsp;</div>
														</div>
													</div></td>
												<td class="fc-day fc-fri fc-widget-content fc-future"
													data-date="2015-01-16"><div>
														<div class="fc-day-number">16</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 40px;">&nbsp;</div>
														</div>
													</div></td>
												<td
													class="fc-day fc-sat fc-widget-content fc-future fc-last"
													data-date="2015-01-17"><div>
														<div class="fc-day-number">17</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 40px;">&nbsp;</div>
														</div>
													</div></td>
											</tr>
											<tr class="fc-week">
												<td
													class="fc-day fc-sun fc-widget-content fc-future fc-first"
													data-date="2015-01-18"><div style="min-height: 88px;">
														<div class="fc-day-number">18</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 0px;">&nbsp;</div>
														</div>
													</div></td>
												<td class="fc-day fc-mon fc-widget-content fc-future"
													data-date="2015-01-19"><div>
														<div class="fc-day-number">19</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 0px;">&nbsp;</div>
														</div>
													</div></td>
												<td class="fc-day fc-tue fc-widget-content fc-future"
													data-date="2015-01-20"><div>
														<div class="fc-day-number">20</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 0px;">&nbsp;</div>
														</div>
													</div></td>
												<td class="fc-day fc-wed fc-widget-content fc-future"
													data-date="2015-01-21"><div>
														<div class="fc-day-number">21</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 0px;">&nbsp;</div>
														</div>
													</div></td>
												<td class="fc-day fc-thu fc-widget-content fc-future"
													data-date="2015-01-22"><div>
														<div class="fc-day-number">22</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 0px;">&nbsp;</div>
														</div>
													</div></td>
												<td class="fc-day fc-fri fc-widget-content fc-future"
													data-date="2015-01-23"><div>
														<div class="fc-day-number">23</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 0px;">&nbsp;</div>
														</div>
													</div></td>
												<td
													class="fc-day fc-sat fc-widget-content fc-future fc-last"
													data-date="2015-01-24"><div>
														<div class="fc-day-number">24</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 0px;">&nbsp;</div>
														</div>
													</div></td>
											</tr>
											<tr class="fc-week">
												<td
													class="fc-day fc-sun fc-widget-content fc-future fc-first"
													data-date="2015-01-25"><div style="min-height: 88px;">
														<div class="fc-day-number">25</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 37px;">&nbsp;</div>
														</div>
													</div></td>
												<td class="fc-day fc-mon fc-widget-content fc-future"
													data-date="2015-01-26"><div>
														<div class="fc-day-number">26</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 37px;">&nbsp;</div>
														</div>
													</div></td>
												<td class="fc-day fc-tue fc-widget-content fc-future"
													data-date="2015-01-27"><div>
														<div class="fc-day-number">27</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 37px;">&nbsp;</div>
														</div>
													</div></td>
												<td class="fc-day fc-wed fc-widget-content fc-future"
													data-date="2015-01-28"><div>
														<div class="fc-day-number">28</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 37px;">&nbsp;</div>
														</div>
													</div></td>
												<td class="fc-day fc-thu fc-widget-content fc-future"
													data-date="2015-01-29"><div>
														<div class="fc-day-number">29</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 37px;">&nbsp;</div>
														</div>
													</div></td>
												<td class="fc-day fc-fri fc-widget-content fc-future"
													data-date="2015-01-30"><div>
														<div class="fc-day-number">30</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 37px;">&nbsp;</div>
														</div>
													</div></td>
												<td
													class="fc-day fc-sat fc-widget-content fc-future fc-last"
													data-date="2015-01-31"><div>
														<div class="fc-day-number">31</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 37px;">&nbsp;</div>
														</div>
													</div></td>
											</tr>
											<tr class="fc-week fc-last">
												<td
													class="fc-day fc-sun fc-widget-content fc-other-month fc-future fc-first"
													data-date="2015-02-01"><div style="min-height: 88px;">
														<div class="fc-day-number">1</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 0px;">&nbsp;</div>
														</div>
													</div></td>
												<td
													class="fc-day fc-mon fc-widget-content fc-other-month fc-future"
													data-date="2015-02-02"><div>
														<div class="fc-day-number">2</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 0px;">&nbsp;</div>
														</div>
													</div></td>
												<td
													class="fc-day fc-tue fc-widget-content fc-other-month fc-future"
													data-date="2015-02-03"><div>
														<div class="fc-day-number">3</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 0px;">&nbsp;</div>
														</div>
													</div></td>
												<td
													class="fc-day fc-wed fc-widget-content fc-other-month fc-future"
													data-date="2015-02-04"><div>
														<div class="fc-day-number">4</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 0px;">&nbsp;</div>
														</div>
													</div></td>
												<td
													class="fc-day fc-thu fc-widget-content fc-other-month fc-future"
													data-date="2015-02-05"><div>
														<div class="fc-day-number">5</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 0px;">&nbsp;</div>
														</div>
													</div></td>
												<td
													class="fc-day fc-fri fc-widget-content fc-other-month fc-future"
													data-date="2015-02-06"><div>
														<div class="fc-day-number">6</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 0px;">&nbsp;</div>
														</div>
													</div></td>
												<td
													class="fc-day fc-sat fc-widget-content fc-other-month fc-future fc-last"
													data-date="2015-02-07"><div>
														<div class="fc-day-number">7</div>
														<div class="fc-day-content">
															<div style="position: relative; height: 0px;">&nbsp;</div>
														</div>
													</div></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					<!-- /.box-body -->
				</div>
				<!-- /. box -->
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->


	</section>


</body>
</html>