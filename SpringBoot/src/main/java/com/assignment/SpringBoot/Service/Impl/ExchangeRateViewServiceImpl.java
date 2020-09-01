package com.assignment.SpringBoot.Service.Impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.SpringBoot.Service.ExchangeRateDataRetrievalService;
import com.assignment.SpringBoot.Service.ExchangeRateViewService;
import com.assignment.SpringBoot.entity.ExchangeRates;
import com.assignment.SpringBoot.model.ExchangeRatesModel;
import com.assignment.SpringBoot.model.RateModel;

@Service
public class ExchangeRateViewServiceImpl implements ExchangeRateViewService{
	
	private static final Logger LOG = LoggerFactory.getLogger(ExchangeRateViewServiceImpl.class);
	
	@Autowired
	ExchangeRateDataRetrievalService dataService;

	@Override
	public ExchangeRatesModel getExchangeRatesByDate(String date) {
		// TODO Auto-generated method stub
		LOG.info("ExchangeRateViewServiceImpl : inside method getExchangeRatesByDate()");
		ExchangeRatesModel model =  new ExchangeRatesModel();
		List<ExchangeRates> rates = dataService.getExchangeRatesByDate(Date.valueOf(date));
		List<RateModel> rateModelList = new ArrayList<>();
		
		for(ExchangeRates r:rates){
			
			RateModel rateModel =  new RateModel();
			rateModel.setCurrencyCode(r.getCurrency());
			rateModel.setDate(r.getDate().toString());
			rateModel.setExChangeRate( r.getExchangeRate());
			rateModelList.add(rateModel);
			model.setBase(r.getBase());
			
			
		}
		
		model.setRates(rateModelList);
		
		return model;
		
	}

}
