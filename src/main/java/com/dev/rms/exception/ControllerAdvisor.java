package com.dev.rms.exception;


import java.util.Date;

import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import feign.FeignException;


	
@ControllerAdvice
public class ControllerAdvisor {

@ExceptionHandler(value = { RateNotFoundException.class })
public ResponseEntity<ErrorDetails> handleIRateNotFoundException(RateNotFoundException ex) {
	
	ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
	
	return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.NOT_FOUND);
	
}

@ExceptionHandler(value = { HibernateException.class })
public ResponseEntity<ErrorDetails> handleHibernateException(HibernateException ex) {
	
	ErrorDetails errorDetails = new ErrorDetails(new Date(), "Internal server error. Please contact admin");
	
	return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	
}

@ExceptionHandler(value = { FeignException.class })
public ResponseEntity<ErrorDetails> handleFeignException(FeignException ex) {
	
	ErrorDetails errorDetails = new ErrorDetails(new Date(), "Internal server error. surcharge is not available , Please contact admin");
	
	return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	
}

// Exception handling at common place


}
