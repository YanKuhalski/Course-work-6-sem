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
				<th>Client </th>
				<th>Driver </th>
				<th>Car </th>
				<th>From </th>
				<th>To </th>
				<th>Discount </th>
				<th>Accepted </th>
				<th>Payed </th>
				<th>Finished </th>
			</tr>

			<#list trips as trip>
				<tr>
					<td>${trip.client.login}</td>
					<td>${trip.driver.login}</td>
					<td>${trip.car.brandName} ${trip.car.carModel} ${trip.car.carNumber}</td>
					<td>${trip.startRegion.name}</td>
					<td>${trip.endRegion.name}</td>
					<td>${trip.discount.value}</td>
					<td>${trip.isAccepted()?c}</td>
					<td>${trip.isPayed()?c}</td>
					<td>${trip.isFinished()?c}</td>
				</tr>
			</#list>
		</table>
	</div>
</body>
</html>



