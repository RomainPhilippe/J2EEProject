var map;
 var initialize;
 var listMarker=[];
 var markertest;
 var map;
 var latitudeMarker;
 var longitudeMarker;

 function initMap() {
   map = new google.maps.Map(document.getElementById('map'), {
     center: {lat: 48.856614 , lng: 2.352222},
     zoom: 11
   });
   
   markertest = new google.maps.Marker({
 	    map: map,
 	    draggable: true,
 	    animation: google.maps.Animation.DROP,
 	    position: {lat: 48.856614 , lng: 2.352222},
 	    icon: './resources/img/marker.png'
 	    //icon :goldStar
 	  });
   markertest.setVisible(false);
   getArea(map);
 }



 $( "#addmarker" ).click(function() {
 	console.log(markertest.getVisible());	
 	if(!markertest.getVisible()){
 		console.log("pas de marekre");
 		markertest.setVisible(true);
 	}

 		
 });

 $("#addarea").click(function() {
 	//console.log("add area");
 	console.log($('#rayon').val());
 	$.ajax({
 	       url : 'createArea/'+id_user+'/'+markertest.getPosition().lat()+'/'+markertest.getPosition().lng()+'/'+$('#areaname').val()+'/'+$('#labelarea').val()+'/'+$('#rayon').val(),
 	       type : 'POST',
 	       encoding:"UTF-8",
 	       async: true,
 	       success : function(data){  
 	    	  window.location="profil";
 	       }
 	    });
 });




 function getArea(map){
 	
 	$.ajax({
 	       url : 'getArea/'+'${token}',
 	       type : 'POST',
 	       encoding:"UTF-8",
 	       async: true,
 	       success : function(data){
 	    	   //alert(data);
 	    	   console.log(data['area'].length);
 	    	   var length=data['area'].length;

 	    	   for (var i = 0; i < length; i++) {
 	    		   id_area=data['area'][i]['id_area'];
 	    		   latitude=data['area'][i]['latitude'];
 	    		   longitude=data['area'][i]['longitude'];
 	    		   distance=data['area'][i]['distance'];
 	    		   nameArea=data['area'][i]['name_area'];
 	    		   labelArea=data['area'][i]['label_area'];
 	    		   //console.log("latitude : "+ data['area'][i]['latitude']);
 	    		   //console.log("longitude : "+ data['area'][i]['longitude']);
 	    		   var myLatlng = new google.maps.LatLng(latitude,longitude);
 	    		   listMarker[i]=createMarker(myLatlng,"m"+i,map,nameArea,labelArea,id_area,latitude,longitude,distance);
 	    		   
 	    		   $('#table_area tr:last').after('<tr><td>'+nameArea+'</td>'+'<td>'+labelArea+'</td>'+'<td><button type="button" class="btn btn-primary" onclick=deleteArea('+id_area+')>delete marker</button></td>'+'</tr>');
 	    	   }
 	    	   
 	    	   
 	       }
 	    });

 	
 }

 function deleteArea(id){
 	
 	$.ajax({
 	       url : 'deleteArea/'+id,
 	       type : 'POST',
 	       encoding:"UTF-8",
 	       async: true,
 	       success : function(data){
 	    	   alert("La zone a bien été supprimé");
 	    	  window.location="profil";
 	       }
 	    });
 }

 function createMarker(pos, t,map,title,description,id,latitude,longitude,distance) {
     var marker = new google.maps.Marker({       
         position: pos, 
         map: map,  // google.maps.Map 
         title: t      
     }); 
     

     var contentString = '<a href="detailObject.action?id='+id+'"><div id="content" class="myInfoWindow">'+
       '<div id="siteNotice">'+
       '</div>'+
       '<h1 id="firstHeading" class="firstHeading">'+title+'</h1>'+
       '<div id="bodyContent">'+
       '<p>'+description+'</p>'+
       '</div>'+
       '</div>';
     
     var cityCircle = new google.maps.Circle({
         strokeColor: '#FF0000',
         strokeOpacity: 0.8,
         strokeWeight: 2,
         fillColor: '#FF0000',
         fillOpacity: 0.35,
         map: map,
         center: pos,
         radius: distance
       });
     

   var infowindow = new google.maps.InfoWindow({
       content: contentString
   });
   
     google.maps.event.addListener(marker, 'click', function() { 
     	infowindow.open(map,marker); 
     }); 
     return marker;  
 }