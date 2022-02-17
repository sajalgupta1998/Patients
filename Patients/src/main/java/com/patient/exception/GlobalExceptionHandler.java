package com.patient.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DiseaseNotFoundException.class)
	ResponseEntity<?> handleException1(DiseaseNotFoundException exception){
		
		ErrorMessage err=new ErrorMessage(exception.getMessage(), new Date());
		return new ResponseEntity(err,HttpStatus.BAD_REQUEST);
	}
	
//	@ExceptionHandler(DataIsEmpty.class)
//	ResponseEntity<?> handleException5(DataIsEmpty exception){
//		
//		ErrorMessage err=new ErrorMessage(exception.getMessage(), new Date());
//		return new ResponseEntity(err,HttpStatus.OK);
//	}
	
	@ExceptionHandler(AgeRestrictionError.class)
	ResponseEntity<?> handleException3(AgeRestrictionError exception){
		
		ErrorMessage err=new ErrorMessage(exception.getMessage(), new Date());
		return new ResponseEntity(err,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(DataIsEmpty.class)
	ResponseEntity<?> handleException4(AgeRestrictionError exception){
		
		ErrorMessage err=new ErrorMessage(exception.getMessage(), new Date());
		return new ResponseEntity(err,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(Exception.class)
	ResponseEntity<?> handleException2(Exception exception){
		
		ErrorMessage err=new ErrorMessage(exception.getMessage(), new Date());
		return new ResponseEntity(err,HttpStatus.FORBIDDEN);
	}
}
