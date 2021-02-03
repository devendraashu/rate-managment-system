package com.dev.rms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.rms.entity.Rate;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {

	Optional<Rate> findByRateId(Long rateId);
	
	
}
