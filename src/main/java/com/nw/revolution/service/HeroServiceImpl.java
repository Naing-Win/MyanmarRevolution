package com.nw.revolution.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nw.revolution.model.Hero;
import com.nw.revolution.repository.HeroRepository;

@Service
public class HeroServiceImpl implements HeroService {

	@Autowired
	private HeroRepository heroRepository;

	@Override
	public Hero save(Hero hero) {
		// TODO Auto-generated method stub
		return heroRepository.save(hero);
	}

	@Override
	public List<Hero> findAll() {
		// TODO Auto-generated method stub
		return heroRepository.findAll();
	}

	@Override
	public Hero detailHeroById(int id) {
		// TODO Auto-generated method stub
		return heroRepository.getById(id);
	}

	@Override
	public Page<Hero> findHeroByKeyword(String keyword, Pageable pageable) {
		// TODO Auto-generated method stub
		return heroRepository.findHeroByKeyword(keyword, pageable);
	}

	@Override
	public Page<Hero> getPaginated(int pageNo, int pageSize, String sortField, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return heroRepository.findAll(pageable);
	}

}
