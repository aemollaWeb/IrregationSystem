package com.bankmasr.plotOfLand.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bankmasr.plotOfLand.dto.PlotOfLandConfigurationDTO;
import com.bankmasr.plotOfLand.dto.PlotOfLandDTO;
import com.bankmasr.plotOfLand.entity.PlotOfLand;
import com.bankmasr.plotOfLand.entity.PlotOfLandConfiguration;
import com.bankmasr.plotOfLand.enums.IrrigationStatus;
import com.bankmasr.plotOfLand.exception.PlotConfigNotFoundException;
import com.bankmasr.plotOfLand.exception.PlotNotFoundException;
import com.bankmasr.plotOfLand.repository.PlotOfLandConfigurationRepository;
import com.bankmasr.plotOfLand.repository.PlotOfLandRepository;

import io.github.resilience4j.retry.annotation.Retry;



@Service
public class PlotOfLandServiceImpl implements PlotOfLandService {
	
	//this just to make the sensore service to fail; 
	private static int callNo=0;
	
	@Autowired
	private PlotOfLandRepository plotOfLandRepository; 
	
	@Autowired
	private PlotOfLandConfigurationRepository plotOfLandConfigurationRepository;
	
	@Autowired
	private ModelMapper mapper;
		
	@Override
	public PlotOfLandDTO savePlotOfLand(PlotOfLandDTO plotOfLandDTO) {
		
		if(plotOfLandDTO.getId()!=0) {
			plotOfLandRepository.findById(plotOfLandDTO.getId()).orElseThrow(()->new PlotNotFoundException());
		}

		PlotOfLand plotOfLand = mapper.map(plotOfLandDTO, PlotOfLand.class); 
		
		plotOfLand = plotOfLandRepository.save(plotOfLand);
		
		plotOfLandDTO = mapper.map(plotOfLand, PlotOfLandDTO.class); 
		
		return plotOfLandDTO; 
	}
	
	
	@Override
	public PlotOfLandConfigurationDTO savePlotOfLand(PlotOfLandConfigurationDTO plotOfLandConfigurationDTO) {
		
		PlotOfLandConfiguration plotOfLandConfiguration = null ; 
		
		if(plotOfLandConfigurationDTO.getId()!=0) {
			plotOfLandConfiguration = plotOfLandConfigurationRepository.findById(plotOfLandConfigurationDTO.getId()).orElseThrow(()->new PlotConfigNotFoundException());
			plotOfLandConfigurationDTO.setPlotId(plotOfLandConfiguration.getPlotOfLand().getId());
		}
		
		PlotOfLand land  = plotOfLandRepository.findById(plotOfLandConfigurationDTO.getPlotId()).orElseThrow(()->new PlotNotFoundException());
		
		plotOfLandConfiguration = mapper.map(plotOfLandConfigurationDTO, PlotOfLandConfiguration.class); 
		
		plotOfLandConfiguration.setPlotOfLand(land);
		
		plotOfLandConfiguration = plotOfLandConfigurationRepository.save(plotOfLandConfiguration);
		
		plotOfLandConfigurationDTO = mapper.map(plotOfLandConfiguration, PlotOfLandConfigurationDTO.class); 
		
		return plotOfLandConfigurationDTO; 
	}
	
	@Override
	public Page<PlotOfLand> getAllPlotOfLand(Pageable pageable){
	
		return plotOfLandRepository.findAll(pageable);
	}
	
//	@CircuitBreaker(name ="myservice",fallbackMethod = "getAllAvailableProducts")
	@Override
	@Retry(name = "sensorService",fallbackMethod = "retryFailback")
	public Boolean irrigate(int configId) {
		
		PlotOfLandConfiguration config  = plotOfLandConfigurationRepository.findById(configId).orElseThrow(()->new PlotConfigNotFoundException());
		
		int count = 0;
		boolean irrigationStatus = callSensor(configId, count);
		
		if(irrigationStatus) {
			config.setStatus(IrrigationStatus.SUCCESS);
			plotOfLandConfigurationRepository.save(config);
			
		}
		return true ;
		
	}
	
	// Alerting system to be implemented in case 
	// the sensor not available and after exceeding 
	// the retry times.
	public Boolean retryFailback(Exception e) {
		System.out.println("Call some msg alert system ");
		return false ;
	}
	

	private boolean  callSensor(int id , int count) {
		System.out.println("trying to call sensor retry #: " + count++);
		if ( id%3!=0){
			throw new RuntimeException();
		}
		return true;  
	}
}
