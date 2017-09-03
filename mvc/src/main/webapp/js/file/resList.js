function init(){
	initResTable();
}

function initResTable(){
	var condition = new Object();
	condition.resId = resId;
	condition.resType = resType;
	condition.applicationName = applicationName;
	$.ajax({
		type: "POST",
		url: contextPath+"/file/file.spr?method=queryResFiles",
		data: "&condition="+encodeURI(encodeURI(JSON.stringify(condition))),
		dataType: "json",
		success: function(data, textStatus) {
			addTable(data);
		}
	});
}

function addTable(data) {
	var rows = "<tr>";
	for(var i=0; data != null && i<data.length; i++){
		rows += "<tr><td class=\"fileId\">"+data[i].file_id+"</td>";
		rows +=	"<td><a href=\"javascript:void(0)\" onclick=\"downloadFile("+data[i].file_id+");\">"+data[i].file_name+"</a></td>";
		rows +=	"<td>"+data[i].file_size+"</td><td>"+data[i].create_time+"</td>";
		rows +=	"<td><a href=\"javascript:void(0)\" onclick=\"downloadFile("+data[i].file_id+");\">下载</a></td>";
		rows +=	"<td><a href=\"javascript:void(0)\" onclick=\"deleteFile("+data[i].file_id+");\">删除</a></td>";
	}
	rows += "</tr>";
	$('#resTable').empty();
	$('#resTable').append(rows);
}
function downloadFile(fileId){
	var url = contextPath + "/file/file.spr?method=downloadFile&fileId="+fileId;
	window.open(url);
}
function deleteFile(fileId){
	$.ajax({
		type: "POST",
		url: contextPath+"/file/file.spr?method=deleteFile",
		data: "&fileId="+fileId,
		dataType: "text",
		success: function(data, textStatus) {
			initResTable();
		}
	});
}