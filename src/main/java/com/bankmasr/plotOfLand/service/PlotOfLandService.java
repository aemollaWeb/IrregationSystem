package com.bankmasr.plotOfLand.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bankmasr.plotOfLand.dto.PlotOfLandConfigurationDTO;
import com.bankmasr.plotOfLand.dto.PlotOfLandDTO;
import com.bankmasr.plotOfLand.entity.PlotOfLand;

import io.github.resilience4j.retry.annotation.Retry;

public interface PlotOfLandService {

	PlotOfLandDTO savePlotOfLand(PlotOfLandDTO plotOfLandDTO);

	PlotOfLandConfigurationDTO savePlotOfLand(PlotOfLandConfigurationDTO plotOfLandConfigurationDTO);

	Page<PlotOfLand> getAllPlotOfLand(Pageable pageable);

	//	@CircuitBreaker(name ="myservice",fallbackMethod = "getAllAvailableProducts")
	Boolean irrigate(int configId);

}