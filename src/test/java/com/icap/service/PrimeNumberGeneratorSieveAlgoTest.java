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
	public void testGetPrimeNumbersWithRangeEqualsOne() throws InvalidLimitException {
		PrimeNumberGeneratorSieveAlgo primeNumberGenerator = new PrimeNumberGeneratorSieveAlgo();
		assertEquals("No Prime Numbers Found", primeNumberGenerator.getPrimeNumbers(1));
	}
	
	@Test
	public void testGetPrimeNumbersWithRangeOf2() throws InvalidLimitException {
		PrimeNumberGeneratorSieveAlgo primeNumberGenerator = new PrimeNumberGeneratorSieveAlgo();
		assertEquals("2", primeNumberGenerator.getPrimeNumbers(2));
	}
	
	@Test
	public void testGetPrimeNumbersWithRangeOf3() throws InvalidLimitException {
		PrimeNumberGeneratorSieveAlgo primeNumberGenerator = new PrimeNumberGeneratorSieveAlgo();
		assertEquals("2 3", primeNumberGenerator.getPrimeNumbers(3));
	}
	
	@Test
	public void testGetPrimeNumbersWithNegativeRange() throws InvalidLimitException {
		expectedEx.expect(InvalidLimitException.class);
	    expectedEx.expectMessage("Invalid range specified: -10. Range must be a positive integer value greater than 0");
	    PrimeNumberGeneratorSieveAlgo primeNumberGenerator = new PrimeNumberGeneratorSieveAlgo();
		primeNumberGenerator.getPrimeNumbers(-10);
	}
	
	@Test
	public void testGetPrimeNumbersWithZeroRange() throws InvalidLimitException {
		expectedEx.expect(InvalidLimitException.class);
	    expectedEx.expectMessage("Invalid range specified: 0. Range must be a positive integer value greater than 0");
	    PrimeNumberGeneratorSieveAlgo primeNumberGenerator = new PrimeNumberGeneratorSieveAlgo();
		primeNumberGenerator.getPrimeNumbers(0);
	}

}
