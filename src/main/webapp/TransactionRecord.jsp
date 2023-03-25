<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
	
<!DOCTYPE html>
<html>
<head>
<%-- <link rel="icon" href="<c:url value='/favicon.ico' />"> --%>
<meta charset="UTF-8" />	
<title>交易紀錄</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<link href="stylesheets/jquery-ui/hot-sneaks/jquery-ui.css" rel="stylesheet">
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" />

<sql:setDataSource dataSource="jdbc/bank" /> 
<% request.setCharacterEncoding("utf-8"); %>

<c:choose>
	<c:when test="${param.timescope=='week'}">
		<sql:query var="result">
			SELECT [operationTime] 日期, [transactionAmount] 金額, [method] 方式, [total] 餘額
		  	FROM [dbo].[record]
			where [bankAccount] = ? and operationTime >= DateAdd(day, -7, GETDATE()) 
			order by 1 desc
			offset (?-1)*10 rows
			fetch next 10 rows only
			<sql:param value="${ sessionScope.su }"></sql:param>
			<sql:param value="${ param.page }"></sql:param>	
		</sql:query>
		
		<sql:query var="result1">
			select COUNT(*) as count from record 
			where [bankAccount] = ? and operationTime >= DateAdd(day, -7, GETDATE());		
			<sql:param value="${ sessionScope.su }"></sql:param>	
		</sql:query>
	</c:when>
	
	<c:when test="${param.timescope=='month1'}">
		<sql:query var="result">
			SELECT [operationTime] 日期, [transactionAmount] 金額, [method] 方式, [total] 餘額
		  	FROM [dbo].[record]
			where [bankAccount] = ? and operationTime >= DateAdd(month, -1, GETDATE()) 
			order by 1 desc
			offset (?-1)*10 rows
			fetch next 10 rows only
			<sql:param value="${ sessionScope.su }"></sql:param>
			<sql:param value="${ param.page }"></sql:param>	
		</sql:query>
		
		<sql:query var="result1">
			select COUNT(*) as count from record 
			where [bankAccount] = ? and operationTime >= DateAdd(month, -1, GETDATE());		
			<sql:param value="${ sessionScope.su }"></sql:param>	
		</sql:query>
	</c:when>
	
	<c:when test="${param.timescope=='month3'}">
		<sql:query var="result">
			SELECT [operationTime] 日期, [transactionAmount] 金額, [method] 方式, [total] 餘額
		  	FROM [dbo].[record]
			where [bankAccount] = ? and operationTime >= DateAdd(month, -3, GETDATE()) 
			order by 1 desc
			offset (?-1)*10 rows
			fetch next 10 rows only
			<sql:param value="${ sessionScope.su }"></sql:param>
			<sql:param value="${ param.page }"></sql:param>	
		</sql:query>
		
		<sql:query var="result1">
			select COUNT(*) as count from record 
			where [bankAccount] = ? and operationTime >= DateAdd(month, -3, GETDATE());		
			<sql:param value="${ sessionScope.su }"></sql:param>	
		</sql:query>
	</c:when>
	
	<c:when test="${param.timescope=='all'}">
		<sql:query var="result">
			SELECT [operationTime] 日期, [transactionAmount] 金額, [method] 方式, [total] 餘額
		  	FROM [dbo].[record]
			where [bankAccount] = ? 
			order by 1 desc
			offset (?-1)*10 rows
			fetch next 10 rows only
			<sql:param value="${ sessionScope.su }"></sql:param>
			<sql:param value="${ param.page }"></sql:param>	
		</sql:query>
		
		<sql:query var="result1">
			select COUNT(*) as count from record where [bankAccount] = ?;		
			<sql:param value="${ sessionScope.su }"></sql:param>	
		</sql:query>
	</c:when>
</c:choose>


<script>
$(function(){
	if(${param.timescope!=null}){		
		$("#${param.timescope}").attr("selected",true);

				
 		let totalrowcount = Number($("#totalrowcount").html());
	 	let totalpagecount = Math.ceil(totalrowcount/10);
			 	
	 	
	 	if(${param.page}==totalpagecount){
	 		$("#viewrow").html(totalrowcount%10);
	 	}else{
	 		$("#viewrow").html(10);
	 	}
	 	
	 	
		for(let i=1; i<=totalpagecount; i++){
	 		let str = "<a class='btn btn-primary' href='${pageContext.request.requestURL}?timescope="
	 			    + "${param.timescope}&page=" + i + "'>" + i + "</a>&nbsp;&nbsp;";
			$("#pages").append(str);
		}
	}
});
</script>

</head>
<body>
	<div class="container w-75 mt-5">
		<h4 class="text-center">交易紀錄查詢</h4>
		
		<div class="row">
			<div class="offset-sm-2 col-sm-8 my-5">
				<form method="get" action="${pageContext.request.requestURL}">
					<div class="row">						
						<select class="offset-sm-3 col-sm-4" name="timescope" >
							<option value="week" id="week">一週內</option>
							<option value="month1" id="month1">一個月內</option>
							<option value="month3" id="month3">三個月內</option>	
							<option value="all" id="all">所有交易</option>							
						</select>
						<input type="text" name="page" id="page1" value="1" style="display: none">
						
						<div class="col-sm-5">
							<button type="submit" class="btn btn-danger">查詢</button>
							<a class="btn btn-primary" href="<c:url value='/MemberOperation.jsp'/>">返回</a>
						</div>
					</div>
				</form>
			</div>
		</div>
		
		
		<div class="row mb-3">
			<c:forEach var="row" items="${result1.rows}">																	
					<span class="offset-sm-1 col-sm-5">共<span id="totalrowcount">${row.count}</span>筆，當頁顯示<span id="viewrow"></span>筆</span>										
			</c:forEach>
					
			<span id=pages class="offset-sm-3 col-sm-1 btn-group"></span>
		</div>
		
		
		<div class="row">
			<div class="offset-sm-1 col-sm-10">
			<table class="table table-striped table-bordered table-hover text-center" >
					<thead>
						<tr>
							<!-- <th>戶號</th><th>日期</th><th>金額</th><th>方式(存、提款)</th><th>餘額</th> -->							
							<c:forEach var="colName" items="${result.columnNames }"><%--result.columnNames資料型別：String[]--%>
								<th>${colName}</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>						
						<c:forEach var="row" items="${result.rows}"><%--result.rows資料型別：SortedMap[]--%>
							<tr >									
								<td style="width:30%">${String.format("%tY/%<tm/%<td &nbsp; %<tH:%<tM:%<tS", row.日期) }</td>	
								<td >$${String.valueOf(row.金額.intValue()).replaceAll("\\B(?=(?:\\d{3})+(?!\\d))", ",")}</td>
								<td><c:if test="${row.方式=='提款'}"><span style="color:red;">提款</span></c:if>
								<c:if test="${row.方式=='存款'}">${row.方式}</c:if></td>
								<td>$${String.valueOf(row.餘額.intValue()).replaceAll("\\B(?=(?:\\d{3})+(?!\\d))", ",")}</td>	<%--intValue()方法是將double轉成int--%>							
							</tr>
						</c:forEach>						
					</tbody>
				</table>
			</div>
		</div>
		
		
	</div>
</body>

</html>

