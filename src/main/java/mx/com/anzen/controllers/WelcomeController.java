package mx.com.anzen.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String printWolcome(ModelMap model)
	{
		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "hello";
	}

}
