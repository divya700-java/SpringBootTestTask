package com.assignment.SpringBoot.Service.Impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.SpringBoot.entity.ExchangeRates;
import com.assignment.SpringBoot.repository.ExchangeRateRepository;


@RunWith(SpringRunner.class)
public class ExchangeRateDataRetrievalServiceImplTest {
	
	@InjectMocks
	private  ExchangeRateDataRetrievalServiceImpl dataServiceImpl;
	
	@Mock
	private ExchangeRateRepository exchangeRateRepository;
	
	@Before
	public void setup(){
		List<ExchangeRates> rateList = buildInput();
		Mockito.when(exchangeRateRepository.findExchangeRatesByDate(Date.valueOf("2020-09-01"))).thenReturn(rateList);
		Mockito.when(exchangeRateRepository.saveAll(ArgumentMatchers.anyListOf(ExchangeRates.class))).thenReturn(rateList);
		
		
		
	}
	
	@Test
	public void testGetExchangeRatesByDate(){
		
		List<ExchangeRates> resList = dataServiceImpl.getExchangeRatesByDate(Date.valueOf("2020-09-01"));
		Assert.assertEquals("USD", resList.get(0).getCurrency());
		
	}
	
	@Test
	public void testLoadExchangeRates() {
		
		
		List<ExchangeRates> res = dataServiceImpl.loadExchangeRates(buildInput());
		Assert.assertEquals("USD", res.get(0).getCurrency());
		
		
	}
	
	private List<ExchangeRates> buildInput(){
		
		List<ExchangeRates> rateList =  new ArrayList<>();
		ExchangeRates rate = new ExchangeRates();
		rate.setBase("EUR");
		rate.setCurrency("USD");
		rate.setDate(Date.valueOf("2020-09-01"));
		rate.setExchangeRate(new BigDecimal(0.90));
		rateList.add(rate);
		
		return rateList;
		
		
	}

	

}
