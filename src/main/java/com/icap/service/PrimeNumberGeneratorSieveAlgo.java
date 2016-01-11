package com.icap.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.icap.primeNumbers.exception.InvalidLimitException;

/**
 * This service generates prime numbers for a specified range using the sieve of Eratosthenes algorithm.
 * 
 * @see https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
 * 
 * @author odanmenj
 *
 */
@Service
public class PrimeNumberGeneratorSieveAlgo {
	
	private boolean[] generatePrimeNumbers(int range) {
		boolean [] numbers = new boolean[range + 1];
		Arrays.fill(numbers, true);
		for (int i = 2; i < Math.sqrt(range); i++) {
			if (numbers[i] == true) {
				int counter = 0;
				for (int j = (i * i) + (counter * i); j <= range; j = (i * i) + (counter * i)) {
					numbers[j] = false;
					++counter;
				}
			}
		}
		return numbers;
	}
	
	/**
	 * Computes the prime numbers up to the specified limit
	 * @param limit prime numbers are computed up to this specified limit
	 * @return <code>String</code> comma separated prime numbers
	 * @throws InvalidLimitException if invalid limit is specified - limit should be greater than 0.
	 */
	public String getPrimeNumbers(int limit) throws InvalidLimitException {
		if (limit <= 0 || limit > Constants.MAX_LIMIT) {
			throw new InvalidLimitException(limit);
		}
		StringBuilder builder = new StringBuilder();
		boolean [] numbers = generatePrimeNumbers(limit);
		for (int i = 2; i <= limit; i++) {
			if (numbers[i] == true) {
				builder.append(i + " ");
			}
		}
		if (builder.length() == 0) {
			return "No Prime Numbers Found";
		}
		return builder.toString().trim();
	}

}
