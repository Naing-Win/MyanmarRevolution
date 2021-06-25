package com.nw.revolution.service;

import java.util.List;

import com.nw.revolution.model.CasesInMyanmarDuringCoup;

public interface CasesInMyanmarDuringCoupService {
	
	public CasesInMyanmarDuringCoup save(CasesInMyanmarDuringCoup casesInMyanmarDuringCoup);
	
	public List<CasesInMyanmarDuringCoup> findAll();
	
	public CasesInMyanmarDuringCoup detailCaseById(int id);
	
	public List<CasesInMyanmarDuringCoup> findByKeyword(String keyword);

}
