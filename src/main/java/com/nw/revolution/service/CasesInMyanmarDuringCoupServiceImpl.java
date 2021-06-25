package com.nw.revolution.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nw.revolution.model.CasesInMyanmarDuringCoup;
import com.nw.revolution.repository.CasesInMyanmarDuringCoupRepository;

@Service
public class CasesInMyanmarDuringCoupServiceImpl implements CasesInMyanmarDuringCoupService {

	@Autowired
	private CasesInMyanmarDuringCoupRepository casesInMyanmarDuringCoupRepository;

	@Override
	public CasesInMyanmarDuringCoup save(CasesInMyanmarDuringCoup casesInMyanmarDuringCoup) {
		// TODO Auto-generated method stub
		return casesInMyanmarDuringCoupRepository.save(casesInMyanmarDuringCoup);
	}

	@Override
	public List<CasesInMyanmarDuringCoup> findAll() {
		// TODO Auto-generated method stub
		return casesInMyanmarDuringCoupRepository.findAll();
	}

	@Override
	public CasesInMyanmarDuringCoup detailCaseById(int id) {
		// TODO Auto-generated method stub
		return casesInMyanmarDuringCoupRepository.getById(id);
	}

	@Override
	public List<CasesInMyanmarDuringCoup> findByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return casesInMyanmarDuringCoupRepository.findByKeyword(keyword);
	}

}
