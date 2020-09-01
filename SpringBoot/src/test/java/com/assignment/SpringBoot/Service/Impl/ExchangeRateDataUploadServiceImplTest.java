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

import com.assignment.SpringBoot.Service.ExchangeRateDataDeleteService;
import com.assignment.SpringBoot.Service.ExchangeRateDataRetrievalService;
import com.assignment.SpringBoot.Service.ExchangeRateProxyApiService;
import com.assignment.SpringBoot.entity.ExchangeRates;
import com.assignment.SpringBoot.exception.ApplicationException;
import com.assignment.SpringBoot.model.ApiResponse;
import com.assignment.SpringBoot.model.ExchangeRatesModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
public class ExchangeRateDataUploadServiceImplTest {
	
	@InjectMocks
	private ExchangeRateDataUploadServiceImpl dataUploadServiceImpl;
	
	@Mock
	private ExchangeRateProxyApiService proxy;
	
   @Mock
   private ExchangeRateDataRetrievalService dataService;
   
   @Mock
   private ExchangeRateDataDeleteService deleteService;
   
   
   @Before
	public void setup() throws JsonMappingException, JsonProcessingException {
	   ApiResponse apiRseponse = new ApiResponse();
		apiRseponse.setBase("EUR");
		apiRseponse.setDate("2020-09-01");
		String rates = "{\"USD\":0.8 ,\"GBP\":0.9, \"HKD\":0.7}";
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(rates);
		apiRseponse.setRates(node);
		Mockito.when(proxy.getExchangeRatesByDate(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(),
				ArgumentMatchers.anyString())).thenReturn(apiRseponse);

		Mockito.when(dataService.loadExchangeRates(ArgumentMatchers.anyListOf(ExchangeRates.class))).thenReturn(buildRates());

	}

	@Test
	public void testUploadExchangeRates() {

		ExchangeRatesModel exRateModel = dataUploadServiceImpl.uploadExchangeRates("test1", "test2");

		Assert.assertEquals("EUR", exRateModel.getBase());
	}

	@Test(expected = ApplicationException.class)
	public void testUploadExchangeRates_NoDataFromExternalApi() {

		Mockito.when(proxy.getExchangeRatesByDate(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(),
				ArgumentMatchers.anyString())).thenReturn(null);
		dataUploadServiceImpl.uploadExchangeRates("test1", "test2");

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
