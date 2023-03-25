<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import=" java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>員工審查</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<% request.setCharacterEncoding("utf-8"); %>
<sql:setDataSource dataSource="jdbc/bank"/>
<sql:query var="application">
select username,applicationStatus,account,password,bornDate,identityCard,phoneNumber,email,applicationDate,reason
from CustomerApplication
where identityCard ='${param.identityCard}'
</sql:query>

</head>
<body>
<div class="container border border-primary w-50 mt-5 py-5">
	<div class="offset-sm-2">
		
		<form method="post" action="<c:url value='/ChangeMemberApplication_controller'/>">
			<c:forEach var="applicationRow" items="${application.rows}">
				<p>姓名:    ${applicationRow.username}</p>
				<p>會員帳號:${applicationRow.account}</p>
				<p>會員密碼:${applicationRow.password}</p>
				<p>出生日期:${applicationRow.bornDate}</p>
				<p>身分證  :${applicationRow.identityCard}</p>
				<p>電話號碼:${applicationRow.phoneNumber}</p>
				<p>信箱    :${applicationRow.email}</p>
				<p>申請日期:${applicationRow.applicationDate}</p>
				<p>審核狀態:${applicationRow.applicationStatus}</p>
				<p>原因:${applicationRow.reason}</p>
			</c:forEach>
			
			<label for="applicationStatusPass">是否通過審核:<input type="radio" id="applicationStatusPass" name="applicationStatus"  value="審查通過">通過</label>
			<label for="applicationStatusNotPass"><input type="radio" id="applicationStatusNotPass" name="applicationStatus" value="審查未通過">未通過</label>
			
			<div>
				<label for ="reason">未通過審核原因</label>
			</div>
			<textarea cols="40" rows="5" id="reason" name="reason" value="${param.reason}"></textarea>
			
			<div>
				<input type="submit" value="送出">
			</div>
			<input type="hidden" name="identityCard" value="${param.identityCard}">
		</form>
		
	</div>
	
	<button class="btn btn-outline-info offset-sm-9 " type="button" name="returnHome" id="returnHome" onclick="location.href=`<c:url value='/Index.jsp'/>`">返回首頁</button>
	
</div>


</body>
</html>