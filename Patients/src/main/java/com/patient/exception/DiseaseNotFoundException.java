package com.patient.exception;

public class DiseaseNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DiseaseNotFoundException(String message) {
		super(message);
		
	}

	
}
