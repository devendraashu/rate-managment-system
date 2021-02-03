package com.dev.rms.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.rms.configuration.MyClientConfiguration;
import com.dev.rms.model.VatSurcharge;

@FeignClient(name = "vat-surcharge", url = "https://surcharge.free.beeceptor.com", configuration = MyClientConfiguration.class)
public interface SurchargeClient {

	@RequestMapping("/surcharge")
	public VatSurcharge findSurcharge();

}
