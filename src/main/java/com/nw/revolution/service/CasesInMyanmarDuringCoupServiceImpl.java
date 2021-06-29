package com.nw.revolution.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public Page<CasesInMyanmarDuringCoup> findByKeyword(String keyword, Pageable pageable) {
		// TODO Auto-generated method stub
		return casesInMyanmarDuringCoupRepository.findByKeyword(keyword, pageable);
	}

	@Override
	public Page<CasesInMyanmarDuringCoup> getPaginated(int pageNo, int pageSize, String sortField, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return casesInMyanmarDuringCoupRepository.findAll(pageable);
	}

}
