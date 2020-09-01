package com.assignment.SpringBoot.Service;

import com.assignment.SpringBoot.model.ExchangeRatesModel;

public interface  ExchangeRateDataUploadService {
	
	ExchangeRatesModel uploadExchangeRates(String userAgent, String symbols);

}
