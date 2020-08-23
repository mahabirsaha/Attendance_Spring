package com.example.demo;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.*;

@Controller
public class LoginController {
	
	@Autowired
	EmployeeRepository erepo;
	
	@GetMapping("/redirect")
	public String redirect(@RequestParam(name="id", required = true, defaultValue = "0")String id, @RequestParam(name="password", required = true, defaultValue = "Edureka")String password , Model model) {
		model.addAttribute("id", id);
		model.addAttribute("password", password);
		
		int x = Integer.valueOf(id);
		
		Employee firste = Employee.builder()
				.id(x)
				.password(password)
				.build();
		
		erepo.save(firste);
		return "redirect";
	}
		
	@GetMapping("/login")
	public String login(Model model){	
		return "login";
	}
	
	
	@GetMapping("/home")
	public  String home(@RequestParam(name="id", required = true, defaultValue = "0")String id, @RequestParam(name="password", required = true, defaultValue = "Edureka")String password , Model model)
	{
		model.addAttribute("id", id);
		model.addAttribute("password", password);
		
		int x= Integer.valueOf(id);
		
		Optional<Employee> firste = erepo.findById(x);
		
		if((firste.isPresent()))
			return "home";
		else 
			return "login";
	}
	
	@GetMapping("/signup")
	public String signup(Model model)
	{
		return "signup";
	}

}


