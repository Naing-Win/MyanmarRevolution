package com.nw.revolution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nw.revolution.model.CasesInMyanmarDuringCoup;

@Repository
public interface CasesInMyanmarDuringCoupRepository extends JpaRepository<CasesInMyanmarDuringCoup, Integer> {

}
