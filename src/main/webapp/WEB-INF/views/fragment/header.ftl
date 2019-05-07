<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="/resources/css/main.css">
	<title>Buber</title>
</head>
<body>
	<header>
		<div class="logo-div">
			<a href="">BUBER</a>
		</div>

		<div class="login-div">
			<#if userName??>
			${userName}
			</#if>

			<a href="">Log in</a>
			<a href="">Log out</a>
		</div>
	</header>
</body>
</html>