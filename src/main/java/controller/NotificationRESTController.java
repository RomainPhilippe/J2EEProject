package controller;

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
import dao.NotificationJDBCTemplate;
import dao.UserJDBCTemplate;
import model.Area;
import model.Notification;
import model.User;

@RestController
public class NotificationRESTController
{

	// pour appeler l'api 
	// http://localhost:8080/SpringMVC/createNotifications/HJBUIB688G8G8/1.8877/8.79988/processing
	@RequestMapping(value = "/createNotifications/{token}/{lat}/{lon}/{state}")
	public ResponseEntity createNotifications(@PathVariable("token") String token,@PathVariable("lat") Float lat
			,@PathVariable("lon") Float lon,@PathVariable("state") String state) throws ClassNotFoundException, SQLException{

		//creation area en local
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("Beans.xml");

		// on retrouve l'id_user qui correspond au token recu
		UserJDBCTemplate userJDBCTemplate = 
				(UserJDBCTemplate)context.getBean("userJDBCTemplate");
		Integer id_user = userJDBCTemplate.getUser(token).getId_user();

		if(id_user!=null){ // si l'id_user existe
			NotificationJDBCTemplate notificationJDBCTemplate= (NotificationJDBCTemplate)context.getBean("notificationJDBCTemplate");
			System.out.println("state : "+state);
			//on r�cup�re la derni�re notification de l'utilisateur
			Notification lastnotifByUser =notificationJDBCTemplate.getLastNotification(id_user);
			
			
			if(state.equals("processing")){ // si la sortie de zone est en cours
				
				System.out.println("notif : "+lastnotifByUser.getFlag_processing().toString());
				if(lastnotifByUser.getFlag_processing()==0){ //si il y a d�ja une notif en cours
					//on update les positions, la date	et le flag_processing =0
					notificationJDBCTemplate.updateNotif(lastnotifByUser.getId_notification(), lat, lon,new Date(), new Integer(0));
				}else{ // on cr�� la notification
					notificationJDBCTemplate.createNotification(id_user, lat, lon, new Date(), new Integer(0));
				}

			}else{ // si la sortie de zone est termin�e
				//on update les positions, la date et le flag_processing =1
				notificationJDBCTemplate.updateNotif(lastnotifByUser.getId_notification(), lat, lon,new Date(), new Integer(1));
			}

			return new ResponseEntity(HttpStatus.OK);
		}else{
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}

	}
}