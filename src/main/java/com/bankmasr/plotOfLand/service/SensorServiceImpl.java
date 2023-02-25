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
public class SensorServiceImpl implements SensorService {
	
	@Autowired
	private PlotOfLandConfigurationRepository plotOfLandConfigurationRepository;

		

	
//	@CircuitBreaker(name ="myservice",fallbackMethod = "getAllAvailableProducts")
	@Override
	@Retry(name = "sensorService",fallbackMethod = "retryFailback")
	public Boolean irrigate(int configId) {
//		try {
		PlotOfLandConfiguration config  = plotOfLandConfigurationRepository.findById(configId).orElseThrow(()->new PlotConfigNotFoundException());
		
		int count = 0;
		boolean irrigationStatus = callSensor(configId, count);
		
		if(irrigationStatus) {
			config.setStatus(IrrigationStatus.SUCCESS);
			plotOfLandConfigurationRepository.save(config);
			
		}
//		}catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
		

		return true ;
	}
	
	
	public Boolean retryFailback(Exception e) {
		System.out.println("failback method called");
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
