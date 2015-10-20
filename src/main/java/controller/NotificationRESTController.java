package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dao.AreaJDBCTemplate;
import model.UserBean;

@RestController
public class NotificationRESTController
{
	
	 
    @RequestMapping(value = "/getNotifications")
    public UserBean getAllEmployees() throws ClassNotFoundException, SQLException
    {
    	UserBean employees = new UserBean("e","e");
    	
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