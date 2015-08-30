package com.spay.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spay.mapper.CategoryMapper;

@Controller
public class HomeController {
	
	@Autowired
	private CategoryMapper category;
	
	@RequestMapping(value="/", method=RequestMethod.GET )
	public String home(Model model) {
		String name = "welcom spring mvp";
		
		model.addAttribute("count", category.getTotalCount());
		model.addAttribute("name", name);
		return "home";
	}
}
