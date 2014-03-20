package mx.com.anzen.controllers;

import java.util.ArrayList;
import java.util.List;

import mx.com.anzen.model.User;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsersController {

	@RequestMapping(value = { "/users" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getUses()
	{
		List<User> users = new ArrayList<User>();
		return users;
	}
}
