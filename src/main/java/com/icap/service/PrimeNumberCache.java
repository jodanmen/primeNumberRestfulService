package com.icap.service;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.icap.primeNumbers.exception.InvalidLimitException;

/**
 * Cache that stores prime numbers for found in a range of One million numbers.
 * It uses the <code>PrimeNumberGeneratorSieveAlgo</code> service to compute these prime numbers
 * @see #PrimeNumberGeneratorSieveAlgo
 * @author odanmenj
 *
 */
@Component
public class PrimeNumberCache {
	
	private int[] primeNumbers;
	
	private PrimeNumberGeneratorSieveAlgo primeNumberGenerator;
	
	private final int MAX_CACHE_VALUE = 1000000;
	
	private static final Logger logger = Logger.getLogger(PrimeNumberCache.class);
	
	@Autowired
	public PrimeNumberCache(PrimeNumberGeneratorSieveAlgo primeNumberGenerator) {
		primeNumbers = new int[MAX_CACHE_VALUE];
		this.primeNumberGenerator = primeNumberGenerator;
		try {
			if (primeNumberGenerator != null) {
				this.primeNumbers = Arrays.asList(this.primeNumberGenerator.getPrimeNumbers(MAX_CACHE_VALUE)
						.split(" "))
						.stream()
						.mapToInt(n -> Integer.valueOf(n))
						.toArray();
			} else {
				logger.error("PrimeNumberGeneratorSieveAlgo service is not available so cache cannot be loaded");
			}
		} catch (InvalidLimitException e) {
			logger.error("Exception occurred while loading cache with prime numbers so cache is not available", e);
		}
	}

	/**
	 * Returns all prime numbers in the cache
	 * @return an array containing all primes.
	 */
	public int[] getPrimeNumbers() {
		return primeNumbers;
	}
	
	/**
	 * Fetches prime numbers from the cache for a specified range up to MAX VALUE 1000000
	 * @param range of prime numbers
	 * @return <code>String</code> Comma separated prime numbers
	 * @throws InvalidLimitException if invalid range is specified - range should be greater than 0 and less than 1000000
	 */
	public String getPrimeNumbers(int range) throws InvalidLimitException {
		if (range <= 0 || range > MAX_CACHE_VALUE) {
			throw new InvalidLimitException("Invalid range specified: " + range + ". Range should be between 1 and " + MAX_CACHE_VALUE);
		}
		StringBuilder stringBuilder = new StringBuilder();
		int highestValueOfPrime = 0;
		for (int i = 0; highestValueOfPrime <= range && i < primeNumbers.length - 1; i++) {
			stringBuilder.append(primeNumbers[i] + " ");
			highestValueOfPrime = primeNumbers[i + 1];
		}
		return stringBuilder.toString().trim();
	}
	
}
