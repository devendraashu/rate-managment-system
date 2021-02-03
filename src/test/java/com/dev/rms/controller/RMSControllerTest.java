package com.dev.rms.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.hibernate.HibernateException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dev.rms.entity.Rate;
import com.dev.rms.exception.RateNotFoundException;
import com.dev.rms.model.RateResponse;
import com.dev.rms.model.VatSurcharge;
import com.dev.rms.repository.SurchargeClient;
import com.dev.rms.service.RateService;

@RunWith(SpringJUnit4ClassRunner.class)
public class RMSControllerTest {

	@Mock
	private RateService rateService;


	@Mock
	private SurchargeClient client;

	private RMSController rmsController;

	@Before
	public void setup() {

		rmsController = new RMSController(rateService);
		

	}

	@Test
	public void shouldReturnRateData_when_validRateIdisProvided() {

		Optional<Rate> rate = Optional.of(Rate.builder().rateDescription("newrate").amount(12L).rateId(1L).build());
		VatSurcharge vat = VatSurcharge.builder().surchargeDescription("surcharge").surchargeRate("123").build();
      
		RateResponse rateResponse = RateResponse.builder().rate(rate.get()).surCharge(vat).build();
				
				
		when(rateService.findSurCharges())
		.thenReturn(vat);
		
		

		RateResponse findRateById = rmsController.findRateById(1L);
		assertThat(findRateById.getRate().getRateDescription()).isEqualTo("newrate");
		assertThat(findRateById.getRate().getRateEffectiveDate()).isNull();
		assertThat(findRateById.getRate().getRateExpirationDate()).isNull();

		assertThat(findRateById.getSurCharge().getSurchargeDescription()).isEqualTo("surcharge");

	}

	@Test
	public void shoudAdddrate_when_validRateProvided() {

		Rate addRate = Rate.builder().rateDescription("newrate").amount(12L).rateId(1L).build();

		when(rateService.addRate(addRate)).thenReturn(addRate);

		Rate saveRate = rmsController .saveRate(addRate);

		assertThat(saveRate.getRateDescription()).isEqualTo("newrate");
		assertThat(saveRate.getRateEffectiveDate()).isNull();
		assertThat(saveRate.getRateExpirationDate()).isNull();

	}
	
	@Test(expected = HibernateException.class)
	public void shoudThrowExceptions_when_databaserIsDownforAddRate() {

		Rate addRate = Rate.builder().rateDescription("newrate").amount(12L).rateId(1L).build();

		when(rateService.addRate(addRate)).thenThrow(new HibernateException("Internal server error. Please contact admin"));

		rmsController.saveRate(addRate);

	}
	
	@Test
	public void shoudupdateRate_when_validRateIsProvided() {
		
		Rate oldRate = Rate.builder().rateDescription("oldrate").amount(12L).rateId(1L).build();

		Rate updateRate = Rate.builder().rateDescription("newrate").amount(12L).rateId(1L).build();
		
		when(rateService.findRateById(anyLong())).thenReturn(Optional.of(oldRate));

		when(rateService.addRate(updateRate)).thenReturn(updateRate);

		Rate saveRate = rmsController.updateRate(updateRate, updateRate.getRateId());;

		assertThat(saveRate.getRateDescription()).isEqualTo("newrate");
		assertThat(saveRate.getRateEffectiveDate()).isNull();
		assertThat(saveRate.getRateExpirationDate()).isNull();

	}
	@Test(expected = RateNotFoundException.class)
	public void shouldeleteRate_when_validRateIsProvided() {
		
		Rate exitinRate = Rate.builder().rateDescription("oldrate").amount(12L).rateId(1L).build();

		
		when(rateService.findRateById(anyLong())).thenReturn(Optional.of(exitinRate));

		Mockito.doNothing().when(rateService).deleteById(12L);
		rmsController.deleteEmployee(12L);
		
		when(rateService.findRateById(anyLong())).thenThrow(RateNotFoundException.class);

		rateService.findRateById(12L);

	}

}
