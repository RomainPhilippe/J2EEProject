<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profil</title>
<style type="text/css">
      #map { height: 100%;width:100% }
    </style>
<!-- CSS -->
<link href="./resources/css/profil.css" rel="stylesheet">

 <script type="text/javascript" src="./resources/js/jquery.js"></script>

<link href="./resources/css/bootstrap.min.css" rel="stylesheet">
   <script async defer
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCnxxaOXRTy7v9YA63bDMEW8Qc05j1dNis&callback=initMap">
    </script>
    
 <script type="text/javascript" src="./resources/js/map.js"></script>
 

    
    
</head>

<body>

	<!-- /header -->

	<header class="header">
	<div class="text-center">
		<h1>Localizonles</h1>
		<h3>Gardez un oeil sur la prunelle de vos yeux</h3>
		<button type="button" class="btn btn-danger">Déconnexion</button>
	</div>
	</header>

	<div class="container">

		<div class="row">

			<!-- Map -->
			<div class="col-xs-12 col-sm-7 col-md-7">
				<section class="map"> <div id="map"></div>

   
   
 
    </section>
			</div>

			<!-- Zones -->
			<div class="col-xs-12 col-sm-5 col-md-5">
				<section>
				<caption>Définition des zones</caption>

				<div class="tableZone">

					<table class="table table-bordered">
						<!-- En-tête du tableau -->
						<thead>		
							<tr>
								<th>lieu</th>
								<th>commentaire</th>
							</tr>
						</thead>
						
						<!-- corps du tableau -->
						<tbody>
							<tr>
								<td class="warning">19:12</td>
								<td class="danger">Issy</td>
							</tr>

							<tr>
								<td class="warning">20:12</td>
								<td class="danger">Sept</td>
							</tr>

							<tr>
								<td class="warning">21:12</td>
								<td class="danger">Paris</td>
							</tr>
						</tbody>
					</table>
				</div>
				</section>
			</div>
		</div>

		<div class="row">
			<!-- notification -->

			<section class="col-xs-12 col-sm-12 col-md-12">
			<caption>Notification</caption>
			<div class="table-responsive">

				<table class="table table-bordered">
					
					<!-- En-tête du tableau -->
					<thead>
						<tr>
							<th>Heure</th>
							<th>Dernière position</th>
						</tr>
					</thead>
					
					<!-- corps du tableau -->
					<tbody>
						<tr>
								<td class="warning">19:12</td>
								<td class="danger">Issy</td>
							</tr>

							<tr>
								<td class="warning">20:12</td>
								<td class="danger">Sept</td>
							</tr>

							<tr>
								<td class="warning">21:12</td>
								<td class="danger">Paris</td>
							</tr>
					</tbody>
					
				</table>
			</div>
			</section>
		</div>
	</div>



</body>
</html>