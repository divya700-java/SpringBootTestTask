package com.assignment.SpringBoot.model;

import java.util.List;

public class ExchangeRatesModel {
	
	private String base;

	private List<RateModel> rates;

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public List<RateModel> getRates() {
		return rates;
	}

	public void setRates(List<RateModel> rates) {
		this.rates = rates;
	}

	
}
