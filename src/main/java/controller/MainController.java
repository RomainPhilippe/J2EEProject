package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//controler principal
@Controller
public class MainController {

		@RequestMapping(value = "/home", method = RequestMethod.GET)
		public String hello(ModelMap model) {
			return "home";
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