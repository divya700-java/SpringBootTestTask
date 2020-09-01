package com.assignment.SpringBoot.model;

public class ExchangeRatesResponse {
	
	private ExchangeRatesModel exchangeRatesInfo;

	private String errorMessage;

	public ExchangeRatesModel getExchangeRatesInfo() {
		return exchangeRatesInfo;
	}

	public void setExchangeRatesInfo(ExchangeRatesModel exchangeRatesInfo) {
		this.exchangeRatesInfo = exchangeRatesInfo;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	

}
