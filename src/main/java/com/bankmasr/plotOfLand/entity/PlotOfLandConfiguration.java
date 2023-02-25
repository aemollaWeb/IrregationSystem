package com.bankmasr.plotOfLand.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

import com.bankmasr.plotOfLand.enums.IrrigationStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="plotOfLandConfiguration")
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
@Getter
@Setter
public class PlotOfLandConfiguration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	private LocalDateTime fromTimestamp; 
	private LocalDateTime toTimestamp; 
	private double waterAmount;
	private IrrigationStatus status ;
	private String sensorId;
	
	@ManyToOne
	@JsonBackReference
	private PlotOfLand plotOfLand; 

}
