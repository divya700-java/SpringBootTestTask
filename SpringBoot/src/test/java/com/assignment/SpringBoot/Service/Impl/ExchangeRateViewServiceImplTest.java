package com.assignment.SpringBoot.Service.Impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.SpringBoot.Service.ExchangeRateDataRetrievalService;
import com.assignment.SpringBoot.entity.ExchangeRates;
import com.assignment.SpringBoot.model.ExchangeRatesModel;


@RunWith(SpringRunner.class)
public class ExchangeRateViewServiceImplTest {
	
	@InjectMocks
	ExchangeRateViewServiceImpl viewService;
	
	@Mock
	ExchangeRateDataRetrievalService dataService;
	
	@Before
	public void setup() {
		Mockito.when(dataService.getExchangeRatesByDate(Date.valueOf("2020-09-01"))).thenReturn(buildRates());
	}
	
	@Test
	public void testGetExchangeRatesByDate(){
		
		ExchangeRatesModel model = viewService.getExchangeRatesByDate("2020-09-01");
		Assert.assertEquals("EUR", model.getBase());
		
	}
	
	private List<ExchangeRates> buildRates() {

		List<ExchangeRates> rateList = new ArrayList<>();
		ExchangeRates rate1 = new ExchangeRates();
		rate1.setBase("EUR");
		rate1.setCurrency("HKD");
		rate1.setDate(Date.valueOf("2020-09-01"));
		rate1.setExchangeRate(new BigDecimal(0.60));
		rateList.add(rate1);

		ExchangeRates rate2 = new ExchangeRates();
		rate2.setBase("EUR");
		rate2.setCurrency("GBP");
		rate2.setDate(Date.valueOf("2020-09-01"));
		rate2.setExchangeRate(new BigDecimal(0.90));
		rateList.add(rate2);

		ExchangeRates rate3 = new ExchangeRates();
		rate3.setBase("EUR");
		rate3.setCurrency("USD");
		rate3.setDate(Date.valueOf("2020-09-01"));
		rate3.setExchangeRate(new BigDecimal(0.80));
		rateList.add(rate3);

		return rateList;

	}

	
}
