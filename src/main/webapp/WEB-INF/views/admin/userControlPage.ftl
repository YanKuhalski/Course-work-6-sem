<!DOCTYPE html>
<html>
<head>
	 <link rel="stylesheet" type="text/css" href="/resources/css/control_page.css">
</head>
<body>
	<#include "../fragment/header.ftl">
	<div class="main-controle-div">
		<table>
			<tr>
				<th>Login </th>
				<th>Enabled</th>
				<th></th>
			</tr>

			<#list users as user>
				</tr>
					<td>${user.login}</td>
					<td>${user.enabled?c}</td>
					<td>
						<#if user.enabled>
						<a style="color:  red"  href="disableUser/${user.id}">Disable</a></td>
						<#else>
						<a href="enableUser/${user.id}">Enable</a></td>
						</#if>
				</tr>
			</#list>
		</table>
	</div>
</body>
</html>