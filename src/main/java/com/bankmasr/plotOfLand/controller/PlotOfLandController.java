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
import com.bankmasr.plotOfLand.service.PlotOfLandService;

@RestController
//@RequestMapping("/plotOfLand")
public class PlotOfLandController {
	
	@Autowired
	private PlotOfLandService plotOfLandService; 
	
	@PostMapping("/plotOfLand")
	public ResponseEntity<PlotOfLandDTO> addPlotOfLand(@RequestBody PlotOfLandDTO plotOfLand) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(plotOfLandService.savePlotOfLand(plotOfLand))  ;
	}
	
	@PostMapping("/configure/{plotId}")
	public ResponseEntity<PlotOfLandConfigurationDTO> configurePlotOfLand(@RequestBody PlotOfLandConfigurationDTO plotOfLandConfigurationDTO, @PathVariable int plotId) {
		
		plotOfLandConfigurationDTO.setPlotId(plotId);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(plotOfLandService.savePlotOfLand(plotOfLandConfigurationDTO)); 
	}
	
	@GetMapping("/plotOfLand")
	public ResponseEntity<Page<PlotOfLand>> getAllPlotOfLand(@RequestParam(name="pageNo",defaultValue = "0") int pageNo ,
			@RequestParam (name="pageSize",defaultValue = "5")  int pageSize ) {
		
		Pageable pageable =(Pageable) PageRequest.of(pageNo, pageSize);
		
		return ResponseEntity.status (HttpStatus.FOUND).body( plotOfLandService.getAllPlotOfLand(pageable));
	}

	
	@PutMapping("/plotOfLand/{id}")
	public  ResponseEntity<PlotOfLandDTO> updatePlotOfLand(@RequestBody PlotOfLandDTO plotOfLand ,@PathVariable int id ) {
		
		plotOfLand.setId(id);
		return ResponseEntity.status(HttpStatus.OK).body( plotOfLandService.savePlotOfLand(plotOfLand));
	}
	
	@PutMapping("/configure/{id}")
	public  ResponseEntity<PlotOfLandConfigurationDTO> updatePlotOfLandConfig(@RequestBody PlotOfLandConfigurationDTO plotOfLandConfigurationDTO ,@PathVariable int id ) {
		
		plotOfLandConfigurationDTO.setId(id);
		return ResponseEntity.status(HttpStatus.OK).body(plotOfLandService.savePlotOfLand(plotOfLandConfigurationDTO));
		
	}



}
