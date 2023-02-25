package com.bankmasr.plotOfLand.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankmasr.plotOfLand.dto.PlotOfLandConfigurationDTO;
import com.bankmasr.plotOfLand.dto.PlotOfLandDTO;
import com.bankmasr.plotOfLand.entity.PlotOfLand;
import com.bankmasr.plotOfLand.service.SensorService;

@RestController
//@RequestMapping("/plotOfLand")
public class SensorIController {
	
	@Autowired
	private SensorService sensorService; 
	
	
	
	@PutMapping("/plotOfLand/confguration/{configId}/irrigate")
	public  ResponseEntity<Boolean> irrigate(@PathVariable int configId) {
		
		return ResponseEntity.status(HttpStatus.OK).body(sensorService.irrigate(configId ));
		
	}
}
