package com.bankmasr.plotOfLand.dto;

import java.time.LocalDateTime;

import com.bankmasr.plotOfLand.enums.IrrigationStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlotOfLandConfigurationDTO {
	private int id; 
	@JsonIgnore
	private int  plotId; 
	private LocalDateTime fromTimestamp; 
	private LocalDateTime toTimestamp; 
	private double waterAmount;
	private IrrigationStatus status ;
	private String sensorId;
}