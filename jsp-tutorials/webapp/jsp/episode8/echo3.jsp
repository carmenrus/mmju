<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="/WEB-INF/tld/episode8.tld" prefix="ep8"%>
<title>Insert title here</title>
</head>
<body>
	<c:import url="/jsp/header.jsp"></c:import>
	<div style="width: 100%; text-align: right">
		<a href="javascript:history.back();">ယခင် စာမျက်နှာသို့</a>
	</div>
	<h3>8.3 BodyTagSupport</h3>
	<div>
		<ep8:echo3 message="Hello from BodyTagSupport!" times="4">Message</ep8:echo3>
	</div>
	<br />
	<div>
		BodyTagSupport အား Extend လုပ်ထားသော Custom Tag တစ်ခု၏
		နမှုနာတစ်ခုဖြစ်ပါသည်။<br /> BodyTagSupport သည် Bodyပိုင်းအား
		ဖော်ပြနိုင်သော BodyTag အင်တာဖေစ်အား Implement လုပ်ထားသော Custom Tag
		တစ်မျိုးဖြစ်ပါသည်။
	</div>
</body>
</html>