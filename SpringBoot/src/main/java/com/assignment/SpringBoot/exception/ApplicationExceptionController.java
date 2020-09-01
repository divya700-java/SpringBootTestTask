package com.assignment.SpringBoot.exception;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.assignment.SpringBoot.model.ExchangeRatesResponse;

@ControllerAdvice
public class ApplicationExceptionController {

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<ExchangeRatesResponse> handleApplicationException(ApplicationException ex) {

		ExchangeRatesResponse res = new ExchangeRatesResponse();
		res.setErrorMessage(ex.getMessage());

		return new ResponseEntity<>(res, ex.getStatus());

	}

}
