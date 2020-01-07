package com.pankaj.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Environment enviornment;
	
	@Autowired
	private ExchangeValueRepository repository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retreiveExchangeValue(@PathVariable String from,@PathVariable String to) {
		
		//ExchangeValue exchangeValue = new  ExchangeValue(1000L, from, to, BigDecimal.valueOf(65));
		ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
		exchangeValue.setPort(Integer.parseInt(enviornment.getProperty("local.server.port")));
		logger.info("{}" ,exchangeValue);
		return exchangeValue;
		
		
		
	}

}
