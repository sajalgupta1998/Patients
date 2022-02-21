package com.patient.exception;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DiseaseNotFoundException.class)
	ResponseEntity<?> handleException1(DiseaseNotFoundException exception){
		
		ErrorMessage err=new ErrorMessage(exception.getMessage(), new Date());
		return new ResponseEntity(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AgeRestrictionError.class)
	ResponseEntity<?> handleException3(AgeRestrictionError exception){
		
		ErrorMessage err=new ErrorMessage(exception.getMessage(), new Date());
		return new ResponseEntity(err,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(DataIsEmpty.class)
	ResponseEntity<?> handleException4(DataIsEmpty exception){
		
		ErrorMessage err=new ErrorMessage(exception.getMessage(), new Date());
		return new ResponseEntity(err,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(Exception.class)
	ResponseEntity<?> handleException2(Exception exception){
		
		ErrorMessage err=new ErrorMessage(exception.getMessage(), new Date());
		return new ResponseEntity(err,HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<List> processException(final MethodArgumentNotValidException ex) {

       List list = ex.getBindingResult().getAllErrors().stream()
               .map(fieldError -> fieldError.getDefaultMessage())
               .collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
    }
}
