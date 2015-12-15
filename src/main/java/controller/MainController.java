package controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import dao.NotificationJDBCTemplate;
import model.Notification;

//controler principal
@Controller
public class MainController {
	
		List<Notification> listNotification=null;
	
		@RequestMapping(value = "/home", method = RequestMethod.GET)
		public String home(ModelMap model) {
			return "home";
		}
		
		@RequestMapping(value = "/profil", method = RequestMethod.GET)
			public ModelAndView profil() 
			{
				List<Notification> list = getList();
				//return back to profil.jsp
				ModelAndView model = new ModelAndView("profil");
				model.addObject("listNotif", list);
			
				return model;
			}

		private List<Notification> getList() {

			List<Notification> list = new ArrayList<Notification>();
			
			//creation area en local
			Integer id_user=1;
	    	ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			NotificationJDBCTemplate notificationJDBCTemplate = (NotificationJDBCTemplate)context.getBean("notificationJDBCTemplate");
	    	
			listNotification= notificationJDBCTemplate.listNotificationByIdUser(id_user);

			return listNotification;

		}
		
//		@RequestMapping(value = "/authentification", method = RequestMethod.GET)
//		public String authentification(ModelMap model) {
//			//requete dao #identificationParent
//			model.addAttribute("msg", "JCG Hello World!");
//			if (true){	
//			return "profil"
//			}	
//		
//			return "home";
//		}
		
		
		
	}