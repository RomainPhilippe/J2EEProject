<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,controller.MainController"%>
<%@ page import="java.util.ArrayList,model.Notification"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profil</title>
<style type="text/css">
#map {
	height: 100%;
	width: 100%
}
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

	<script type="text/javascript">
		modelAttributeValue = '${token}';
		id_user = '${id_user}';
	</script>

	<!-- /header -->

	<header class="header">
	<div class="text-center">
		<h1>Localizonles</h1>
		<h3>Gardez un oeil sur la prunelle de vos yeux</h3>
		<h4>Numéro du token : ${token}</h4>

		<form:form method="GET" action="redirect">
			<input class="btn btn-danger" type="submit" value="Déconnexion" />
		</form:form>

	</div>
	</header>

	<div class="container">

		<div class="row">

			<!-- Map -->
			<div class="col-xs-12 col-sm-7 col-md-7">
				<section class="map">
				<div id="map"></div>
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
								<th>delete</th>
							</tr>
						</thead>
					</table>

					<button type="button" class="btn btn-primary" id="addmarker">Add
						marker</button>
					<br> <br>

					<div class="form-group col-xs-4">
						<label for="areaname" class="sr-only"></label> <input
							id="areaname" class="form-control input-group-lg reg_name"
							type="text" name="areaname" title="Enter first name"
							placeholder="name aera" />
					</div>

					<div class="form-group col-xs-4">
						<label for="labelarea" class="sr-only"></label> <input
							id="labelarea" class="form-control input-group-lg reg_name"
							type="text" name="labelarea" title="Enter last name"
							placeholder="label area" />
					</div>

					<div class="form-group col-xs-4">
						<label for="rayon" class="sr-only"></label> <input id="rayon"
							class="form-control input-group-lg reg_name" type="text"
							name="rayon" title="Enter last name" placeholder="Rayon" />
					</div>

					<button type="button" class="btn btn-primary" id="addarea">Add
						area</button>
				</div>
				</section>
			</div>
		</div>

		<div class="row">
			<!-- notification -->
			<section class="col-xs-12 col-sm-12 col-md-11">
			<caption>Notification</caption>

			<div class="table-responsive">
				<table class="table table-bordered" id="table_notif">
					<!-- En-tête du tableau -->
					<thead>
						<tr>
							<th>id_notif</th>
							<th>Date</th>
							<th>Dernière position (Latitude, Longitude)</th>
							<th>flag_notif</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>

			</section>
			
			<section class="col-xs-2 col-sm-2 col-md-1">
				<button type="button" class="btn btn-default btn-lg" id="refresh_notif">
	  					<img src="./resources/img/refresh.png"/>
				</button>
			</section>
		</div>

	</div>
</body>
</html>

