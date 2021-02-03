package com.dev.rms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class VatSurcharge {
	
	public String surchargeRate;
    public String surchargeDescription;
    

}
