package com.icap.service;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.icap.primeNumbers.exception.InvalidLimitException;

public class PrimeNumberGeneratorTrialDivisionAlgoTest {
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void testGetPrimeNumbers() throws InvalidLimitException {
		PrimeNumberGeneratorTrialDivisionAlgo primeNumberGenerator = new PrimeNumberGeneratorTrialDivisionAlgo();
		assertEquals("2 3 5 7", primeNumberGenerator.getPrimeNumbers(10));
	}
	
	@Test
	public void testGetPrimeNumbersWithLimitEqualsOne() throws InvalidLimitException {
		PrimeNumberGeneratorTrialDivisionAlgo primeNumberGenerator = new PrimeNumberGeneratorTrialDivisionAlgo();
		assertEquals("No Prime Numbers Found", primeNumberGenerator.getPrimeNumbers(1));
	}
	
	@Test
	public void testGetPrimeNumbersWithNegativeLimit() throws InvalidLimitException {
		expectedEx.expect(InvalidLimitException.class);
	    expectedEx.expectMessage("Invalid limit specified: -10. Limit must be a positive integer value greater than 0");
		PrimeNumberGeneratorTrialDivisionAlgo primeNumberGenerator = new PrimeNumberGeneratorTrialDivisionAlgo();
		primeNumberGenerator.getPrimeNumbers(-10);
	}
	
	@Test
	public void testGetPrimeNumbersWithLimitAboveMaxLimit() throws InvalidLimitException {
		expectedEx.expect(InvalidLimitException.class);
	    expectedEx.expectMessage("Invalid limit specified: 1000000001. Limit must be a positive integer value greater than 0");
		PrimeNumberGeneratorTrialDivisionAlgo primeNumberGenerator = new PrimeNumberGeneratorTrialDivisionAlgo();
		primeNumberGenerator.getPrimeNumbers(1000000001);
	}
	
	@Test
	public void testGetPrimeNumbersWithZeroRange() throws InvalidLimitException {
		expectedEx.expect(InvalidLimitException.class);
	    expectedEx.expectMessage("Invalid range specified: 0. Range must be a positive integer value greater than 0");
		PrimeNumberGeneratorTrialDivisionAlgo primeNumberGenerator = new PrimeNumberGeneratorTrialDivisionAlgo();
		primeNumberGenerator.getPrimeNumbers(0);
	}

}
