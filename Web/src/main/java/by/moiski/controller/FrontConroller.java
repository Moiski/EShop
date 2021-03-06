package by.moiski.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FrontConroller {
	
	@RequestMapping(value="/main", method = RequestMethod.GET)
	public String showMainPage(Model model){
		return "main";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout (HttpSession session){
		session.invalidate();
		return "main";
	}

}
