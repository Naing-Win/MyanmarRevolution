package com.nw.revolution.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nw.revolution.model.Hero;
import com.nw.revolution.repository.HeroRepository;

@Service
public class HeroServiceImpl implements HeroService {

	@Autowired
	private HeroRepository heroRepository;

	@Override
	public void save(Hero hero) {
		// TODO Auto-generated method stub
		heroRepository.save(hero);
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

}
