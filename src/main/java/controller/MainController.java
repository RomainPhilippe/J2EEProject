package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import dao.NotificationJDBCTemplate;
import dao.UserDao;
import dao.UserJDBCTemplate;
import model.Authentification;
import model.Contact;
import model.Greeting;
import model.Notification;
import model.Student;
import model.User;

//controler principal
@Controller
public class MainController {
	
		List<Notification> listNotification=null;
		
		@RequestMapping(value = "/home", method = RequestMethod.GET)
		public ModelAndView home() {
			return new ModelAndView("home", "command", new Authentification());
		}
		
		@RequestMapping(value="/home",method=RequestMethod.POST)
		public ModelAndView executeLogin()
		{
				System.out.println("home");
				
//				ModelAndView model= null;
//				try
//				{
//					ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
//					UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("userJDBCTemplate");
//						String isValidUser = userJDBCTemplate.identificationParent(loginBean.getMail(), loginBean.getPassword());
//						if(isValidUser != null)
//						{
//								System.out.println("User Login Successful");
//								request.setAttribute("loggedInUser", loginBean.getMail());
//								model = new ModelAndView("profil");
//						}
//						else
//						{
//								model = new ModelAndView("home");
//								request.setAttribute("message", "Invalid credentials!!");
//						}
//
//				}
//				catch(Exception e)
//				{
//						e.printStackTrace();
//				}

				return new ModelAndView("profil");
		}
		
		@RequestMapping(value = "/student", method = RequestMethod.GET)
		   public ModelAndView student() {
		      return new ModelAndView("student", "command", new Authentification());
		      //return "student";
		   }
		   
		   @RequestMapping(value = "/authentification", method = RequestMethod.POST)
		   public ModelAndView addStudent(@ModelAttribute("SpringWeb")Authentification student, 
		   ModelMap model) {
			   System.out.println("name : "+student.getEmail());
			   System.out.println("age : "+student.getPassword());

				try
				{
					ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
					UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("userJDBCTemplate");
						String isValidUser = userJDBCTemplate.identificationParent(student.getEmail(), student.getPassword());
						if(isValidUser != null)
						{
								System.out.println("User Login Successful");
								//request.setAttribute("loggedInUser", loginBean.getMail());
								return new ModelAndView("profil");
						}
						else
						{
							    return new ModelAndView("home", "command", new Authentification());
								//request.setAttribute("message", "Invalid credentials!!");
						}

				}
				catch(Exception e)
				{
						e.printStackTrace();
				}
				return new ModelAndView("home", "command", new Authentification());
		      
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