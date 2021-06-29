package com.nw.revolution.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nw.revolution.model.Hero;

public interface HeroService {

	public Hero save(Hero hero);
	public List<Hero> findAll();
	public Hero detailHeroById(int id);
	public Page<Hero> findHeroByKeyword(String keyword, Pageable pageable);
	public Page<Hero> getPaginated(int pageNo, int pageSize, String sortField, String sortDir);
}
