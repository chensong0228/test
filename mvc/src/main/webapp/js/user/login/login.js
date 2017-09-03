function init() {
	$("#userName").bind("focus", function(){
		$('#userName').tooltip('hide');
	});
	$("#password").bind("focus", function(){
		$('#password').tooltip('hide');
	});
	$("#login").bind("click", function(){
		login();
	});
}

function login() {
	//进行校验
	var useInfo = validation();
	if(!useInfo){
		return;
	}
	$.ajax({
		type: "GET",
		url: contextPath+"/user/login.spr?method=login",
		data: useInfo,
		dataType: "json",
		success: function(data, textStatus) {
			var result = data.result;
			var userNameMsg = data.userNameMsg;
			var passwordMsg = data.passwordMsg;
			if(result == "success"){
 				window.location = contextPath+"/basic/basic.spr?method=toBasic";
			}else if(loginNameMsg != ""){
				$('#userName').tooltip({
					title : userNameMsg,
					placement : 'right',
					trigger : 'manual',
					container : $("#userNameMsg")
				});
				$('#userName').tooltip('show');
			}else if(passwordMsg != ""){
				$('#password').tooltip({
					title : passwordMsg,
					placement : 'right',
					trigger : 'manual',
					container : $("#passwordMsg")
				});
				$('#password').tooltip('show');
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			console.log("登录发生错误");
		}
	});
}

function validation(){
	var userName = $("#userName").val().trim();
	var password = $("#password").val().trim();
	if(userName == ""){
		$('#loginName').tooltip({
			title : '用户名不能为空',
			placement : 'right',
			trigger : 'manual',
			container : $("#loginNameMsg")
		});
		$('#loginName').tooltip('show');
		return null;
	}
	if(password == ""){
		$('#password').tooltip({
			title : '密码不能为空',
			placement : 'right',
			trigger : 'manual',
			container : $("#passwordMsg")
		});
		$('#password').tooltip('show');
		return null;
	}
	var userInfo = {userName:userName,password:password};
	return userInfo;
}