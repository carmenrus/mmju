<%@page import="com.mmju.jsp.ep2.Car"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<script type="text/javascript"  src="/js/confirm.js"  charset="utf-8"></script>
<c:import url="/jsp/header.jsp"></c:import>
</head>
<body>
	<div style="width: 100%; text-align: right">
		<a href="javascript:history.back();">ယခင် စာမျက်နှာသို့</a>
	</div>
	<h3>စာရင်းသွင်းရန် ကား</h3>

	<div id="left">
		<form id="car_form" method="post">
		<input type="hidden" name="model" value="${new_car.model}" />
		<input type="hidden" name="brand" value="${new_car.brand}" />
		<input type="hidden" name="year" value="${new_car.year}" />
			<table>
				<tr>
					<td width="180px">ကားအမည်</td>
					<td id="ed_model" class="member"><c:out value="${new_car.model}" /></td>
				</tr>
				<tr>
					<td>ကားကုမ္ပဏီ</td>
					<td id="ed_brand" class="member"><c:out value="${new_car.brand}" /></td>
				</tr>
				<tr>
					<td>ထုတ်လုပ်သည့်နှစ်</td>
					<td id="ed_year" class="member"><c:out value="${new_car.year}" /></td>
				</tr>
				<tr>
					<td colspan="2">
					<c:choose>
							<c:when test="${ref_key eq 'edit'}">
								<input type="button" onclick="javascript:history.back()" value="ပြန်ပြင်မည်" />
								<input type="button"  class="action" name ="/episode5/add.ep4" value="စာရင်းသွင်းမည်" />
							</c:when>
							<c:otherwise>
								<input type="button" class="action" name ="/episode5/delete.ep4"  value="စာရင်းဖျက်မည်" />
								<input type="button" class="edit" name ="/episode5/update.ep4" value="စာရင်းပြင်မည်" />
							</c:otherwise>
						</c:choose></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>