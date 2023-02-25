package com.bankmasr.plotOfLand.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="plotOfLand")
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
@Getter
@Setter
public class PlotOfLand {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	private double area; 
	private String description; 
	
	@OneToMany(mappedBy="plotOfLand",cascade =CascadeType.ALL )
	@JsonManagedReference
	private List<PlotOfLandConfiguration> configuration;
}
