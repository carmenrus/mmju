<%@page import="java.util.ArrayList"%>
<%@page import="com.mmju.jsp.ep2.Car"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="/theme/episode.css" type="text/css" />
<title>Show List of Car</title>
</head>
<body>
	<div style="width: 100%; text-align: right">
		<a href="javascript:history.back();">ယခင် စာမျက်နှာသို့</a>
	</div>
	<h3>ကားစာရင်း</h3>
	<%
		List<Car> carList = (List<Car>) application
				.getAttribute("car-list");
		Car car = (Car)session.getAttribute("car");
		
		if(null == carList)
			carList = new ArrayList<Car>();
		
		if(null != car) {
			carList.add(car);
			application.setAttribute("car-list", carList);
			session.removeAttribute("car");
		}
		
		if (null == carList || carList.size() == 0) {
	%>
	<jsp:forward page="./ep2-11.jsp">
		<jsp:param value="ဖော်ပြစရာကား စရင်းမရှိပါ သဖြင့် ကားအသစ်များကို စာရင်းသွင်းပါ။" name="msg"/>
	</jsp:forward>
	<%
		} else {
	%>
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
			<%
				for (Car c : carList) {
			%>
			<tr>
				<td><%=c.getBrand()%></td>
				<td><%=c.getModel()%></td>
				<td><%=c.getYear()%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	</div>
	<%
		}
	%>
	<jsp:include page="./ep2-14.jsp">
		<jsp:param value="10" name="p"/>
	</jsp:include>
</body>
</html>