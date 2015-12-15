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

<!-- CSS -->
<link href="./resources/css/profil.css" rel="stylesheet">

<!-- Bootstrap Core CSS -->
<link href="./resources/css/bootstrap.min.css" rel="stylesheet">
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
				<section class="map"> <iframe width="100%" height="100%"
					scrolling="no"
					src="https://maps.google.com/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=Twitter,+Inc.,+Market+Street,+San+Francisco,+CA&amp;aq=0&amp;oq=twitter&amp;sll=28.659344,-81.187888&amp;sspn=0.128789,0.264187&amp;ie=UTF8&amp;hq=Twitter,+Inc.,+Market+Street,+San+Francisco,+CA&amp;t=m&amp;z=15&amp;iwloc=A&amp;output=embed"></iframe>
				<br />
				<small> <a
					href="https://maps.google.com/maps?f=q&amp;source=embed&amp;hl=en&amp;geocode=&amp;q=Twitter,+Inc.,+Market+Street,+San+Francisco,+CA&amp;aq=0&amp;oq=twitter&amp;sll=28.659344,-81.187888&amp;sspn=0.128789,0.264187&amp;ie=UTF8&amp;hq=Twitter,+Inc.,+Market+Street,+San+Francisco,+CA&amp;t=m&amp;z=15&amp;iwloc=A"></a>
				</small> </section>
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