package com.dev.rms.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rateId ;
	
	@Column(nullable = false)
	private String rateDescription;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate rateEffectiveDate;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate rateExpirationDate;
	private Long amount;
	
	


}
