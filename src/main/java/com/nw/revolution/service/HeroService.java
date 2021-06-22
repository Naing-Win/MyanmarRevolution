package com.nw.revolution.service;

import java.util.List;

import com.nw.revolution.model.Hero;

public interface HeroService {

	public void save(Hero hero);
	public List<Hero> findAll();
	public Hero detailHeroById(int id);
}
