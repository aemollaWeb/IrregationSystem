package com.bankmasr.plotOfLand.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlotOfLandDTO {
	private int id; 
	private double area; 
	private String description; 
	
	private List<PlotOfLandConfigurationDTO> configuration;


}