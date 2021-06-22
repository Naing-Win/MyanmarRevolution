package com.nw.revolution.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nw.revolution.model.Hero;
import com.nw.revolution.service.HeroServiceImpl;

@Controller
@RequestMapping("/hero")
public class HeroController {

	@Autowired
	private HeroServiceImpl heroService;

	/*
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	*/

	@GetMapping("/list")
	public String indexPage(Model model) {
		model.addAttribute("heros", heroService.findAll());
		return "index";
	}

	@GetMapping("/create")
	public String showHeroForm(Model model) {
		model.addAttribute("hero", new Hero());
		return "hero/create";
	}

	@PostMapping("/create")
	public String processHeroForm(@Valid Hero hero, BindingResult result) {
		if (result.hasErrors()) {
			return "hero/create";
		}
		heroService.save(hero);
		return "redirect:/hero/list";
	}
	
	@GetMapping("/detail/{id}")
	public String detailHeroById(@PathVariable int id, Model model) {
		model.addAttribute("hero", heroService.detailHeroById(id));
		return "hero/detail";
	}
	
}
