function init(){
	$("#applicationName").val(applicationName);
	$("#resType").val(resType);
	$("#resId").val(resId);
	$("#upload").bind("click", function(){
	  submitForm();
	});
}

function submitForm(){
	$("form").submit();
//	window.location.replace(location.href);
//	window.location = contextPath+"/file/file.spr?method=toResList&applicationName=relax1992&resType=001&resId=001";
}