package com.patient.exception;

import java.time.LocalDate;
import java.util.Date;

public class ErrorMessage {

	private String message;
	private LocalDate date;
	public ErrorMessage(String message, Date date) {
		super();
		this.message = message;
		this.date = LocalDate.now();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = LocalDate.now();
	}
	
}
