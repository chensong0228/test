<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	var ws = null;
	function connect() {
		var url = "ws://127.0.0.1:8080/mvc/systemWebSocketHandler.spr";
		ws = new WebSocket(url);
		ws.onopen = function() {
			alert("connect success");
		};
		ws.onmessage = function(event) {
			alert("receice messageï¼" + event.data);
		};
		ws.onclose = function(event) {
			alert("close success");
		};
	}
	
	function disconnect() {
        if (ws != null) {
            ws.close();
            ws = null;
        }
    }

	function echo() {
		if (ws != null) {
			ws.send("发送测试消息：hello word");
		} else {
			alert('connection not established, please connect.');
		}
	}
</script>
</head>
<body>
	<div>
		<button id="connect" onclick="connect();">Connect</button>
		<button id="disconnect" onclick="disconnect();">Disconnect</button>
		<button id="echo" onclick="echo();">Echo message</button>
	</div>
</body>
</html>