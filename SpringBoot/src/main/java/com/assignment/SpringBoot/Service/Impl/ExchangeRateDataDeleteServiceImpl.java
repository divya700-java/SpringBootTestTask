package com.assignment.SpringBoot.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.SpringBoot.Service.ExchangeRateDataDeleteService;
import com.assignment.SpringBoot.repository.ExchangeRateRepository;

@Service
public class ExchangeRateDataDeleteServiceImpl implements ExchangeRateDataDeleteService {
	
	@Autowired
	private ExchangeRateRepository repo;

	@Override
	public void removeOldRecords() {
		// TODO Auto-generated method stub
		repo.deleteAll();
	}

}
