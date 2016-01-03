package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dao.AreaJDBCTemplate;
import dao.UserJDBCTemplate;
import model.Area;
import model.ListArea;
import model.User;

@RestController
public class AreaRESTController
{
	
	// pour appeler l'api 
	// http://localhost:8080/SpringMVC/getArea/HJBUIB688G8G8
    @RequestMapping(value = "/getArea/{token}")
    public ResponseEntity<ListArea> getAllAreaByUser(@PathVariable("token") String token) throws ClassNotFoundException, SQLException
    {
    	
    	//creation area en local
    	 ApplicationContext context = 
                 new ClassPathXmlApplicationContext("Beans.xml");
    	 
    	 // on retrouve l'id_user qui correspond au token recu
    	 UserJDBCTemplate userJDBCTemplate = 
          	      (UserJDBCTemplate)context.getBean("userJDBCTemplate");
    	 Integer id_user = userJDBCTemplate.getUser(token).getId_user();
    	 
    	 List<Area> listArea=null;
    	 
    	 if(id_user!=null){ 
    	
    	 // a partir de l'id_user on retrouve la liste des zones associ�es a cet id
    	 AreaJDBCTemplate areaJDBCTemplate = 
       	      (AreaJDBCTemplate)context.getBean("areaJDBCTemplate");
    	 listArea=areaJDBCTemplate.listAreaByIdUser(id_user);
    	 return new ResponseEntity<ListArea>(new ListArea(listArea), HttpStatus.OK);
    	 
    	 }else{
    		 System.out.println("id_user not found");
    		 
    	 }
    	 return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    
    //  http://localhost:8080/SpringMVC/createArea/1/12.22/23.1/test/test/12
    @RequestMapping(value = "/createArea/{idUser}/{lat}/{lon}/{nameArea}/{labelArea}/{rayon}")
    public ResponseEntity<String> createArea(@PathVariable("idUser") Integer idUser,
    		@PathVariable("lat") Float lat,@PathVariable("lon") Float lon,
    		@PathVariable("nameArea") String nameArea,@PathVariable("labelArea") String labelArea,
    		@PathVariable("rayon") Double rayon
    		) throws ClassNotFoundException, SQLException
    {
    	
    	//creation area en local
    	 ApplicationContext context = 
                 new ClassPathXmlApplicationContext("Beans.xml");
    	 
    	 
    	 List<Area> listArea=null;
    	 
    	 if(idUser!=null){ 
    	
    	 // a partir de l'id_user on retrouve la liste des zones associ�es a cet id
    	 AreaJDBCTemplate areaJDBCTemplate = 
       	      (AreaJDBCTemplate)context.getBean("areaJDBCTemplate");
    	 areaJDBCTemplate.create(idUser, nameArea, labelArea, lat, lon, rayon);
    	 return new ResponseEntity<String>(HttpStatus.OK);
    	 
    	 }else{
    		 System.out.println("id_user not found");
    		 
    	 }
    	 return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    
 // pour appeler l'api 
 	// http://localhost:8080/SpringMVC/deleteArea/quelquechose
     @RequestMapping(value = "/deleteArea/{id_area}")
     public ResponseEntity deleteArea(@PathVariable("id_area") Integer id_area) throws ClassNotFoundException, SQLException
     {
     	
     	//creation area en local
     	 ApplicationContext context = 
                  new ClassPathXmlApplicationContext("Beans.xml");
     	 
     	 AreaJDBCTemplate areaJDBCTemplate = 
          	      (AreaJDBCTemplate)context.getBean("areaJDBCTemplate");
     	 
     	areaJDBCTemplate.deleteArea(id_area);
     	
     	 return new ResponseEntity(HttpStatus.OK);

     }
 
}