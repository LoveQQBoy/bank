<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>功能選項</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<link href="stylesheets/jquery-ui/hot-sneaks/jquery-ui.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" >

<script>
	$(function(){
		$( "#deposit, #withdrawal" ).dialog({resizable: false, autoOpen: false, modal: true, height: "auto", width: 400,
			show: {
			effect: "drop",
			duration: 500
			},
			hide: {
			effect: "fold",
			duration: 500
			}
		});
		
		
		$( "#debt" ).on( "click", function() {
	    	$( "#deposit" ).dialog( "open" );
	    	$("#insp").html("");
	    	$("#inputmoney").val("");
	    	$('#h1').html("");	    	
	    });
		
		
		$( "#wibt" ).on( "click", function() {
	    	$( "#withdrawal" ).dialog( "open" );
	    	$("#outsp").html("");
	    	$("#outputmoney").val("");
	    	$('#h1').html("");
	    });
		
		
		$( "#debtn, #wibtn" ).on( "click", function() {
	    	$( "#deposit, #withdrawal" ).dialog( "close" );	    	
	    });
	
		
		var flag = false;		
		$("#inputmoney, #outputmoney").on("change",function(){
			let money = $(this).val();
	    	let outmoney = $("#outputmoney").val();
	    	
	    	if(!(money.match(/^[1-9][\d]*00/g)) || outmoney>${ sessionScope.totalmoney}){
	    		$("#insp, #outsp").html("無效數字");	
	    		flag = false;
	    	}else {
	    		$("#insp, #outsp").html("");
	    		flag = true;
	    	}   		  	
	    });
		
		
		$( "#debty, #wibty" ).on( "click", function(e) {			
			if(!flag){
	    		e.preventDefault();
	    	}
	    });
								
		
		if($("#regex").html()!=""){
			let value = $("#regex").html();
			let regex = /\B(?=(?:\d{3})+(?!\d))/g
			$("#regex").html(value.toString().replace(regex, ','));
			
		}
				
	});
</script>

</head>
<body>

	<div class="container" style="width:30%;margin-top:10%">
		<div class="float-start">
			<h2>${ sessionScope.authenticated}&nbsp;君</h2>
		  	<h4 class="text-muted">歡迎蒞臨&nbsp;嵂昕銀行</h4>
			<h5 class="mt-4">戶號&nbsp;:&nbsp;${ sessionScope.su}</h5>	
			<h5>餘額&nbsp;:&nbsp;<span id="regex">${ sessionScope.totalmoney}</span>元</h5>		
		</div>
		
		<div class="btn-group-vertical float-end" role="group" aria-label="Vertical button group">
			<input class="btn btn-primary" type="button" value="用戶資料修改" id="revise-bt" onclick="location.href = `<c:url value='/BRevise' />`" />
			<input class="btn btn-danger" type="button" value="登出"  onclick="location.href = `<c:url value='/MemberLogout_controller' />`" />
		</div>
				
		<div class="btn-group container mt-3" role="group" aria-label="Basic outlined button group">
		  	<input type="button" value="存款" id="debt" class="btn btn-outline-primary"/>
			<input type="button" value="提款" id="wibt" class="btn btn-outline-primary"/>
		  	<input type="button" value="交易紀錄"  onclick="location.href = `<c:url value='/TransactionRecord.jsp' />`" class="btn btn-outline-primary"/>	
		</div>
		<br>
		<h3 id='h1' class="mt-3" style="color:red;margin-left:35%">${sessionScope.dewimessage}</h3>	
	</div>
	
	<form autocomplete="off" action="<c:url value='/Dewi_controller' />" id='deposit' method="post" title="存款">
		<input type="text" name="inputmoney" id='inputmoney' placeholder="請輸入金額(至少百元)" />
		<span>元</span><br><span id='insp'></span><br>
		<input type="submit" id="debty" value="確定"/>
		<input type="button" id="debtn" value="取消" />				
	</form>
	
	<form autocomplete="off" action="<c:url value='/Dewi_controller' />" id='withdrawal' method="post" title="提款">
		<input type="text" name="outputmoney" id='outputmoney' placeholder="請輸入金額(至少百元)" />
		<br><span id='outsp'></span><br>
		<input type="submit" id="wibty" value="確定"/>
		<input type="button" id="wibtn" value="取消" />				
	</form>
	
</body>
</html>