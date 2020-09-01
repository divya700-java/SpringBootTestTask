package com.assignment.SpringBoot.Service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.assignment.SpringBoot.fallback.ExchangeRateProxyApiFallBackImpl;
import com.assignment.SpringBoot.model.ApiResponse;


@FeignClient(name="external-rates-api",url = "${rate.api.uri}", fallback = ExchangeRateProxyApiFallBackImpl.class) 
public interface ExchangeRateProxyApiService {
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/{date}")  
	  ApiResponse getExchangeRatesByDate(@RequestHeader(name = "user-agent") String userAgent, 
			  @RequestParam(value = "symbols") String symbols, @PathVariable(value = "date") String date);

}
