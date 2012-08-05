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
	<h3>8.1 Iterator Tag</h3>
	<div>
		<ep8:echo message="Hello" times="4">Message : </ep8:echo>
	</div>
	<br />
	<div>
		IterationTag အား အသုံးပြုသော Custom Tag တစ်ခု၏ နမှုနာတစ်ခုဖြစ်ပါသည်။<br />
		message နှင့် times အား Attribute အနေဖြင့် ရယူပြီး၊ ရယူထားသော message
		အား times အကြိမ် ဖော်ပြစေပါသည်။<br /> Attribute အနေဖြင့် List များကို
		ရယူပြီး၊ List အတွင်းမှ အချက်အလက်များအား တစ်ခုချင်း ဖော်ပြရာတွင်
		အသုံးဝင်ပါသည်။<br />
	</div>
</body>
</html>