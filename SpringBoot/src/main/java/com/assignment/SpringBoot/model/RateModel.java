package com.assignment.SpringBoot.model;

import java.math.BigDecimal;

public class RateModel {
	
	private String currencyCode;
	private BigDecimal exChangeRate;
	private String date;
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public BigDecimal getExChangeRate() {
		return exChangeRate;
	}
	public void setExChangeRate(BigDecimal exChangeRate) {
		this.exChangeRate = exChangeRate;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	

}
