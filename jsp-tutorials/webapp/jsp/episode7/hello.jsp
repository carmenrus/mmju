<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ taglib uri="/WEB-INF/tld/mytag.tld"  prefix="mytag"%>
<title>Insert title here</title>
</head>
<body>
<div style="width: 100%; text-align: right">
    <a href="javascript:history.back();">ယခင် စာမျက်နှာသို့</a>
</div>
<h3>7.1 Body မသုံးသော Tag</h3>
<div>
<mytag:HelloTag/></div>
<div>
Tag ၏ ဘော်ဒီအပိုင်းအား အသုံးမပြုသော Custom Tag တစ်ခု၏ နမှုနာတစ်ခုဖြစ်ပါသည်။<br/>
Tag#doEndTag လုပ်ဆောင်ချက်အတွင်းမှ JSP Writer အား အသုံးပြု၍ စာသားများအား ရိုက်ထုတ်နေခြင်းသာဖြစ်၏။
</div>
</body>
</html>