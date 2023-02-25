package com.bankmasr.plotOfLand.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bankmasr.plotOfLand.entity.PlotOfLand;

public interface PlotOfLandRepository extends JpaRepository<PlotOfLand, Integer>{
	
	 Page<PlotOfLand> findAll(Pageable pageable)  ; 

}
