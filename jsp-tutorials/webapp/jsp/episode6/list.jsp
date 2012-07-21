<%@page import="com.mmju.jsp.ep2.Car"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/functions' prefix='fn'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/cars.js"></script>
<c:import url="/jsp/header.jsp"></c:import>
</head>
<body>

	<c:if test="${fn:length(car_list) == 0}">
		<c:redirect url="/episode6/edit.em">
			<c:param name="error_message"
				value="There is No Car in the List! Please add the car."></c:param>
		</c:redirect>
	</c:if>

	<div style="width: 100%; text-align: right">
		<a href="javascript:history.back();">ယခင် စာမျက်နှာသို့</a>
	</div>
	<h3>ကားစာရင်း</h3>

	<div id="left">
	<form id="details" action="/episode5/details.ep4" method="post">
		<table class="lineTbl">
			<thead>
				<tr>
					<td>ကားကုမ္ပဏီ</td>
					<td>ကားအမျိုးအစား</td>
					<td>ထုတ်လုပ်သည့်နှစ်</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${car_list}" var="car">
					<tr class="car-row">
						<td class="brand"><c:out value="${car.brand }" /></td>
						<td class="model"><c:out value="${car.model}" /></td>
						<td class="year"><c:out value="${car.year }" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
	</div>
</body>
</html>