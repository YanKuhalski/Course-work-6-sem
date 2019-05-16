<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="/resources/css/main.css">
	<title>Buber</title>
</head>
<body>
	<header>
		<div class="left-box">
			<a href="/">
				<div class="logo-div">
					BUBER
				</div>
			</a>

		</div>


		<div class="right-box">
			 
				
				<#if userName??>
					<#if role??>
						<#if role="[ROLE_USER]" || role="[ROLE_DRIVER]" >
							<div><a href="/common/tripStatistic/${userName}"><img class="table-icon" src="/resources/img/tableIcon.png"></a></div>
						</#if>
					</#if>
				<div class="login">${userName} | </div>
				
				<a  href="/logout"><div class="login-button"> <center style =" color:  white;">Log out
				<#else>

				<div class="login"> </div>
				<a href="/loginPage"><div class="login-button"> <center style =" color:  white;"> Log in
				</#if>
				
				</center></a></div>
				
		</div>

	</header>
</body>
</html>