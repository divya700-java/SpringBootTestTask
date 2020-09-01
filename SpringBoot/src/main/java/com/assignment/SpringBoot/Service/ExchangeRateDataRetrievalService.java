package com.assignment.SpringBoot.Service;
import java.sql.Date;
import java.util.List;

import com.assignment.SpringBoot.entity.ExchangeRates;

public interface  ExchangeRateDataRetrievalService {
	
	List<ExchangeRates> loadExchangeRates(List<ExchangeRates> rateDetails);
	List<ExchangeRates> getExchangeRatesByDate(Date date);

}
