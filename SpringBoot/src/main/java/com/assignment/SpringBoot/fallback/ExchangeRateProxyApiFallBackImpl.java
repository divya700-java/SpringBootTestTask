package com.assignment.SpringBoot.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.assignment.SpringBoot.Service.ExchangeRateProxyApiService;
import com.assignment.SpringBoot.model.ApiResponse;

@Service
public class ExchangeRateProxyApiFallBackImpl implements ExchangeRateProxyApiService {
	private static final Logger LOG = LoggerFactory.getLogger(ExchangeRateProxyApiFallBackImpl.class);
	@Override
	public ApiResponse getExchangeRatesByDate(String userAgent, String symbols, String date) {
		// TODO Auto-generated method stub
		LOG.info("Hystrix,Netflix Invoked FallBack Method");
		return new ApiResponse();
	}

	
}
