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
	// http://localhost:8080/SpringMVC/getArea/{token}
    @RequestMapping(value = "/getArea/{token}")
    public List<Area> getAllAreaByUser(@PathVariable("token") String token) throws ClassNotFoundException, SQLException
    {
    	
    	//creation area en local
    	 ApplicationContext context = 
                 new ClassPathXmlApplicationContext("Beans.xml");
    	 
    	 // on retrouve l'id_user qui correspond au token recu
    	 UserJDBCTemplate userJDBCTemplate = 
          	      (UserJDBCTemplate)context.getBean("userJDBCTemplate");
    	 Integer id_user = userJDBCTemplate.getUser(token).getId_user();
    	 
    	 
    	 // a partir de l'id_user on retrouve la liste des zones associées a cet id
    	 AreaJDBCTemplate areaJDBCTemplate = 
       	      (AreaJDBCTemplate)context.getBean("areaJDBCTemplate");
    	 List<Area> listArea =areaJDBCTemplate.listAreaByIdUser(id_user);
         
        return listArea;
    }
 
}