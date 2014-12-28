<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="option"
    uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../WEB-INF/resources/js/request.js"></script>
<script type="text/javascript" src="../WEB-INF/resources/js/daterangepicker.js"></script>
<script type="text/javascript" src="../WEB-INF/resources/js/search.js"></script>
<script type="text/javascript" src="../WEB-INF/resources/js/countries.js"></script>
<script type="text/javascript" src="../WEB-INF/resources/js/jput.min.js"></script>
<title>Search</title>
<link rel="stylesheet" type="text/css"
    href="../WEB-INF/resources/css/daterangepicker-bs3.css">
</head>
<body>
    <section class="content-header">
    <h1><spring:message code="label.searchhosts" /></h1>
    </section>

    <section class="content">

    <div>
        <!-- general form elements -->
        <div class="box box-primary">

            <div class="box-body">

                <div class="row">
                    <div class="col-md-12">
                        <form id="myForm" method="get"
                            action="searchhosts">

                            <div class="col-md-8">
                                <div class="row">
                                    <h4>
                                        <spring:message
                                            code="label.selectTravelDates" />
                                        :
                                    </h4>
                                    <input name="timeRange" type="text"
                                        class="form-control pull-right"
                                        id="reservationtime" />
                                </div>
                                <div class="row"
                                    style="padding-top: 0.5em;">
                                    <div class="checkbox_cnt">
                                        <h4>
                                            <spring:message
                                                code="label.chooseRequirements" />
                                            :
                                        </h4>
                                        <div class="col-md-4">

                                            <div class="radio">
                                                <div
                                                    style="padding-bottom: 0.5em; font-weight: bold">
                                                    <spring:message
                                                        code="label.travellers" />
                                                    :
                                                </div>
                                                <input type="radio"
                                                    name="optionsRadios"
                                                    id="female"
                                                    value="FEMALE">
                                                <span
                                                    class="optionsList"><spring:message
                                                        code="label.onlyWomen" /></span>

                                            </div>
                                            <div class="radio">
                                                <input type="radio"
                                                    name="optionsRadios"
                                                    id="male"
                                                    value="MALE"><span
                                                    class="optionsList"><spring:message
                                                        code="label.onlyMen" /></span>
                                            </div>
                                            <div class="radio">
                                                <input type="radio"
                                                    name="optionsRadios"
                                                    id="optionsRadios3"
                                                    value="UNSPECIFIED"
                                                    checked="checked"><span
                                                    class="optionsList"><spring:message
                                                        code="label.both" /></span>
                                            </div>
                                        </div>
                                        <div class="checkboxes">
                                            <div class="col-md-6">
                                                <div class="checkbox">
                                                    <input
                                                        type="checkbox"
                                                        id="children">
                                                    <span
                                                        class="optionsList"><spring:message
                                                            code="label.children" /></span>
                                                </div>
                                                <div class="checkbox">
                                                    <input
                                                        type="checkbox"
                                                        id="family">
                                                    <span
                                                        class="optionsList"><spring:message
                                                            code="label.family" /></span>
                                                </div>
                                                <div class="checkbox">
                                                    <input
                                                        type="checkbox"
                                                        id="pets">
                                                    <span
                                                        class="optionsList"><spring:message
                                                            code="label.pets" /></span>
                                                </div>
                                                <div class="checkbox">
                                                    <input
                                                        type="checkbox"
                                                        id="smoking">
                                                    <span
                                                        class="optionsList"><spring:message
                                                            code="label.smoking" /></span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <div class="row">
                                    <div class="row">
                                        <div class="col-md-3"
                                            style="padding-right: 0em">
                                            <h4>
                                                <spring:message
                                                    code="label.selectCountry" />
                                                :
                                            </h4>
                                        </div>
                                        <div class="col-md-6"
                                            style="padding-left: 0em;">
                                            <div class="select">
                                                <select
                                                    class="form-control"
                                                    id="country"
                                                    style="margin-top: 0.5em; width: 12em; height: 1.8em"
                                                    name="country"
                                                    onclick="print_state('region',this.selectedIndex);"
                                                    onchange="print_state('region',this.selectedIndex);">
                                                    <option></option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="row">
                                        <div class="col-md-3"
                                            style="padding-right: 0em">
                                            <h4>
                                                <spring:message
                                                    code="label.selectRegion" />
                                                :
                                            </h4>
                                        </div>
                                        <select
                                            class="select form-control"
                                            id="region"
                                            style="margin-top: 0.5em; width: 12em; height: 1.8em">
                                            <option></option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="row">
                                        <div class="col-md-3"
                                            style="padding-right: 0em">
                                            <h4>
                                                <spring:message
                                                    code="label.numberPeople" />
                                                :
                                            </h4>
                                        </div>
                                        <select
                                            class="select form-control"
                                            id="count"
                                            style="margin-top: 0.5em; width: 12em; height: 1.8em">
                                            <option></option>
                                            <option>1</option>
                                            <option>2</option>
                                            <option>3</option>
                                            <option>4</option>
                                            <option>5</option>
                                            <option>6</option>
                                            <option>7</option>
                                            <option>8</option>
                                            <option>9</option>
                                            <option>10</option>
                                            <option>11</option>
                                            <option>12</option>
                                            <option>13</option>
                                            <option>14</option>
                                            <option>15</option>
                                            <option>16</option>
                                            <option>17</option>
                                            <option>18</option>
                                            <option>19</option>
                                            <option>20</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row"
                                    style="margin-top: 20px;">

                                    <spring:message
                                        code="label.searchHosting"
                                        var="searchHosting" />
                                    <div class="col-md-2"
                                        style="padding-left: 0em;">
                                        <input class="btn btn-primary"
                                            type="submit"
                                            value="${searchHosting}"
                                            id="search" />
                                    </div>
                                </div>
                            </div>
                            <div id="searchhosts"></div>
                        </form>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <table id="searchTable"
                            class="table table-bordered"
                            style="display: none; margin-top: 1.5em; margin-bottom: 0.5em;">
                            <tr>
                                <th><spring:message code="label.country" /></th>
                                <th><spring:message code="label.region" /></th>
                                <th><spring:message code="label.city" /></th>
                                <th><spring:message code="label.address" /></th>
                                <th><spring:message code="label.hoster" /></th>
                                <th><spring:message code="label.requests" /></th>
                            </tr>
                        </table>

                        <div class="pagination pagination-sm inline" id="pagination">
                            <form id="myFormPagePrevious" method="get"
                                action="searchhosts"
                                style="display: inline">
                                <input type="submit" id="previousPage"
                                    value="&laquo"
                                    style="display: none" />
                            </form>
                            <div id="currentPage"
                                style="display: inline"></div>
                            <form id="myFormPageNext" method="get"
                                action="searchhosts"
                                style="display: inline">
                                <input type="submit" id="nextPage"
                                    value="&raquo"
                                    style="display: none" />
                            </form>
                        </div>

                    </div>
                </div>

            </div>

        </div>
    </div>

    </section>
    <script src="../WEB-INF/resources/js/jquery-2.1.1.js"></script>
    <script src="../WEB-INF/resources/js/bootstrap.js"></script>
    <script type="text/javascript">
					$('#myModal').modal('show');
				</script>
    <script language="javascript">
					print_country("country");
				</script>
</body>
</html>
