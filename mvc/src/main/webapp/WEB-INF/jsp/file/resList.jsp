<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件列表</title>
<link href="${pageContext.request.contextPath}/component/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/skin/css/file/resList.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
	<div class="row">
		<table class="table table-striped">
			<thead>
				<tr>
					<th class="fileId">文件ID</th>
					<th>文件名称</th>
					<th>大小</th>
					<th>上传时间</th>
					<th>下载</th>
					<th>删除</th>
				</tr>
			</thead>
			<tbody id="resTable">

			</tbody>
		</table>
	</div>
</div>
<script src="${pageContext.request.contextPath}/component/jquery-easyui/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/component/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/file/resList.js"></script>
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