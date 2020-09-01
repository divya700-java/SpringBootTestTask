package com.assignment.SpringBoot.model;
import com.fasterxml.jackson.databind.JsonNode;

public class ApiResponse {
	
	private String base;

	private JsonNode rates;

	private String date;

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public JsonNode getRates() {
		return rates;
	}

	public void setRates(JsonNode rates) {
		this.rates = rates;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	

}
