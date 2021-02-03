package com.dev.rms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.rms.entity.Rate;
import com.dev.rms.exception.RateNotFoundException;
import com.dev.rms.model.VatSurcharge;
import com.dev.rms.repository.RateRepository;
import com.dev.rms.repository.SurchargeClient;

@Service
public class RateService {
	
	@Autowired
	private RateRepository rateRepostory ;
	
	@Autowired
	private SurchargeClient client;

	public Rate addRate(Rate rate) {
		
		return rateRepostory.save(rate);
		
	}

	public Optional<Rate> findRateById(Long rateId) {
		
		return rateRepostory.findByRateId(rateId);
		 
		
	}

	public void deleteById(Long rateId) {
		
		 rateRepostory.deleteById(rateId);
		
	}
	
   public VatSurcharge findSurCharges() {
		
		return client.findSurcharge();
   }

}
