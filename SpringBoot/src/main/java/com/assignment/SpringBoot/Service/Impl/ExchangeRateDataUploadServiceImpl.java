package com.assignment.SpringBoot.Service.Impl;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.assignment.SpringBoot.Service.ExchangeRateDataDeleteService;
import com.assignment.SpringBoot.Service.ExchangeRateDataRetrievalService;
import com.assignment.SpringBoot.Service.ExchangeRateDataUploadService;
import com.assignment.SpringBoot.Service.ExchangeRateProxyApiService;
import com.assignment.SpringBoot.entity.ExchangeRates;
import com.assignment.SpringBoot.exception.ApplicationException;
import com.assignment.SpringBoot.model.ApiResponse;
import com.assignment.SpringBoot.model.ExchangeRatesModel;
import com.assignment.SpringBoot.model.RateModel;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class ExchangeRateDataUploadServiceImpl implements ExchangeRateDataUploadService{
	
	private static final Logger LOG = LoggerFactory.getLogger(ExchangeRateDataUploadServiceImpl.class);
	
	@Autowired
	private ExchangeRateDataRetrievalService dataSrvice;
	
	@Autowired
	private  ExchangeRateProxyApiService proxyService;
	@Autowired
	private  ExchangeRateDataDeleteService deleteService;

	@Override
	public ExchangeRatesModel uploadExchangeRates(String userAgent, String symbols) {
		// TODO Auto-generated method stub
		LOG.info("ExchangeRateDataUploadServiceImpl : inside method uploadExchangeRates ");
		removeOldRecords();
		LocalDate currentDate = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
		LocalDate pastOneYear = currentDate.minusYears(1).with(TemporalAdjusters.firstDayOfMonth());
		ApiResponse apiResponse=null;
		ExchangeRatesModel exchangeRatesModel=new ExchangeRatesModel();
		List<RateModel> ratesModelList = new ArrayList<>();
		LOG.info("ExchangeRateDataUploadServiceImpl :: External API will be called"
				+ " for last 12 Months exchange Rates");
		for (LocalDate date = pastOneYear; date
				.isBefore(currentDate); date = date.with(TemporalAdjusters.firstDayOfNextMonth())) {
			apiResponse = proxyService.getExchangeRatesByDate(userAgent, symbols, date.toString());
			if (ObjectUtils.isEmpty(apiResponse)) {
				throw new ApplicationException("no records found from externalAPI", HttpStatus.FAILED_DEPENDENCY);
			}
			exchangeRatesModel.setBase(apiResponse.getBase());
			List<ExchangeRates> ratesList = new ArrayList<>();
			Optional<JsonNode> optionalNode = Optional.ofNullable(apiResponse.getRates());
			if (optionalNode.isPresent()) {
				Iterator<Entry<String, JsonNode>> jsonEntries = apiResponse.getRates().fields();

				while (jsonEntries.hasNext()) {

					ExchangeRates rates = new ExchangeRates();
					RateModel rateModel = new RateModel();
					Entry<String, JsonNode> e = jsonEntries.next();
					rates.setCurrency(e.getKey());
					rates.setExchangeRate(e.getValue().decimalValue());
					rates.setBase(apiResponse.getBase());
					rates.setDate(Date.valueOf(LocalDate.parse(apiResponse.getDate())));
					ratesList.add(rates);

					rateModel.setCurrencyCode(e.getKey());
					rateModel.setExChangeRate(e.getValue().decimalValue());
					rateModel.setDate(apiResponse.getDate());
					ratesModelList.add(rateModel);

				}
			}
			exchangeRatesModel.setRates(ratesModelList);

			dataSrvice.loadExchangeRates(ratesList);

			LOG.info("ExchangeRateDataRetrievalServiceImpl :records updated in DB" );
		}
		
		return exchangeRatesModel;
	}
private void removeOldRecords()
{
	deleteService.removeOldRecords();
}
}
