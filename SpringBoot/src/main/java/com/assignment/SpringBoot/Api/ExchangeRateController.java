package com.assignment.SpringBoot.Api;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.SpringBoot.Service.ExchangeRateDataUploadService;
import com.assignment.SpringBoot.Service.ExchangeRateViewService;
import com.assignment.SpringBoot.exception.ApplicationException;
import com.assignment.SpringBoot.model.ExchangeRatesModel;
import com.assignment.SpringBoot.model.ExchangeRatesResponse;

@RestController
@RequestMapping("/api/")
public class ExchangeRateController {
	private static final Logger LOG = LoggerFactory.getLogger(ExchangeRateController.class);
	
	@Autowired
	ExchangeRateDataUploadService uploadService;
	
	@Autowired
	ExchangeRateViewService viewService;
	
	@Value("${header.user.agent}")
	private String userAgent;

	@Value("${rate.api.symbols}")
	private String symbols;
	
	@GetMapping("/exchangeRates")
	public ResponseEntity<ExchangeRatesResponse> loadExchangeRates() {
		LOG.info("RateViewController::loadExchangeRates");

		return buildResponse(uploadService.uploadExchangeRates(userAgent, symbols));

	}

	@GetMapping("/exchangeRates/{date}")
	public ResponseEntity<ExchangeRatesResponse> getExchangeRates(@PathVariable("date") String date) {
		LOG.info("RateViewController::getExchangeRates");
		checkDateString(date);

		return buildResponse(viewService.getExchangeRatesByDate(date));

	}

	private ResponseEntity<ExchangeRatesResponse> buildResponse(ExchangeRatesModel model) {

		ExchangeRatesResponse response = new ExchangeRatesResponse();
		response.setExchangeRatesInfo(model);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private void checkDateString(String date) {
		LOG.info("RateViewController::checkDateString");
		try {
			Date.valueOf(date);
		} catch (IllegalArgumentException e) {
			LOG.error("failed to parse input parameter {} to type Date", date);
			throw new ApplicationException("Invalid input parameter  :" + date, HttpStatus.BAD_REQUEST);
		}
	}
	

}
