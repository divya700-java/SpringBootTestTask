package com.assignment.SpringBoot.repository;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.SpringBoot.entity.ExchangeRates;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ExchangeRateRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private ExchangeRateRepository exchangeRateRepository;
	
	@Test
	public void testFindExchangeRatesByDate() {

		ExchangeRates details = new ExchangeRates();
		details.setBase("EUR");
		details.setCurrency("USD");
		details.setDate(Date.valueOf("2020-08-25"));

		entityManager.persist(details);
		entityManager.flush();

		List<ExchangeRates> response = exchangeRateRepository.findExchangeRatesByDate(Date.valueOf("2020-08-25"));

		Assert.assertEquals("USD", response.get(0).getCurrency());

	}

	
}
