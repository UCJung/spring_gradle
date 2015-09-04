package com.spay.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spay.module.CategoryMapper;

@Controller
public class HomeController {
	
	@Autowired
	private CategoryMapper category;
	
	@RequestMapping(value="/jstl", method=RequestMethod.GET )
	public String homeJsp(Model model) {
		String name = "welcom spring mvp";
		
		model.addAttribute("count", category.getTotalCount());
		model.addAttribute("name", name);
		return "jstl";
	}
	
	@RequestMapping(value="/freemarker", method=RequestMethod.GET )
	public String homeFreemarker(Model model) {
		String name = "welcom spring mvp";
		
		model.addAttribute("count", category.getTotalCount());
		model.addAttribute("name", name);
		return "freemarker";
	}	
	
	@RequestMapping(value={ "/tiles", "/tiles.json" }, method=RequestMethod.GET )
	public String homeTiles(Model model) {
		String name = "welcom spring mvp";
		
		model.addAttribute("count", category.getTotalCount());
		model.addAttribute("name", name);
		return "tiles";
	}
	
	@RequestMapping(value="/tiles/test", method=RequestMethod.GET )
	public String homeTilesTest(Model model) {
		String name = "welcom spring mvp";
		
		model.addAttribute("count", category.getTotalCount());
		model.addAttribute("name", name);
		return "tiles/test";
	}			
}
