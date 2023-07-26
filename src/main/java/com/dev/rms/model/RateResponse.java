package com.dev.rms.model;

import com.dev.rms.entity.Rate;

import lombok.Builder;
import lombok.Data;



@Builder
@Data
public class RateResponse {
	
	private Rate rate;
	private VatSurcharge surCharge;

	// pojo for rate
	

}
