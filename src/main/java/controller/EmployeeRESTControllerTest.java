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
public class EmployeeRESTControllerTest
{
	
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	 static final String DB_URL = "jdbc:mysql://projets-tomcat.isep.fr:80/museum";

	 //http://projets-tomcat.isep.fr:80/museum
	 //jdbc:mysql://localhost:3306/localizonles
	 
	 //  Database credentials
	 static final String USER = "eleve1";
	 static final String PASS = "eleve1";


	 
	 
    @RequestMapping(value = "/employees")
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
         
//    	 Connection conn = null;
//    	 Statement stmt = null;
//         Class.forName(JDBC_DRIVER);
//
//         //STEP 3: Open a connection
//         System.out.println("Connecting to database...");
//         conn = DriverManager.getConnection(DB_URL,USER,PASS);
//
//         //STEP 4: Execute a query
//         System.out.println("Creating statement...");
//         stmt = conn.createStatement();
//         
//         String sql = "insert into area (id_user, name_area,latitude, longitude, distance) values (2, toto22,3,3,3)";
//         stmt.executeUpdate(sql);
         
        return employees;
    }
     //http://localhost:8080/SpringMVC/employees
    @RequestMapping(value = "/employees/{id}")
    public ResponseEntity<UserBean> getEmployeeById (@PathVariable("id") int id)
    {
        if (id <= 3) {
        	UserBean employees = new UserBean("e",String.valueOf(id));
            return new ResponseEntity<UserBean>(employees, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
}