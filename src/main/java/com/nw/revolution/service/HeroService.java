package com.nw.revolution.service;

import java.util.List;

import com.nw.revolution.model.Hero;

public interface HeroService {

	public Hero save(Hero hero);
	public List<Hero> findAll();
	public Hero detailHeroById(int id);
	public List<Hero> findHeroByKeyword(String keyword);
}
