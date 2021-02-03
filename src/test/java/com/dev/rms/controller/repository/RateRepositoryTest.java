package com.dev.rms.controller.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dev.rms.entity.Rate;
import com.dev.rms.repository.RateRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RateRepositoryTest {

	@Autowired
	private RateRepository rateRepository;

	@Test
	public void whenFindByRateId_thenReturnRate() {
		Optional<Rate> rate = Optional.of(Rate.builder().rateDescription("new rate").amount(12L).build());

		rateRepository.save(rate.get());

		Optional<Rate> found = rateRepository.findByRateId(rate.get().getRateId());

		assertThat(found.get().getRateDescription()).isEqualTo(rate.get().getRateDescription());

		assertNotNull(found.get().getRateId());
	}

}
