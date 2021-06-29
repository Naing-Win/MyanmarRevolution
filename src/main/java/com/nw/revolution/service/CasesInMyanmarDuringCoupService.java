package com.nw.revolution.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nw.revolution.model.CasesInMyanmarDuringCoup;

public interface CasesInMyanmarDuringCoupService {
	
	public CasesInMyanmarDuringCoup save(CasesInMyanmarDuringCoup casesInMyanmarDuringCoup);
	
	public List<CasesInMyanmarDuringCoup> findAll();
	
	public CasesInMyanmarDuringCoup detailCaseById(int id);
	
	public Page<CasesInMyanmarDuringCoup> findByKeyword(String keyword, Pageable pageable);

	Page<CasesInMyanmarDuringCoup> getPaginated(int pageNo, int pageSize, String sortField, String sortDir);

}
