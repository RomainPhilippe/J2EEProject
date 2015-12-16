<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,controller.MainController"%>
<%@ page import="java.util.ArrayList,model.Notification"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<section class="map"> 
					<div id="map">
					</div>
			    </section>
			</div>

			<!-- Zones -->
			<div class="col-xs-12 col-sm-5 col-md-5">
				<section>
						<caption>Définition des zones</caption>
		
						<div class="tableZone">
		
							<table class="table table-bordered" id="table_area">
								<!-- En-tête du tableau -->
								<thead>		
									<tr>
										<th>lieu</th>
										<th>commentaire</th>
									</tr>
								</thead>
								
								<!-- corps du tableau -->
								<tbody>
								</tbody>
							</table>
							
					
								<button type="button" class="btn btn-primary" id="addmarker">Add marker</button><br><br>
								
								<div class="form-group col-xs-4">
					                <label for="areaname" class="sr-only"></label>
					                <input id="areaname" class="form-control input-group-lg reg_name" type="text" name="areaname"
					                       title="Enter first name"
					                       placeholder="name aera"/>
					            </div>
					
					            <div class="form-group col-xs-4">
					                <label for="labelarea" class="sr-only"></label>
					                <input id="labelarea" class="form-control input-group-lg reg_name" type="text" name="labelarea"
					                       title="Enter last name"
					                       placeholder="label area"/>
					            </div>
					            
					            <div class="form-group col-xs-4">
					                <label for="rayon" class="sr-only"></label>
					                <input id="rayon" class="form-control input-group-lg reg_name" type="text" name="rayon"
					                       title="Enter last name"
					                       placeholder="Rayon"/>
					            </div>
     
    							 <button type="button" class="btn btn-primary" id="addarea">Add area</button>
							
							
							
            
						
						
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
							<th>id_user</th>
							<th>id_notif</th>
							<th>Date</th>
							<th>Dernière position (Latitude, Longitude)</th>
							<th>flag_notif</th>
						</tr>
					</thead>
					
					<!-- corps du tableau -->
					<tbody>
						<c:if test="${not empty listNotif}">
							<c:forEach var="notif" items="${listNotif}">	
								<tr>
									<td class="warning">${notif.id_user}</td>
									<td class="danger">${notif.id_notification}</td>
									<td class="danger">${notif.date}</td>
									<td class="danger">Latitude : ${notif.latitude} - Longitude : ${notif.longitude}</td>
									<td class="danger">${notif.flag_processing}</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
					
				</table>
			</div>
			
			</section>
		</div>
	
	</div>
</body>
</html>
 <script type="text/javascript" src="./resources/js/map.js"></script>