<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>嵂昕銀行入口網</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<link href="stylesheets/jquery-ui/hot-sneaks/jquery-ui.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" >


<script>
$( function() {
	 var dialog = $( "#dialog-form" ).dialog({resizable: false, 
		  		   autoOpen: false, modal: true, width: 500, 
					show: {
					effect: "puff",
					duration: 500
					},
					hide: {
					effect: "fade",
					duration: 500
					}		
				});
	 
	  
	$("#bt").click(function(){
		dialog.dialog( "close" );
	});  

	
    
    $( "#create-user" ).on( "click", function() {
    	$( "#dialog-form" ).dialog( "open" );
    	$("#dialog-form input:lt(7)").val("");
    	$("#sp, #dialog-form span:lt(7)").html("");
    	$("#sub").attr('disabled',true);
    	$( "#dialog-form input:lt(7)" ).addClass('error');
    });
             

    $("#newaccount").on("change blur",function(){
    	let account = $(this).val();  	
    	if(!(account.match(/[A-Za-z\d]{1,20}/g))){
    		$("#sp2").html("格式錯誤");
    		$(this).addClass('error');   		
    	}else {
    		$("#sp2").html("");
    		$(this).removeClass('error');
    	}  
    });
    
    
    $("#newpwd").on("change blur",function(){
    	let pwd = $(this).val();
    	if(!(pwd.match(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,20}$/g))){
    		$("#sp3").html("格式錯誤");
    		$(this).addClass('error');    		
    	}else{    		  		
    		$("#sp3").html("");
    		$(this).removeClass('error');  		
    	}
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
       
    
    $("#dialog-form input").change(function(){
    	console.log($("#dialog-form input").hasClass('error'))
    	if($("#dialog-form input").hasClass('error')){
    		$("#sub").attr('disabled',true);
    	}else $("#sub").attr('disabled',false);
    });
               
});
</script>
</head>

<body>

<div class="container w-25 " style="margin-top:10%">
	<h1 class="mb-5 ps-3 ms-3">嵂昕銀行登入網</h1>

	<form action="<c:url value='/MemberLogin_controller' />" method="post">
		<div class="form-floating mb-3">
		  <input type="text" name="account" class="form-control" id="floatingInput" placeholder="帳號" >
		  <label for="floatingInput">帳號</label>
		</div>
		<div class="form-floating">
		  <input type="password" name="pwd" class="form-control" id="floatingPassword" placeholder="密碼" >
		  <label for="floatingPassword">密碼</label>
		</div>	
		<h5>${ requestScope.loginerror} ${ requestScope.waitlogin}${requestScope.deleteMessage}</h5>	
		
		<div class="d-grid gap-2">
			<input type="submit" value="登入" class="btn btn-primary">				
			<input type="button" value="建立帳戶" id="create-user" class="btn btn-primary">
			<button class="btn btn-primary" type="button"
			name="returnHome" id="returnHome"
			onclick="location.href=`<c:url value='/Index.jsp'/>`">返回首頁</button>
			<span id=sp>${ requestScope.createmessage}</span>	
		</div>
	</form>
</div>
  
<div id="dialog-form" title="新建用戶">
  	<form action="<c:url value='/MemberApplication_controller' />" method="post">		
        <label for="newname">姓名</label>
        <input type="text" name="username" id="newname" placeholder="username" autocomplete="off" class="m-2 text ui-widget-content ui-corner-all" >
        <span id=sp1></span><br>
        
        <label for="newaccount">帳號</label>
        <input type="text" name="account" id="newaccount" placeholder="account" autocomplete="off" class="m-2 text ui-widget-content ui-corner-all">
        <span id=sp2></span><br>
        
        <label for="newpwd">密碼</label>
        <input type="password" name="password" id="newpwd" placeholder="password" autocomplete="off" class="m-2 text ui-widget-content ui-corner-all">
        <span id=sp3></span><br>
        
        <label for="bornDate">出生日期</label>
		<input type="date" name="bornDate" id="bornDate" placeholder="bornDate" autocomplete="off" class="m-2 text ui-widget-content ui-corner-all"/>
		<span id=sp4></span><br>
		
		<label for="identityCard">身分證號碼</label>
		<input type="text" name="identityCard" id="identityCard" placeholder="identityCard" autocomplete="off" class="m-2 text ui-widget-content ui-corner-all"/>
		<span id=sp5></span><br>
		
		<label for="phoneNumber">電話號碼</label>
		<input type="text" name="phoneNumber" id="phoneNumber" placeholder="phoneNumber" autocomplete="off" class="m-2 text ui-widget-content ui-corner-all"/>
		<span id=sp6></span><br>
		
		<label for="email">信箱</label>
		<input type="email" name="email" id="email" placeholder="email" autocomplete="off" autocomplete="off" class="m-2 text ui-widget-content ui-corner-all"/>
        <span id=sp7></span><br>
        				     				      	    	    	    
  		<div class="alert alert-danger" role="alert">*帳號:請輸入20位內的字母或數字<br>*密碼:請輸入6~20位的英、數字，需包含字母及數字</div>
  		<br><input type="submit" value="創建" id="sub"/>
  		<input type="button" value="取消" id="bt"/>
  	</form>
</div>
   
</body>
</html>