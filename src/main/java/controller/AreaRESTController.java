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
import model.User;

@RestController
public class AreaRESTController
{
	
	// pour appeler l'api 
	// http://localhost:8080/SpringMVC/getArea/HJBUIB688G8G8
    @RequestMapping(value = "/getArea/{token}")
    public ResponseEntity<List<Area>> getAllAreaByUser(@PathVariable("token") String token) throws ClassNotFoundException, SQLException
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
    	 return new ResponseEntity<List<Area>>(listArea, HttpStatus.OK);
    	 
    	 }else{
    		 System.out.println("id_user not found");
    		 
    	 }
    	 return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
 
}