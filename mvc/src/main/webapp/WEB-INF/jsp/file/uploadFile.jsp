<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件列表</title>
<link href="${pageContext.request.contextPath}/component/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/component/bootstrap-fileinput/css/fileinput.min.css" rel="stylesheet">
</head>
<body>
<form role="form" action="${pageContext.request.contextPath}/file/file.spr?method=saveResFile" method="post" enctype="multipart/form-data">
   	<div class="panel panel-default">
		<div class="panel-body">
	   		<div class="col-md-4 col-sm-4 hidden">
	   			<div class="form-group">
					<label class="col-md-4 col-sm-4 control-label">应用名称:</label>
					<div class="col-md-8 col-sm-8 input-group">
						<input class="form-control" type="text" id="applicationName" name="applicationName" id="applicationName"/>
					</div>
				</div>
	   		</div>
	   		<div class="col-md-4 col-sm-4 hidden">
	   			<div class="form-group">
					<label class="col-md-4 col-sm-4 control-label">资源类型:</label>
					<div class="col-md-8 col-sm-8 input-group">
						<input class="form-control" type="text" id="resType" name="resType" id="resType"/>
					</div>
				</div>
	   		</div>
	   		<div class="col-md-4 col-sm-4 hidden">
	   			<div class="form-group">
					<label class="col-md-4 col-sm-4 control-label">资源ID:</label>
					<div class="col-md-8 col-sm-8 input-group">
						<input class="form-control" type="text" id="resId" name="resId" id="resId" />
					</div>
				</div>
	   		</div>
	   		<div class="col-md-12 col-sm-12">
	   			<div class="form-group">
	   				<label class="col-md-4 col-sm-4 control-label">文件上传:</label>
					<div class="col-md-8 col-sm-8 input-group">
						<input type="file" name="file" class="file" data-show-preview="false">
					</div>
				</div>
	   		</div>
		</div>
		<div class="panel-footer" style="text-align: center;">
			<button id="upload" type="button" class="btn btn-primary">上传</button>
		</div>
	</div>
</form>
<script src="${pageContext.request.contextPath}/component/jquery-easyui/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/component/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/component/bootstrap-fileinput/js/fileinput.min.js"></script>
<script src="${pageContext.request.contextPath}/component/bootstrap-fileinput/js/locales/zh.js"></script>
<script src="${pageContext.request.contextPath}/js/file/uploadFile.js"></script>
<script type="text/javascript">
var contextPath = "${pageContext.request.contextPath}";
var resId = "${requestScope.resId}";
var resType = "${requestScope.resType}";
var applicationName = "${requestScope.applicationName}";

$(function() {
	init();
});
</script>
</body>
</html>