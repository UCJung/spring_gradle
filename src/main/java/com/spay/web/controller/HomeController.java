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
	
	@RequestMapping(value="/jsp", method=RequestMethod.GET )
	public String homeJsp(Model model) {
		String name = "welcom spring mvp";
		
		model.addAttribute("count", category.getTotalCount());
		model.addAttribute("name", name);
		return "home";
	}
	
	@RequestMapping(value="/freemarker", method=RequestMethod.GET )
	public String homeFreemarker(Model model) {
		String name = "welcom spring mvp";
		
		model.addAttribute("count", category.getTotalCount());
		model.addAttribute("name", name);
		return "sample";
	}	
}
