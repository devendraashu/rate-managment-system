package com.dev.rms.controller.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.dev.rms.entity.Rate;
import com.dev.rms.exception.RateNotFoundException;
import com.dev.rms.model.VatSurcharge;
import com.dev.rms.repository.RateRepository;
import com.dev.rms.repository.SurchargeClient;
import com.dev.rms.service.RateService;

@RunWith(SpringRunner.class)
public class RateServiceTest {

	@TestConfiguration
	static class RateServiceTestTestContextConfiguration {

		@Bean
		public RateService rateService() {
			return new RateService();
		}
	}

	@Autowired
	private RateService rateService;

	@MockBean
	private RateRepository rateRepository;

	@MockBean
	private SurchargeClient client;

	@Test
	public void rateShouldBeFound_when_validIdGiven() {
		Optional<Rate> rate = Optional
				.of(Rate.builder().rateDescription("updated rate").amount(12L).rateId(1L).build());

		Mockito.when(rateRepository.findByRateId(anyLong())).thenReturn(rate);
		Optional<Rate> found = rateService.findRateById(12L);

		assertThat(found.get().getRateDescription()).isEqualTo("updated rate");
	}

	@Test(expected = RateNotFoundException.class)
	public void rateShouldBeDeleted_when_validIdGiven() {

		Mockito.doNothing().when(rateRepository).deleteById(12L);

		rateService.deleteById(12L);

		Mockito.when(rateRepository.findByRateId(anyLong())).thenThrow(RateNotFoundException.class);

		rateService.findRateById(12L);
	}

	@Test
	public void shouldGetVatSurcharge() {

		Mockito.when(client.findSurcharge())
				.thenReturn(VatSurcharge.builder().surchargeRate("456").surchargeDescription("VAT charges").build());

		VatSurcharge findSurcharge = client.findSurcharge();

		assertThat(findSurcharge.getSurchargeRate()).isEqualTo("456");
		assertThat(findSurcharge.getSurchargeDescription()).isEqualTo("VAT charges");

	}

	
	@Test
	public void rateShouldadd_when_validDataProvided() {
		Optional<Rate> rate = Optional
				.of(Rate.builder().rateDescription("new rate").amount(12L).rateId(1L).build());

		Mockito.when(rateRepository.save(rate.get())).thenReturn(rate.get());
		Rate added = rateService.addRate(rate.get());

		assertThat(added.getRateDescription()).isEqualTo("new rate");
	}
	
}
