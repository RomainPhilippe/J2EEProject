package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import dao.UserJDBCTemplate;
import model.Authentification;
import model.Notification;
import model.User;

//controller principal
@Controller
@SessionAttributes({ "token", "id_user" })
public class MainController {

	ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	List<Notification> listNotification = null;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		return new ModelAndView("home", "command", new Authentification());
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public ModelAndView executeLogin() {
		System.out.println("home");
		return new ModelAndView("profil");
	}

	@RequestMapping(value = "/authentification", method = RequestMethod.POST)
	public ModelAndView addStudent(@ModelAttribute("SpringWeb") Authentification use, ModelMap model) {
		try {
			UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("userJDBCTemplate");
			User user = userJDBCTemplate.identificationParent2(use.getEmail(), use.getPassword());
			if (user.getToken() != null) {
				ModelAndView modelAndView = new ModelAndView("profil");
				System.out.println("User Login Successful");
				modelAndView.addObject("token", user.getToken());
				modelAndView.addObject("id_user", user.getId_user());
				return modelAndView;
			} else {
				return new ModelAndView("home", "command", new Authentification());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("home", "command", new Authentification());

	}

	@RequestMapping(value = "/profil", method = RequestMethod.GET)
	public ModelAndView profil() {
		ModelAndView model = new ModelAndView("profil");

		return model;
	}

	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public ModelAndView redirect(WebRequest request, HttpSession session, SessionStatus status) {
		status.setComplete();
		request.removeAttribute("token", WebRequest.SCOPE_SESSION);
		return new ModelAndView("home", "command", new Authentification());
	}

}