package com.nw.revolution.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nw.revolution.common.FileStorageCommon;
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
	public String indexPage(Model model, String keyword) {
		return getPaginated(1, "revolutionHeroName", "asc", keyword, model);
	}

	@GetMapping("/create")
	public String showHeroForm(Model model) {
		model.addAttribute("hero", new Hero());
		return "hero/create";
	}

	@PostMapping("/create")
	public String processHeroForm(@RequestParam("file") MultipartFile file, @Valid Hero hero, BindingResult result) throws IOException {
		if (result.hasErrors()) {
			return "hero/create";
		}
		String fileName = file.getOriginalFilename();
		hero.setImageForHero(fileName);
		Hero savedHero = heroService.save(hero);
		FileStorageCommon.fileStorage(file, savedHero.getId(), savedHero);
		/*
		String uploadDir = "src/main/resources/static/images/hero/" + savedHero.getId();
		Path uploadPath = Paths.get(uploadDir);
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		try (InputStream inputStream = file.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			// TODO: handle exception
			throw new IOException("Could not save image file: " + fileName, ioe);
		}
		*/
		return "redirect:/hero/list";
	}
	
	@GetMapping("/detail/{id}")
	public String detailHeroById(@PathVariable int id, Model model) {
		model.addAttribute("hero", heroService.detailHeroById(id));
		return "hero/detail";
	}
	
	@GetMapping("/edit/{id}")
	public String editHeroForm(@PathVariable int id, Model model) {
		model.addAttribute("hero", heroService.detailHeroById(id));
		return "hero/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String processEditHeroForm(@PathVariable int id, Hero hero, @RequestParam("file") MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		Hero tempHero = heroService.detailHeroById(id);
		tempHero.setId(id);
		tempHero.setAge(hero.getAge());
		tempHero.setDateOfDead(hero.getDateOfDead());
		tempHero.setDivision(hero.getDivision());
		tempHero.setHowToDead(hero.getHowToDead());
		tempHero.setImageForHero(fileName);
		tempHero.setRevolutionHeroName(hero.getRevolutionHeroName());
		tempHero.setStateName(hero.getStateName());
		tempHero.setVillageName(hero.getVillageName());
		Hero savedHero = heroService.save(tempHero);
		FileStorageCommon.fileStorage(file, savedHero.getId(), savedHero);
		/*
		String uploadDir = "src/main/resources/static/images/hero/" + savedHero.getId();
		Path uploadPath = Paths.get(uploadDir);
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		try (InputStream inputStream = file.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			// TODO: handle exception
			throw new IOException("Could not save image file: " + fileName, ioe);
		}
		*/
		return "redirect:/hero/list";
	}
	
	@GetMapping("/page/{pageNo}")
	public String getPaginated(@PathVariable("pageNo") int pageNo, @RequestParam("sortField") String sortField , @RequestParam("sortDir") String sortDir, @Param("keyword") String keyword, Model model) {
		int pageSize = 4;
        Page<Hero> page = heroService.getPaginated(pageNo, pageSize, sortField, sortDir);
        List<Hero> heros = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc": "asc");
        if (keyword != null) {
			model.addAttribute("heros", heroService.findHeroByKeyword(keyword, PageRequest.of(pageNo - 1, pageSize)));
		} else {
			model.addAttribute("heros", heros);
		}
        return "index";
	}
	
}
