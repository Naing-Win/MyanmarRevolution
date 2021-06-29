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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nw.revolution.common.FileStorageCommon;
import com.nw.revolution.model.CasesInMyanmarDuringCoup;
import com.nw.revolution.service.CasesInMyanmarDuringCoupServiceImpl;

@Controller
@RequestMapping("/case")
public class CasesInMyanmarDuringCoupController {
	
	@Autowired
	private CasesInMyanmarDuringCoupServiceImpl casesInMyanmarDuringCoupService;
	
	@GetMapping("/list")
	private String casesInMyanmar(Model model, String keyword) {
		/*
		if (keyword != null) {
			model.addAttribute("cases", casesInMyanmarDuringCoupService.findByKeyword(keyword));
		} else {
			model.addAttribute("cases", casesInMyanmarDuringCoupService.findAll());
		}
		return "case/list";
		*/
		return getPaginated(1, "caseTitle", "asc", keyword, model);
	}
	
	@GetMapping("/create")
	public String showCaseForm(Model model) {
		model.addAttribute("coup", new CasesInMyanmarDuringCoup());
		return "case/create";
	}
	
	@PostMapping("/create")
	public String processCaseForm(@RequestParam("file") MultipartFile file, @Valid @ModelAttribute("coup") CasesInMyanmarDuringCoup coup, BindingResult result) throws IOException {
		if (result.hasErrors()) {
			return "case/create";
		}
		String fileName = file.getOriginalFilename();
		coup.setImageForCase(fileName);
		CasesInMyanmarDuringCoup savedCase = casesInMyanmarDuringCoupService.save(coup);
		FileStorageCommon.fileStorage(file, savedCase.getId(), savedCase);
		/*
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
		*/
		return "redirect:/case/list";
	}
	
	@GetMapping("/detail/{id}")
	public String detailCaseById(@PathVariable int id, Model model) {
		model.addAttribute("case", casesInMyanmarDuringCoupService.detailCaseById(id));
		return "case/detail";
	}
	
	@GetMapping("/edit/{id}")
	public String editCaseForm(@PathVariable int id, Model model) {
		model.addAttribute("coup", casesInMyanmarDuringCoupService.detailCaseById(id));
		return "case/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String processEditCaseForm(@RequestParam("file") MultipartFile file, @PathVariable int id, CasesInMyanmarDuringCoup caseInMyanmar) throws IOException {
		String fileName = file.getOriginalFilename();
		CasesInMyanmarDuringCoup tempCase = casesInMyanmarDuringCoupService.detailCaseById(id);
		tempCase.setId(id);
		tempCase.setCaseTitle(caseInMyanmar.getCaseTitle());
		tempCase.setCityOrState(caseInMyanmar.getCityOrState());
		tempCase.setDateOfCase(caseInMyanmar.getDateOfCase());
		tempCase.setDescription(caseInMyanmar.getDescription());
		tempCase.setDivision(caseInMyanmar.getDivision());
		tempCase.setImageForCase(fileName);
		tempCase.setVillageOrWard(caseInMyanmar.getVillageOrWard());
		CasesInMyanmarDuringCoup savedCase = casesInMyanmarDuringCoupService.save(tempCase);
		FileStorageCommon.fileStorage(file, savedCase.getId(), savedCase);
		/*
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
		*/
		return "redirect:/case/list";
	}
	
	/*
	@GetMapping("/search")
	public String findCasesByKeyword(Model model, @Param("keyword") String keyword) {
		if (keyword == null) {
			model.addAttribute("cases", casesInMyanmarDuringCoupService.findAll());
		} else {
			model.addAttribute("cases", casesInMyanmarDuringCoupService.findByKeyword(keyword));
			model.addAttribute("keyword", keyword);
		}
		return "case/list";
	}
	*/
	
	@GetMapping("/page/{pageNo}")
	public String getPaginated(@PathVariable("pageNo") int pageNo, @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir, @Param("keyword") String keyword, Model model) {
		int pageSize = 4;
		Page<CasesInMyanmarDuringCoup> page = casesInMyanmarDuringCoupService.getPaginated(pageNo, pageSize, sortField, sortDir);
		List<CasesInMyanmarDuringCoup> cases = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		if (keyword != null) {
			model.addAttribute("cases", casesInMyanmarDuringCoupService.findByKeyword(keyword, PageRequest.of(pageNo - 1, pageSize)));
		} else {
			model.addAttribute("cases", cases);
		}
		return "case/list";
	}
}
