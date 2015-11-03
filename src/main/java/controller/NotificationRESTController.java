package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dao.AreaJDBCTemplate;
import model.User;

@RestController
public class NotificationRESTController
{
	
	// pour appeler l'api 
	// http://localhost:8080/SpringMVC/getNotifications
    @RequestMapping(value = "/getNotifications")
    public User getAllEmployees() throws ClassNotFoundException, SQLException
    {
    	User employees = new User(new Integer(2),"e","ded", new Date(),"ffe");
    	
    	//creation area en local
    	 ApplicationContext context = 
                 new ClassPathXmlApplicationContext("Beans.xml");
    	 AreaJDBCTemplate areaJDBCTemplate = 
       	      (AreaJDBCTemplate)context.getBean("areaJDBCTemplate");
         areaJDBCTemplate.create(new Integer(3), "toto2", "titi", new Double(3.33), new Double (4.33), new Double (22));
         //
         
        return employees;
    }
}