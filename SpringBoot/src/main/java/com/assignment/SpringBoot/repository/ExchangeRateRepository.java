package com.assignment.SpringBoot.repository;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assignment.SpringBoot.entity.ExchangeRates;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRates, Long> 
 {
	
	@Query("select r from ExchangeRates r where r.date >= :date")
	List<ExchangeRates> findExchangeRatesByDate(@Param("date") Date date);


}
