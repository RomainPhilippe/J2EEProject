package controller;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
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
public class UserRESTController
{
	private SecureRandom random = new SecureRandom();

	public String nextSessionId() {
		return new BigInteger(100, random).toString(32);
	}


	// pour appeler l'api 
	// http://localhost:8080/SpringMVC/createUser/romain.philippe78@gmail.com/totototot
	@RequestMapping(value = "/createUser/{email}/{password}")
	public ResponseEntity<String> createUser(@PathVariable("email") String email,
			@PathVariable("password") String password) throws ClassNotFoundException, SQLException
	{

		//creation area en local
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("Beans.xml");


		UserJDBCTemplate userJDBCTemplate = 
				(UserJDBCTemplate)context.getBean("userJDBCTemplate");

		String token= nextSessionId();
		System.out.println("token : "+token);

		//check mail exist, return true if there is already a mail
		Boolean mailExist=userJDBCTemplate.checkMailUser(email);

		if(mailExist){
			return new ResponseEntity<String>("adresse email already exist", HttpStatus.OK);
		}else{
			//creation d'un utilisateur
			userJDBCTemplate.createUser(email, password, new  Date(), token);
			return new ResponseEntity<String>(token, HttpStatus.OK);
		}

	}


	// pour appeler l'api 
	// http://localhost:8080/SpringMVC/identificationParent/romain.philippe78@gmail.com/totototot
	@RequestMapping(value = "/identificationParent/{email}/{password}")
	public ResponseEntity<String> identificationParent(@PathVariable("email") String email,
			@PathVariable("password") String password) throws ClassNotFoundException, SQLException
	{

		//creation area en local
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("Beans.xml");


		UserJDBCTemplate userJDBCTemplate = 
				(UserJDBCTemplate)context.getBean("userJDBCTemplate");

		//check mail exist, return true if there is already a mail
		String token=userJDBCTemplate.identificationParent(email,password);

		if(token!=null){
			return new ResponseEntity<String>(token, HttpStatus.OK);
		}else{
			//creation d'un utilisateur
			return new ResponseEntity<String>("UNAUTHORIZED", HttpStatus.OK);
		}

	}
	
	
	// pour appeler l'api 
		// http://localhost:8080/SpringMVC/identificationChildren/HJBUIB688G8G8
		@RequestMapping(value = "/identificationChildren/{token}")
		public ResponseEntity<String> identificationChildren(@PathVariable("token") String token) throws ClassNotFoundException, SQLException
		{

			//creation area en local
			ApplicationContext context = 
					new ClassPathXmlApplicationContext("Beans.xml");


			UserJDBCTemplate userJDBCTemplate = 
					(UserJDBCTemplate)context.getBean("userJDBCTemplate");

			System.out.println("token input : "+token);

			//check mail exist, return true if there is already a mail
			Boolean identificationCorrect=userJDBCTemplate.identificationChildren(token);

			if(identificationCorrect){ // on retourne l'id_user
				return new ResponseEntity<String>("AUTHORIZED", HttpStatus.OK);
			}else{ //sinon on retourne un message d'erreur
				return new ResponseEntity<String>("UNAUTHORIZED", HttpStatus.OK);
			}

		}
}