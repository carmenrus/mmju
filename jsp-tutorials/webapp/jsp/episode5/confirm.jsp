<%@page import="com.mmju.jsp.ep2.Car"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:import url="/jsp/header.jsp"></c:import>
</head>
<body>
	<div style="width: 100%; text-align: right">
		<a href="javascript:history.back();">ယခင် စာမျက်နှာသို့</a>
	</div>
	<h3>စာရင်းသွင်းရန် ကား</h3>

	<div id="left">
		<form action="/episode5/add.ep4" method="post">
			<table>
				<tr>
					<td width="180px">ကားအမည်</td>
					<td><c:out value="${sessionScope.new_car.model}" /></td>
				</tr>
				<tr>
					<td>ကားကုမ္ပဏီ</td>
					<td><c:out value="${sessionScope.new_car.brand}" /></td>
				</tr>
				<tr>
					<td>ထုတ်လုပ်သည့်နှစ်</td>
					<td><c:out value="${sessionScope.new_car.year}" /></td>
				</tr>
				<tr>
					<td colspan="2"><input onclick="javascript:history.back();"
						type="button" value="ပြန်ပြင်မည်" /> <input type="submit"
						value="စာရင်းသွင်းမည်" /></td>
				</tr>
			</table>
		</form>
	</div>
	<c:import url="../episode3/ep3-14.jsp">
		<c:param name="p" value="10"></c:param>
	</c:import>
</body>
</html>