package com.assignment.SpringBoot.Service;

import com.assignment.SpringBoot.model.ExchangeRatesModel;

public interface  ExchangeRateViewService {

	
	ExchangeRatesModel getExchangeRatesByDate(String date);
}
