<%@page import="com.mmju.jsp.ep2.Car"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="/theme/episode.css" type="text/css" />
<title>Script Sample</title>
</head>
<body>
	<div style="width: 100%; text-align: right">
		<a href="javascript:history.back();">ယခင် စာမျက်နှာသို့</a>
	</div>
	<h3>စာရင်းသွင်းရန် ကား</h3>

	<jsp:useBean id="car" class="com.mmju.jsp.ep2.Car" scope="session">
		<jsp:setProperty name='car' property='*' />
	</jsp:useBean>

	<div id="left">
		<form action="./ep2-10.jsp" method="post">
			<table>
				<tr>
					<td width="180px">ကားအမည်</td>
					<td><jsp:getProperty property="model" name="car" /></td>
				</tr>
				<tr>
					<td>ကားကုမ္ပဏီ</td>
					<td><jsp:getProperty property="brand" name="car" /></td>
				</tr>
				<tr>
					<td>ထုတ်လုပ်သည့်နှစ်</td>
					<td><jsp:getProperty property="year" name="car" /></td>
				</tr>
				<tr>
					<td colspan="2"><input onclick="javascript:history.back();"
						type="button" value="ပြန်ပြင်မည်" /> <input type="submit"
						value="စာရင်းသွင်းမည်" /></td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="./ep2-14.jsp">
		<jsp:param name="p" value="12" />
	</jsp:include>
</body>
</html>