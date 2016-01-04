package controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Properties;

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

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@RestController
public class UserRESTController
{
	private SecureRandom random = new SecureRandom();
	private String USERNAME_MAIL="romain.philippe78@gmail.com";
	private String PASSWORD_MAIL="";

	public String nextSessionId() {
		return new BigInteger(100, random).toString(32);
	}


	/*
	 * pour appeler l'api:
	 * http://localhost:8080/SpringMVC/createUser/romain.philippe78@gmail.com/totototot
	 * 
	 * */ 
	@RequestMapping(value = "/createUser/{email}/{password}")
	public ResponseEntity<String> createUser(@PathVariable("email") String email,
			@PathVariable("password") String password) throws ClassNotFoundException, SQLException, UnsupportedEncodingException
	{

	//creation area en local
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

		UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate)context.getBean("userJDBCTemplate");

		String token= nextSessionId();
		System.out.println("token : "+token);

	// vérification si le mail exist et return true s'il y a déjà un mail
		Boolean mailExist=userJDBCTemplate.checkMailUser(email);

		if(mailExist){
			return new ResponseEntity<String>("adresse email already exist", HttpStatus.OK);
		}
		
		else{ //creation d'un utilisateur
			userJDBCTemplate.createUser(email, password, new  Date(), token);
			sendMail(email,token);

			return new ResponseEntity<String>(token, HttpStatus.OK);
		}

	}

	/*
	 * pour appeler l'api:
	 * http://localhost:8080/SpringMVC/identificationParent/romain.philippe78@gmail.com/totototot
	 * 
	 * */ 
	@RequestMapping(value = "/identificationParent/{email}/{password}")
	public ResponseEntity<String> identificationParent(@PathVariable("email") String email,
			@PathVariable("password") String password) throws ClassNotFoundException, SQLException
	{

	//creation area en local
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

		UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate)context.getBean("userJDBCTemplate");

	//vérification si le mail exist et return true s'il y a déjà un mail
		
		String token = userJDBCTemplate.identificationParent(email,password);

		if(token!=null){
			return new ResponseEntity<String>(token, HttpStatus.OK);
		}
		else{ //creation d'un utilisateur
			return new ResponseEntity<String>("UNAUTHORIZED", HttpStatus.OK);
		}

	}

	
	/*
	 * pour appeler l'api:
	 * http://localhost:8080/SpringMVC/identificationChildren/HJBUIB688G8G8
	 * 
	 */ 
		@RequestMapping(value = "/identificationChildren/{token}")
		public ResponseEntity<String> identificationChildren(@PathVariable("token") String token) throws ClassNotFoundException, SQLException
		{

		//creation area en local
			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

			UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate)context.getBean("userJDBCTemplate");
			System.out.println("token input : "+token);

		//vérification si le mail exist et return true s'il y a déjà un mail
			Boolean identificationCorrect = userJDBCTemplate.identificationChildren(token);

			if(identificationCorrect){ // on retourne l'id_user
				return new ResponseEntity<String>("AUTHORIZED", HttpStatus.OK);
			}
			else{ //sinon on retourne un message d'erreur
				return new ResponseEntity<String>("UNAUTHORIZED", HttpStatus.OK);
			}

	}

	public void sendMail(String email, String token){

		if(PASSWORD_MAIL!=""){
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(USERNAME_MAIL, PASSWORD_MAIL);
				}
			});

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("from-email@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(email));
				message.setSubject("Welcome to Localizonles");
				message.setText("Dear customer,"
						+ "\n\n You are please to inform you that your profil account is now ready to use! \n Your token is :"+token);

				Transport.send(message);

				System.out.println("email send");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}

	}
}