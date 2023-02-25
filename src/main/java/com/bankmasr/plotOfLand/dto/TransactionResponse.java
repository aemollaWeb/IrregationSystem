package com.bankmasr.plotOfLand.dto;

import com.bankmasr.plotOfLand.entity.PlotOfLand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
	
	private PlotOfLand plotOfLand ; 
//	private Payment payment;
	private double amount;
	private String transactionId;
	private String message; 

}