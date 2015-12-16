var map;
var initialize;
var listMarker=[];
 var markertest;
var map;
var latitudeMarker;
var longitudeMarker;

function initMap() {
  map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: 48, lng: 2},
    zoom: 8
  });
  
 
  getPosition(map);
}


$( "#addmarker" ).click(function() {
	alert('toto');
	 markertest = new google.maps.Marker({
		    map: map,
		    draggable: true,
		    animation: google.maps.Animation.DROP,
		    position: {lat: 48, lng: 2},
		    icon: './resources/img/marker.png'
		    //icon :goldStar
		  });
	 google.maps.event.addListener(markertest, 'dragend', function (event) {
			latitudeMarker =this.getPosition().lat();
			longitudeMarker=this.getPosition().lng();
		});
});

$("#addarea").click(function() {
	console.log("add area");
	console.log($('#rayon').val());
	$.ajax({
	       url : 'createArea/1/'+latitudeMarker+'/'+longitudeMarker+'/'+$('#areaname').val()+'/'+$('#labelarea').val()+'/'+$('#rayon').val(),
	       type : 'POST',
	       encoding:"UTF-8",
	       async: true,
	       success : function(data){  
	       }
	    });
});




function getPosition(map){
	
	$.ajax({
	       url : 'getArea/HJBUIB688G8G8',
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
	    		   
	    		   $('#table_area tr:last').after('<tr><td>'+nameArea+'</td>'+'<td>'+labelArea+'</td></tr>');
	    	   }
	    	   
	    	   
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

