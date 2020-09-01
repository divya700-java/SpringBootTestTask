package com.assignment.SpringBoot.Service.Impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.SpringBoot.Service.ExchangeRateDataRetrievalService;
import com.assignment.SpringBoot.entity.ExchangeRates;
import com.assignment.SpringBoot.repository.ExchangeRateRepository;

@Service
public class ExchangeRateDataRetrievalServiceImpl implements ExchangeRateDataRetrievalService {
	
	@Autowired
	private ExchangeRateRepository repo;

	@Override
	public List<ExchangeRates> loadExchangeRates(List<ExchangeRates> rateDetails) {
		// TODO Auto-generated method stub
		List<ExchangeRates> rates =repo.saveAll(rateDetails);
		repo.flush();
		return rates;
	}

	@Override
	public List<ExchangeRates> getExchangeRatesByDate(Date date) {
		// TODO Auto-generated method stub
		List<ExchangeRates> exchangeRates=repo.findExchangeRatesByDate(date);
		return exchangeRates;
	}

}
