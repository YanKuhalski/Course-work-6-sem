<div style="position:  absolute; top:  174 ;
 background-color:#F8F8FF; left:  500 ; padding:15  150 ; border:  solid 	#808080 1px ">
	<h1>BUBER</h1>

	<#if  error?? ><div>${error}</div></#if>

	<form name='login' action="/register/${role}" method='POST'>
		<table>
			<tr>
				<td>UserName:</td>
				<td><input type='text' name='username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			

			<#if role="ROLE_DRIVER">
			<tr>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td>Car brand:</td>
				<td><input type='text' name='brand' /></td>
			</tr>

			<tr>
				<td>Model:</td>
				<td><input type='text' name='model' /></td>
			</tr>

			<tr>
				<td>Number:</td>
				<td><input type='text' name='number' /></td>
			</tr>

			</#if>

			<tr>
				<td ><input name="submit" type="submit" value="submit" /></td>
				<td><a href="/"> Back to main</a></td>
			</tr>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</table>
		 
	</form>

  <br/>
</div>
