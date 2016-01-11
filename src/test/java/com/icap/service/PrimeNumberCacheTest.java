package com.icap.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icap.App;
import com.icap.primeNumbers.exception.InvalidLimitException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = App.class)
public class PrimeNumberCacheTest {
	
	@Autowired
	private PrimeNumberCache primeNumberCache;

	@Test
	public void testGetPrimeNumbers() {
		assertTrue(primeNumberCache.getPrimeNumbers().length == 78498);
	}
	
	@Test
	public void testGetPrimeNumbersForSpecifiedRange() throws InvalidLimitException {
		assertEquals("2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97",
				primeNumberCache.getPrimeNumbers(100));
	}

}
