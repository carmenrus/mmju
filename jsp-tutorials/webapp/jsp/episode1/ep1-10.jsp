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
	<h3>Scripts Sample</h3>
	<%!String message = "မင်္ဂလာပါ JSP";%>

	<%
		for (int i = 0; i < 5; i++) {
	%>
	<%=i%>
	:
	<%=message%>
	<br />
	<%
		}
	%>
</body>
</html>