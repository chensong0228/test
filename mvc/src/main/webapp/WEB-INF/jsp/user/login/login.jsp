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
		<div class="row">
			<div class="col-sm-4 col-sm-offset-5">
				<h1>&nbsp;</h1>
				<h1>Relax1992</h1>
				<h1>&nbsp;</h1>
			</div>
		</div>
		<div class="row">
			<form class="form-horizontal" action="#">
				<div class="form-group">
					<label for="userName" class="col-sm-4 control-label">用户名</label>
					<div class="col-sm-4">
						<input type="text" class="form-control input-lg" id="userName" placeholder="请输入用户名">
					</div>
					<div id="userNameMsg" class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-4 control-label">密码</label>
					<div class="col-sm-4">
						<input type="password" class="form-control input-lg" id="password" placeholder="请输入密码">
					</div>
					<div id="passwordMsg" class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-2">
						<div class="checkbox">
							<h4>
								<label><input type="checkbox">记住密码</label>
							</h4>
						</div>
					</div>
					<div class="col-sm-2">
						<button id="login" type="button" class="btn btn-default btn-lg">登录</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/component/jquery-easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/component/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/user/login/login.js"></script>
	<script type="text/javascript">
var contextPath = "${pageContext.request.contextPath}";
$(function($) {
	init();
});
</script>
</body>
</html>

