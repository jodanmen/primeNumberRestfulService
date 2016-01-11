package com.icap.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icap.entities.PrimeNumbers;
import com.icap.primeNumbers.exception.InvalidLimitException;
import com.icap.service.PrimeNumberCache;
import com.icap.service.PrimeNumberGeneratorSieveAlgo;
import com.icap.service.PrimeNumberGeneratorTrialDivisionAlgo;

/**
 * Restful Controller to service requests for prime numbers.
 * 
 * @author odanmenj
 *
 */
@RestController
public class PrimeNumberController {
	
	private final AtomicLong counter = new AtomicLong();
	
	private static final Logger logger = Logger.getLogger(PrimeNumberController.class);
	
	@Autowired
	private PrimeNumberGeneratorTrialDivisionAlgo primeNumberGeneratorByTrialDivision;
	
	@Autowired
	private PrimeNumberGeneratorSieveAlgo primeNumberGeneratorBySieve;
	
	@Autowired
	private PrimeNumberCache primeNumberCache;
	
	@RequestMapping("/trialDivision")
	public PrimeNumbers getPrimeNumberByTrialDivision(@RequestParam(value="limit", defaultValue="100") int limit) {
		logger.info("Processing request for prime numbers for limit: " + limit);
		long id = counter.incrementAndGet();
		try {
			return new PrimeNumbers(id, primeNumberGeneratorByTrialDivision.getPrimeNumbers(limit));
		} catch (InvalidLimitException e) {
			logger.error("Exception occurred whilst getting prime numbers using trial division algorithm", e);
			return new PrimeNumbers(id, e.getMessage());
		}
	}
	
	@RequestMapping("/sieve")
	public PrimeNumbers getPrimeNumberBySieve(@RequestParam(value="limit", defaultValue="100") int limit) {
		logger.info("Processing request for prime numbers for limit: " + limit);
		long id = counter.incrementAndGet();
		try {
			return new PrimeNumbers(id, primeNumberGeneratorBySieve.getPrimeNumbers(limit));
		} catch (InvalidLimitException e) {
			logger.error("Exception occurred whist getting prime numbers using trial division algorithm", e);
			return new PrimeNumbers(id, e.getMessage());
		}
	}
	
	@RequestMapping("/cache")
	public PrimeNumbers getPrimeNumbersFromCache(@RequestParam(value="limit", defaultValue="100") int limit) {
		logger.info("Processing request for prime numbers for limit: " + limit);
		long id = counter.incrementAndGet();
		try {
			return new PrimeNumbers(id, primeNumberCache.getPrimeNumbers(limit));
		} catch (InvalidLimitException e) {
			logger.error("Exception occurred whist getting prime numbers using trial division algorithm", e);
			return new PrimeNumbers(id, e.getMessage());
		}
	}

}
