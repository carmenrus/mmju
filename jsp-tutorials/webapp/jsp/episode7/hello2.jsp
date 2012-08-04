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
<h3>7.3 Attribute သုံးသော Tag</h3>
<div>
<mytag:HelloTag2 name="Min Lwin"/>
<br/>
<br/>
</div>
<div>
Custom Tag အတွင်းမှ Attribute အား ခေါ်ယူ အသုံးပြုနိုင်ပုံကို ဖော်ပြထားသော နမှုနာတစ်ခုဖြစ်ပါသည်။<br/>
TLD ဖိုင်တွင် attribute tag အား ဖြည့်စွက်ရေးသားထားပါက Custom Tag တွင် Attribute အား အသုံးပြုနိုင်မည် ဖြစ်သည်။<br/>
</div>
</body>
</html>