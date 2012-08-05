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
	<%
		out.println("<h3>ကားအသစ် စာရင်းသွင်းရန်</h3>");
		String msg = request.getParameter("msg");
		if (null != msg) {
			out.println("<p>" + msg + "</p>");
		}
	%>
	<div id="left">
		<form action="./ep2-12.jsp" method="post">
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
	<jsp:include page="./ep2-14.jsp">
		<jsp:param value="11" name="p" />
	</jsp:include>
</body>
</html>