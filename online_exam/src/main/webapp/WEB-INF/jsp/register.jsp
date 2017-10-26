﻿<!DOCTYPE html>
<html lang="zh-CN">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>注册丨Sharelink</title>
<link rel="stylesheet" href="css/style.css">
<body>

<div class="register-container">
	<h1>用户注册</h1>
	
	<div class="connect">
		<p>Link the world. Share to world.</p>
		<p class="red">${msg}</p>	
	</div>
	
	<form action="doRegister" method="post" id="registerForm">
		<div>
			<input type="text" name="userName" class="username" placeholder="您的用户名" autocomplete="off"/>
		</div>
		<div>
			<input type="password" name="passWord" class="password" placeholder="输入密码" oncontextmenu="return false" onpaste="return false" />
		</div>
		<div>
			<input type="password" name="confirm_password" class="confirm_password" placeholder="再次输入密码" oncontextmenu="return false" onpaste="return false" />
		</div>
		<button id="submit" type="submit">注 册</button>
	</form>
	<a href="index.html">
		<button type="button" class="register-tis">已经有账号？</button>
	</a>

</div>

</body>
<script src="http://www.jq22.com/jquery/1.11.1/jquery.min.js"></script>
<script src="js/common.js"></script>
<!--背景图片自动更换-->
<script src="js/supersized.3.2.7.min.js"></script>
<script src="js/supersized-init.js"></script>
<!--表单验证
<script src="js/jquery.validate.min.js?var1.14.0"></script>
-->
</html>