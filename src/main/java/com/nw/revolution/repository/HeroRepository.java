package com.nw.revolution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nw.revolution.model.Hero;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer> {

}