<script type="text/javascript">
	var map;
	var initialize;
	var listMarker = [];
	var markertest;
	var map;
	var latitudeMarker;
	var longitudeMarker;

	function initMap() {
		map = new google.maps.Map(document.getElementById('map'), {
			center : {
				lat : 48.856614,
				lng : 2.352222
			},
			zoom : 11
		});

		markertest = new google.maps.Marker({
			map : map,
			draggable : true,
			animation : google.maps.Animation.DROP,
			position : {
				lat : 48.856614,
				lng : 2.352222
			},
			icon : './resources/img/marker.png'
		//icon :goldStar
		});
		markertest.setVisible(false);
		getArea(map);
		getNotification();
	}

	$("#addmarker").click(function() {
		console.log(markertest.getVisible());
		if (!markertest.getVisible()) {
			console.log("pas de marekre");
			markertest.setVisible(true);
		}

	});

	$("#addarea").click(
			function() {
				//console.log("add area");
				console.log($('#rayon').val());
				//alert('id_user' + id_user);
				//alert('token' + modelAttributeValue);
				$.ajax({
					url : 'createArea/' + id_user + '/'
							+ markertest.getPosition().lat() + '/'
							+ markertest.getPosition().lng() + '/'
							+ $('#areaname').val() + '/'
							+ $('#labelarea').val() + '/' + $('#rayon').val(),
					type : 'POST',
					encoding : "UTF-8",
					async : true,
					success : function(data) {
						window.location = "profil";
					}
				});
			});

	function getArea(map) {

		$
				.ajax({
					url : 'getArea/' + '${token}',
					type : 'POST',
					encoding : "UTF-8",
					async : true,
					success : function(data) {
						//alert(data);
						console.log(data['area'].length);
						var length = data['area'].length;

						for (var i = 0; i < length; i++) {
							id_area = data['area'][i]['id_area'];
							latitude = data['area'][i]['latitude'];
							longitude = data['area'][i]['longitude'];
							distance = data['area'][i]['distance'];
							nameArea = data['area'][i]['name_area'];
							labelArea = data['area'][i]['label_area'];
							//console.log("latitude : "+ data['area'][i]['latitude']);
							//console.log("longitude : "+ data['area'][i]['longitude']);
							var myLatlng = new google.maps.LatLng(latitude,
									longitude);
							listMarker[i] = createMarker(myLatlng, "m" + i,
									map, nameArea, labelArea, id_area,
									latitude, longitude, distance);

							$('#table_area tr:last')
									.after(
											'<tr><td>'
													+ nameArea
													+ '</td>'
													+ '<td>'
													+ labelArea
													+ '</td>'
													+ '<td><button type="button" class="btn btn-primary" onclick=deleteArea('
													+ id_area
													+ ')>delete marker</button></td>'
													+ '</tr>');
						}

					}
				});

	}

	function getNotification() {

		//$("#table_notif tr").remove(); 
		
		$.ajax({
					url : 'getNotifications/' + '${token}',
					type : 'POST',
					encoding : "UTF-8",
					async : true,
					success : function(data) {
						//alert(data);
						//console.log(data['area'].length);
						var length = data['notification'].length;

						for (var i = 0; i < length; i++) {
							id_notification = data['notification'][i]['id_notification'];
							latitude = data['notification'][i]['latitude'];
							longitude = data['notification'][i]['longitude'];
							date = data['notification'][i]['date'];
							flag_processing = data['notification'][i]['flag_processing'];
							if(flag_processing==0){
								flag_processing="En cours...";
								classBoostrap="danger";
							}else{
								flag_processing="Terminé";
								classBoostrap="success";
							}
							$('#table_notif tr:last')
									.after('<tr class="'+classBoostrap+'"><td>' + id_notification
													+ '</td>' + '<td>'
													+ date + '</td>' + '<td>(' + latitude +' - '+ longitude 
													+ ')</td>' + '<td>' + flag_processing + '</td>' + '</tr>');
						}

					}
				});

	}
	
	$("#refresh_notif").click(
			function() {
				$("#table_notif").find("tr:gt(0)").remove();
				getNotification();
			});

	function deleteArea(id) {

		$.ajax({
			url : 'deleteArea/' + id,
			type : 'POST',
			encoding : "UTF-8",
			async : true,
			success : function(data) {
				alert("La zone a bien été supprimé");
				window.location = "profil";
			}
		});
	}

	function createMarker(pos, t, map, title, description, id, latitude,
			longitude, distance) {
		var marker = new google.maps.Marker({
			position : pos,
			map : map, // google.maps.Map 
			title : t
		});

		var contentString = '<a href="detailObject.action?id=' + id
				+ '"><div id="content" class="myInfoWindow">'
				+ '<div id="siteNotice">' + '</div>'
				+ '<h1 id="firstHeading" class="firstHeading">' + title
				+ '</h1>' + '<div id="bodyContent">' + '<p>' + description
				+ '</p>' + '</div>' + '</div>';

		var cityCircle = new google.maps.Circle({
			strokeColor : '#FF0000',
			strokeOpacity : 0.8,
			strokeWeight : 2,
			fillColor : '#FF0000',
			fillOpacity : 0.35,
			map : map,
			center : pos,
			radius : distance
		});

		var infowindow = new google.maps.InfoWindow({
			content : contentString
		});

		google.maps.event.addListener(marker, 'click', function() {
			infowindow.open(map, marker);
		});
		return marker;
	}
</script>


