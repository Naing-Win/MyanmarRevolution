package com.nw.revolution.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nw.revolution.model.Hero;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer> {

	@Query(value = "select * from hero where revolution_hero_name like %?1% or division like %?1% or state_name like %?1% or village_or_ward_name like %?1%", nativeQuery = true)
	public Page<Hero> findHeroByKeyword(String keyword, Pageable pageable);
}
