package com.dev.rms.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.rms.entity.Rate;
import com.dev.rms.exception.RateNotFoundException;
import com.dev.rms.model.RateResponse;
import com.dev.rms.model.VatSurcharge;
import com.dev.rms.service.RateService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@RestController
@RequestMapping("/rms")
public class RMSController {
	Logger logger = LoggerFactory.getLogger(RMSController.class);

	private RateService rateService;
	
	@Autowired
	public RMSController(RateService rateService) {
		this.rateService = rateService;
	}

	@PostMapping("/rate/")
	public Rate saveRate(@RequestBody Rate rate) {

		logger.info("inside saveRate");
		return rateService.addRate(rate);
	}

	@GetMapping("/rate/{id}")
	@CircuitBreaker(name = "surcharge", fallbackMethod = "fallbackForSurcharge")
	public RateResponse findRateById(@PathVariable("id") Long rateId) {
		
		logger.info("inside findRateById");

		Optional<Rate> findRateById = rateService.findRateById(rateId);

		if (findRateById.isEmpty()) {
			throw new RateNotFoundException(rateId);
		}
		
		VatSurcharge surCharge= rateService.findSurCharges();//

		return RateResponse.builder().rate(findRateById.get()).surCharge(surCharge).build();

	}

	@PutMapping("/rate/{id}")
	public Rate updateRate(@RequestBody Rate rate, @PathVariable("id") Long rateId) {

		Optional<Rate> findRateById = rateService.findRateById(rateId);

		if (findRateById.isEmpty()) {
			return rateService.addRate(rate);
		} else {

			Rate updatedRate = Rate.builder().rateId(findRateById.get().getRateId()).amount(rate.getAmount())
					.rateDescription(rate.getRateDescription()).rateEffectiveDate(rate.getRateEffectiveDate())
					.rateExpirationDate(rate.getRateExpirationDate()).build();

			return rateService.addRate(updatedRate);

		}

	}

	@DeleteMapping("/rate/{id}")
	void deleteEmployee(@PathVariable("id") Long rateId) {

		Optional<Rate> findRateById = rateService.findRateById(rateId);

		if (findRateById.isEmpty()) {
			throw new RateNotFoundException(rateId);
		}

		rateService.deleteById(rateId);
	}

}
