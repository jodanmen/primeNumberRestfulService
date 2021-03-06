package com.icap.service;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.icap.primeNumbers.exception.InvalidLimitException;

public class PrimeNumberGeneratorSieveAlgoTest {
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void testGetPrimeNumbers() throws InvalidLimitException {
		PrimeNumberGeneratorSieveAlgo primeNumberGenerator = new PrimeNumberGeneratorSieveAlgo();
		assertEquals("2 3 5 7", primeNumberGenerator.getPrimeNumbers(10));
	}
	
	@Test
	public void testGetPrimeNumbersWithLimitEqualsOne() throws InvalidLimitException {
		PrimeNumberGeneratorSieveAlgo primeNumberGenerator = new PrimeNumberGeneratorSieveAlgo();
		assertEquals("No Prime Numbers Found", primeNumberGenerator.getPrimeNumbers(1));
	}
	
	@Test
	public void testGetPrimeNumbersWithLimitOf2() throws InvalidLimitException {
		PrimeNumberGeneratorSieveAlgo primeNumberGenerator = new PrimeNumberGeneratorSieveAlgo();
		assertEquals("2", primeNumberGenerator.getPrimeNumbers(2));
	}
	
	@Test
	public void testGetPrimeNumbersWithLimitOf3() throws InvalidLimitException {
		PrimeNumberGeneratorSieveAlgo primeNumberGenerator = new PrimeNumberGeneratorSieveAlgo();
		assertEquals("2 3", primeNumberGenerator.getPrimeNumbers(3));
	}
	
	@Test
	public void testGetPrimeNumbersWithNegativeLimit() throws InvalidLimitException {
		expectedEx.expect(InvalidLimitException.class);
	    expectedEx.expectMessage("Invalid limit specified: -10. Limit must be a positive integer value greater than 0 and less than " + Constants.MAX_LIMIT);
	    PrimeNumberGeneratorSieveAlgo primeNumberGenerator = new PrimeNumberGeneratorSieveAlgo();
		primeNumberGenerator.getPrimeNumbers(-10);
	}
	
	@Test
	public void testGetPrimeNumbersWithZeroLimit() throws InvalidLimitException {
		expectedEx.expect(InvalidLimitException.class);
	    expectedEx.expectMessage("Invalid limit specified: 0. Limit must be a positive integer value greater than 0 and less than " + Constants.MAX_LIMIT);
	    PrimeNumberGeneratorSieveAlgo primeNumberGenerator = new PrimeNumberGeneratorSieveAlgo();
		primeNumberGenerator.getPrimeNumbers(0);
	}
	
	@Test
	public void testGetPrimeNumbersLimitAboveMaxLimit() throws InvalidLimitException {
		expectedEx.expect(InvalidLimitException.class);
	    expectedEx.expectMessage("Invalid limit specified: 1000000001. Limit must be a positive integer value greater than 0 and less than " + Constants.MAX_LIMIT);
	    PrimeNumberGeneratorSieveAlgo primeNumberGenerator = new PrimeNumberGeneratorSieveAlgo();
		primeNumberGenerator.getPrimeNumbers(1000000001);
	}

}
