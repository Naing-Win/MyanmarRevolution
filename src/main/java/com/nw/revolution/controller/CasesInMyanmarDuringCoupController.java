package com.nw.revolution.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nw.revolution.model.CasesInMyanmarDuringCoup;
import com.nw.revolution.service.CasesInMyanmarDuringCoupServiceImpl;

@Controller
@RequestMapping("/case")
public class CasesInMyanmarDuringCoupController {
	
	@Autowired
	private CasesInMyanmarDuringCoupServiceImpl casesInMyanmarDuringCoupService;
	
	@GetMapping("/list")
	private String casesInMyanmar(Model model) {
		model.addAttribute("cases", casesInMyanmarDuringCoupService.findAll());
		return "case/list";
	}
	
	@GetMapping("/create")
	public String showCaseForm(Model model) {
		model.addAttribute("coup", new CasesInMyanmarDuringCoup());
		return "case/create";
	}
	
	@PostMapping("/create")
	public String processCaseForm(@RequestParam("file") MultipartFile file, CasesInMyanmarDuringCoup coup) throws IOException {
		String fileName = file.getOriginalFilename();
		coup.setImageForCase(fileName);
		CasesInMyanmarDuringCoup savedCase = casesInMyanmarDuringCoupService.save(coup);
		String uploadDir = "src/main/resources/static/images/case/" + savedCase.getId();
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
		return "redirect:/case/list";
	}
	
	@GetMapping("/detail/{id}")
	public String detailCaseById(@PathVariable int id, Model model) {
		model.addAttribute("case", casesInMyanmarDuringCoupService.detailCaseById(id));
		return "case/detail";
	}

}
