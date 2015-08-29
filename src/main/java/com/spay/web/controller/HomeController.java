package com.spay.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value="/", method=RequestMethod.GET )
	public String home(Model model) {
		String name = "welcom spring mvp";
		
		model.addAttribute("name", name);
		return "home";
	}
}
