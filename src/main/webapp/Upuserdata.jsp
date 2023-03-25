<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用戶資料修改</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<!-- <link rel="stylesheet" href="/resources/demos/style.css"> -->
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<link href="stylesheets/jquery-ui/hot-sneaks/jquery-ui.css" rel="stylesheet">
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" >


<script>
$(function(){	 
	
   	//$("form").append(`<div>${requestScope.userdataError}</div>`);
   	   	   	
          
    $("#account").on("change blur",function(){
    	let account = $(this).val();  	
    	if(!(account.match(/[A-Za-z\d]{1,20}/g))){
    		$("#sp2").html("格式錯誤");
    		$(this).addClass('error');   		
    	}else {
    		$("#sp2").html("");
    		$(this).removeClass('error');
    	}   		  	
    });
    
    
    $("#password").on("change blur",function(){  
    	$(this).attr("type", "password");
    	let pwd = $(this).val();
    	if(!(pwd.match(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,20}$/g))){
    		$("#sp3").html("格式錯誤");
    		$(this).addClass('error');   		
    	}else{     		
    		$("#sp3").html("");
    		$(this).removeClass('error');  		
    	}
    });
	
	
    $("#password").on("focus",function(){
   		$(this).attr("type", "text");    	 		
   		
    });
    
    
    let array=['newname', 'bornDate', 'identityCard', 'phoneNumber', 'email'];
    for(let i=0;i<array.length;i++){
    	$("#"+array[i]).on("change blur", function(){
        	let value = $(this).val();   	
        	if( value==null || value=='' || value.includes(" ")) {  
        		$(this).next().html("");
        		$(this).next().html("不可空白"); 
        		$(this).addClass('error');
        	}else{
        		$(this).next().html("");
        		$(this).removeClass('error');
        	}       	
        });
    }
        
    
    $("#revise").click(function(e){   	
    	if($("#dialog-form input").hasClass('error')){
	  		e.preventDefault();
	  	}
    })                	
				            
    
});	

</script>

</head>
<body>
	<div class="container" style="margin-top:5%;width:30%">
  	<form autocomplete="off"  action="<c:url value='/ARevise' />" method="post">		
        <label for="newname">姓名</label>
        <input type="text" name="username" id="username" placeholder="username"  
        class="m-2 text ui-widget-content ui-corner-all" value="${sessionScope.authenticated}">
        <span></span><br>
        
        <label for="newaccount">帳號</label>
        <input type="text" name="account" id="account" placeholder="account" 
        class="m-2 text ui-widget-content ui-corner-all" value="${sessionScope.account}">
        <span id=sp2></span><br>
        
        <label for="newpwd">密碼</label>
        <input type="password" name="password" id="password" placeholder="password"  
        class="m-2 text ui-widget-content ui-corner-all" value="${sessionScope.password}">
        <span id=sp3></span><br>
        
        
        <label for="bornDate">出生日期</label>
		<input type="date" name="bornDate" id="bornDate" placeholder="bornDate"  
		class="m-2 text ui-widget-content ui-corner-all" value="${sessionScope.bornDate}"/>
		<span></span><br>
		
		
		<label for="identityCard">身分證號碼</label>
		<input type="text" name="identityCard" id="identityCard" placeholder="identityCard" 
		class="m-2 text ui-widget-content ui-corner-all" value="${sessionScope.identityCard}"/>
		<span></span><br>
		
		
		<label for="phoneNumber">電話號碼</label>
		<input type="text" name="phoneNumber" id="phoneNumber" placeholder="phoneNumber" 
		class="m-2 text ui-widget-content ui-corner-all" value="${sessionScope.phoneNumber}"/>
		<span></span><br>
		
		
		<label for="email">信箱</label>
		<input type="email" name="email" id="email" placeholder="email"  
		class="m-2 text ui-widget-content ui-corner-all" value="${sessionScope.email}"/>
        <span></span><br>
        				     				      	    	    	    
  		<div class="alert alert-danger w-75" role="alert">*帳號:請輸入20位內的字母或數字<br>*密碼:請輸入6~20位的英、數字，需包含字母及數字</div>
  		<input type="submit" value="修改" id="revise"/>
  		<input type="button" value="刪除" id="delete" onclick="location.href = `<c:url value='Deleteuser' />`"/>
  		<input type="button" value="返回" id="cancel" onclick="location.href = `<c:url value='/MemberOperation.jsp' />`"/>
  	</form>
  	</div>
  	
  	<h3 style="color:red;margin-left:38%" class="mt-3">${requestScope.reviseMessage}${requestScope.deleteMessage }</h3>
  		
</body>
</html>