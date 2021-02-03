package com.dev.rms.exception;


public class RateNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RateNotFoundException(Long id) {

        super(String.format("RateId %d not found in RMS", id));
     ;
    }
    
}