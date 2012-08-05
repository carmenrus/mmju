<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="/WEB-INF/tld/mytag.tld" prefix="mytag"%>
<title>Insert title here</title>
</head>
<body>
	<c:import url="/jsp/header.jsp"></c:import>
	<div style="width: 100%; text-align: right">
		<a href="javascript:history.back();">ယခင် စာမျက်နှာသို့</a>
	</div>
	<h3>7.2 Body သုံးသော Tag</h3>
	<div>
		<mytag:DateTag>Min Lwin</mytag:DateTag>
		<br />
	</div>
	<div>
		Tag ၏ ဘော်ဒီအပိုင်းအား အသုံးပြုသော Custom Tag တစ်ခု၏
		နမှုနာတစ်ခုဖြစ်ပါသည်။<br /> Tag#doStartTag
		လုပ်ဆောင်ချက်ပြီးဆုံးပြီးနောက် ရလဒ်သည် EVAL_BODY_INCLUDE ဖြစ်ပါက Body
		အပိုင်းအား ဆက်လက်ဖော်ပြပြီး၊ နောက်ပိုင်းတွင် Tag#doEndTag ကို
		ခေါ်ယူမည် ဖြစ်၏။<br />
	</div>
</body>
</html>