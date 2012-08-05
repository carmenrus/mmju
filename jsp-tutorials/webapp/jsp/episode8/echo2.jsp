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
	<h3>8.2 TagSupport</h3>
	<div>
		<ep8:echo2 message="Hello from TagSupport" times="4">Message</ep8:echo2>
	</div>
	<br />
	<div>
		TagSupport အား Extend လုပ်ထားသော Custom Tag တစ်ခု၏
		နမှုနာတစ်ခုဖြစ်ပါသည်။<br /> Tag နှင့် IterationTag အင်တာဖေစ်များအား
		Implement လုပ်ထားသော Tag Handler Class အား Custom Tag အဖြစ်
		အသုံးပြုနိုင်သော်လည်း Business Logic အပြင် အခြားသော
		လုပ်ဆောင်ချက်များကိုလည်း Override လုပ်ရန် လိုအပ်ခဲ့၏။ သို့ရာတွင်
		TagSupport ကလပ်စ်သည် အဆိုပါ အင်တာဖေစ်များအား implement လုပ်ထားပါသဖြင့်
		ရေးသားလိုသော လုပ်ဆောင်ချက်များကိုသာ Override လုပ်ရန်လိုအပ်ပါသည်။
	</div>
</body>
</html>