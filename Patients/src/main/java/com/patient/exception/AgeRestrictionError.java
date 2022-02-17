package com.patient.exception;

public class AgeRestrictionError extends RuntimeException {

	private static final long serialVersionUID = 1L;

    public AgeRestrictionError(String str) {
	
    	super(str);
    }
}
