package com.nw.revolution.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nw.revolution.model.CasesInMyanmarDuringCoup;

@Repository
public interface CasesInMyanmarDuringCoupRepository extends JpaRepository<CasesInMyanmarDuringCoup, Integer> {

	//@Query(value = "select * from cases_in_myanmar_during_coup where division like %?1%", nativeQuery = true)
	@Query(value="SELECT * FROM cases_in_myanmar_during_coup WHERE case_title like %:keyword% or division like %:keyword%  or city_or_state like %:keyword%",nativeQuery = true)
	public Page<CasesInMyanmarDuringCoup> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
