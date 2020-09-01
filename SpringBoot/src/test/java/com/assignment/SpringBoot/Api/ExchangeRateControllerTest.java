package com.assignment.SpringBoot.Api;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.assignment.SpringBoot.Service.ExchangeRateDataUploadService;
import com.assignment.SpringBoot.Service.ExchangeRateViewService;
import com.assignment.SpringBoot.model.ExchangeRatesModel;
import com.assignment.SpringBoot.model.ExchangeRatesResponse;
import com.assignment.SpringBoot.model.RateModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(ExchangeRateController.class)
public class ExchangeRateControllerTest {
	
	@MockBean
	private ExchangeRateDataUploadService loadService;

	@MockBean
	private ExchangeRateViewService viewService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void  testLoadExchangeRates() throws JsonProcessingException, Exception {
		
		ExchangeRatesModel model = new ExchangeRatesModel();
		model.setBase("EUR");
		Mockito.when(loadService.uploadExchangeRates(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(model);
		
		MvcResult mvcResult = mockMvc
				.perform(get("/api/exchangeRates").content(getObjectMapper().writeValueAsString(model))
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andReturn();
		
		
		ExchangeRatesResponse serviceRespopnse = parseMvcResult(mvcResult);
		Assert.assertEquals("EUR", serviceRespopnse.getExchangeRatesInfo().getBase());
		
	}
	
	@Test
	public void testGetExchangeRates() throws JsonProcessingException, Exception {
		
		ExchangeRatesModel model = new ExchangeRatesModel();
		model.setBase("EUR");
		List<RateModel> rateModelList = new ArrayList<>();
		RateModel rateModel = new RateModel();
		rateModel.setCurrencyCode("USD");
		rateModel.setExChangeRate(new BigDecimal(0.90));
		rateModelList.add(rateModel);
		model.setRates(rateModelList);
		Mockito.when(viewService.getExchangeRatesByDate(ArgumentMatchers.anyString())).thenReturn(model);
		MvcResult mvcResult = mockMvc
				.perform(get("/api/exchangeRates/2020-08-10").content(getObjectMapper().writeValueAsString(model))
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andReturn();
		ExchangeRatesResponse serviceRespopnse = parseMvcResult(mvcResult);
		Assert.assertEquals("USD", serviceRespopnse.getExchangeRatesInfo().getRates().get(0).getCurrencyCode());
		
	}
	
	@Test
	public void testGetExchangeRates_IllegalArgumentException() throws JsonProcessingException, Exception {
		
		ExchangeRatesModel model = new ExchangeRatesModel();
		model.setBase("EUR");
		List<RateModel> rateModelList = new ArrayList<>();
		RateModel rateModel = new RateModel();
		rateModel.setCurrencyCode("USD");
		rateModel.setExChangeRate(new BigDecimal(0.90));
		rateModelList.add(rateModel);
		model.setRates(rateModelList);
		MvcResult mvcResult = mockMvc
				.perform(get("/api/exchangeRates/test").content(getObjectMapper().writeValueAsString(model))
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andReturn();
		
		ExchangeRatesResponse serviceRespopnse = parseMvcResult(mvcResult);
		Assert.assertEquals("Invalid input parameter  :test", serviceRespopnse.getErrorMessage());
		
	}
	
	private ObjectMapper getObjectMapper() {

		return new ObjectMapper();
	}
	
	private ExchangeRatesResponse  parseMvcResult(MvcResult mvcResult) throws UnsupportedEncodingException, JsonMappingException, JsonProcessingException {
		
		String resultJson = mvcResult.getResponse().getContentAsString();
		return getObjectMapper().readValue(resultJson, ExchangeRatesResponse.class);
		
		
	}
	

	
}
