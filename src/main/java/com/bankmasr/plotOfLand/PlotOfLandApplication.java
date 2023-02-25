package com.bankmasr.plotOfLand;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bankmasr.plotOfLand.dto.PlotOfLandConfigurationDTO;
import com.bankmasr.plotOfLand.entity.PlotOfLandConfiguration;

@SpringBootApplication
public class PlotOfLandApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlotOfLandApplication.class, args);
	}
	
	@Bean
	public ModelMapper getModelMapper() {
		 ModelMapper modelMapper = new ModelMapper();
		    modelMapper.addMappings(
		        new PropertyMap<PlotOfLandConfiguration,PlotOfLandConfigurationDTO>() {
		          @Override
		          protected void configure() {
//		            skip(destination.getId());
		            skip(destination.getPlotId());
//		            skip(destination.getSensorId());
		          }
		        });
		    return modelMapper;
	}

}
