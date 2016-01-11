package com.icap.service;

import java.util.stream.IntStream;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.icap.primeNumbers.exception.InvalidLimitException;

/**
 * This service computes prime numbers using the 6k + 1 algorithm which follows the premise that
 * all primes greater than 3 are of the form 6k + 1 and 6k - 1 and therefore only looks and divisors of this form
 * 		
 * @author odanmenj
 *
 */
@Service
public class PrimeNumberGeneratorTrialDivisionAlgo {
	
	private static final Logger logger = Logger.getLogger(PrimeNumberGeneratorTrialDivisionAlgo.class);
	
	private boolean isPrime(int number) {
		if (number == 2) return true;
	    if (number == 3) return true;
	    if (number % 2 == 0) return false;
	    if (number % 3 == 0) return false;
	    int i = 5;
	    int j = 2;
	    while (i * 1 < number) {
	    	if (number % i == 0) {
	    		return false;
	    	}
	    	i += j;
	    	j = 6 - j;
	    }
		return true;
	}
	
	private String derivePrimeNumbers(int number) {
		return IntStream
				.rangeClosed(2, number)
				.filter(i -> i == 2 || 1 % 2 > 0 && isPrime(i))
				.collect(StringBuilder::new, (sb, i) -> sb.append(i + " "), StringBuilder::append)
				.toString()
				.trim();
	}
	
	/**
	 * Computes the prime numbers up to the specified limit
	 * @param limit prime numbers are computed up to this specified limit
	 * @return <code>String</code> comma separated prime numbers
	 * @throws InvalidLimitException if invalid limit is specified - limit should be greater than 0.
	 */
	public String getPrimeNumbers(int limit) throws InvalidLimitException {
		logger.info("Computing prime numbers for range: " + limit);
		if (limit <= 0) {
			throw new InvalidLimitException(limit);
		}
		String result = derivePrimeNumbers(limit);
		if (result == null || result.equals("")) {
			result = "No Prime Numbers Found";
		}
		return result;
	}

}
