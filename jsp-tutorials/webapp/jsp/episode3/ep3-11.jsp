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
	<h3>ကားအသစ် စာရင်းသွင်းရန်</h3>
	<div id="left">
		<c:out value="${error_message}" />
		<form action="/ep3/confirm" method="post">
			<table>
				<tr>
					<td width="180px">ကားအမည်</td>
					<td><input type="text" name="model"></input></td>
				</tr>
				<tr>
					<td>ကားကုမ္ပဏီ</td>
					<td><input type="text" name="brand"></input></td>
				</tr>
				<tr>
					<td>ထုတ်လုပ်သည့်နှစ်</td>
					<td><input type="text" name="year"></input></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="စာရင်းသွင်းမည်"></input></td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="./ep3-14.jsp">
		<jsp:param value="11" name="p" />
	</jsp:include>
</body>
</html>