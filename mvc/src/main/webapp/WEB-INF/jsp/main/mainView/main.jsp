<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link href="${pageContext.request.contextPath}/component/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<button id="exit" type="button" class="btn btn-default btn-lg">退出</button>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/component/jquery-easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/component/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
var contextPath = "${pageContext.request.contextPath}";
$(function($) {
	$("#exit").bind("click", function(){
		$.ajax({
	        type :"get",
	        dataType :"text",
	        timeout: 30000,
	        url :contextPath+"/user/login.spr?method=logout",
	        success : function(url) {
	             if(url != "" && url != null){
	                 location.replace(url)
	             }
	        },
	        error :function(xhr, options) {
	        }
	    });
    });
});
</script>
</body>
</html>

