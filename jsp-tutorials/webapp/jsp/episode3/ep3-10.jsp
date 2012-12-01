<%@page import="com.mmju.jsp.ep2.Car"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/jsp/header.jsp"%>
</head>
<body>
	<div style="width: 100%; text-align: right">
		<a href="javascript:history.back();">ယခင် စာမျက်နှာသို့</a>
	</div>
	<h3>ကားစာရင်း</h3>

	<div id="left">
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
					<tr>
						<td><c:out value="${car.brand }" /></td>
						<td><c:out value="${car.model}" /></td>
						<td><c:out value="${car.year }" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<jsp:include page="./ep3-14.jsp">
		<jsp:param value="10" name="p" />
	</jsp:include>
</body>
</html>