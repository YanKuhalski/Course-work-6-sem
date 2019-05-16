<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="/resources/css/welcome_css.css">
	<title>Buber</title>
</head>
<body>
 
	<#include "fragment/header.ftl">
	<div style="display: flex;" >
			<div class="main-info-box">
				<#if userName??>

					<#if role="[ROLE_ADMIN]">
						<div class="admin-menu-button" ><a href="/admin/userControl/ROLE_USER/1"><center>User control</center></a></div>
						<div class="admin-menu-button" ><a href="/admin/userControl/ROLE_DRIVER/1"><center>Driver control</center></a></div>


					<#elseif  role="[ROLE_USER]">
						<#if regions??>
						<#if  driverNotEmpty?? ><div class="massage-div">${driverNotEmpty}</div></#if>	
						<form action="/common/addRideRequest">
							<select class="selection" name="regionIdfrom"  onchange="
							 var obj1 = document.getElementById('regionSel');  
							 obj1.style.display = 'block';">
								<#list regions as region>
								  <option  value="${region.id}">${region.name}</option>
								</#list>
							</select>
							<select class="selection" id="regionSel" name="regionIdto" style="  display: none;" onchange="
							 var obj1 = document.getElementById('carSelection');  
							 obj1.style.display = 'block';
							  var obj2 = document.getElementById('button');  
							 obj2.style.display = 'block';">
								<#list regions as region>
								  <option  value="${region.id}">${region.name}</option>
								</#list>
							</select>
							<select class="selection" id="carSelection" name="car" style="  display: none; " >
								<#list cars as car>
								  <option value="${car.id}">${car.brandName} ${car.carModel}</option>
								</#list>
							</select>
							<input class="submit-button" style="display: none;" id="button" class="button" type="submit" value="Go">
						</form>
						</#if>
						<#if activeTrip??>
						<#if  cantCancel?? ><div class="massage-div">Trip is already accepted</div></#if>	
							 <div class="massage-div"><br>
							Driver : ${activeTrip.driver.login}
							 <br> Car :
							${activeTrip.car.brandName} 
							${activeTrip.car.carModel} 
							${activeTrip.car.carNumber}
							 <br>
							 <br>
							 <br>
							 Price : 
							 ${price} $
							</div>

							<#if !activeTrip.isAccepted()>
							<a href="/common/cancelTrip/${activeTrip.id}">
								<div  class="register-button"><center> Cancel</center></div>
							</a>
							</#if> 
						</#if>



					<#elseif  role="[ROLE_DRIVER]">

						<#if activeTrip??>
							<div class="massage-div">
							<br>
							Client : ${activeTrip.client.login}
							<br>
							<br>  
							From :
							<br>
							${activeTrip.startRegion.name} 
							<br> 
							<br>
							To :
							<br>
							${activeTrip.endRegion.name} 
							
							<br>
							<br>
							<br>
							Price : 
							${price} $
							<br>
							<br>
							<br>
							Your profit : 
							${profit} $
							</div>
							<#if !activeTrip.isPayed()>
							<a href="/driver/acceptTripPayment/${activeTrip.id}">
								<div  class="register-button"><center> Accept payment</center></div>
							</a>
							</#if>
							<a href="/driver/acceptTripFinish/${activeTrip.id}">
								<div  class="register-button"><center> Finish</center></div>
							</a>

						</#if>

						<#if activeTrips?? &&  1 != size>
							<table>

								<#list 0..size-1  as x>
									<tr>
										<td style="width: 150px;">${activeTrips[x].client.login}</td>
										<td style="width: 250px;">${activeTrips[x].startRegion.name}</td>
										<td style="width: 250px;">${activeTrips[x].endRegion.name}</td>
										<td style="width: 100px;">${profits[x]} $</td>
										<td style="width: 100px;">
											<a style=" color:  blue;" href="/driver/acceptTrip/${activeTrips[x].id}"> Accept </a>
										</td>
									</tr>
								</#list>
							</table>
						</#if>


					<#else>

					</#if>
				<#else>	
					<h1>Your freedom</h1>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<div class="info-box">
					<div class=" info-div">
						<h2>For drivers</h2>
						<hr>
						Choose when taking orders.<br><br>
						<a href="registrationPage/ROLE_DRIVER">
							<div class="register-button"><center> Sing up as driver</center></div>
						</a>
					</div>
					<div  class=" info-div">
						<h2>For users</h2>
						<hr>
						Ordering a trip with 
						<br>Uber is very simple: 
						<br>just a few touches.
						<a href="registrationPage/ROLE_USER">
							<div class="register-button"><center>Sing up as user</center></div>
					</a>
					</div>
					</div>
				</#if>	
			</div>

			<div class=" pic-div " >
				<img class="pic " src="/resources/img/car1.jpg">
			</div>
	</div>

</body>
</html>


