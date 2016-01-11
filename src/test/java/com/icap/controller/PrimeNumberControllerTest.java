package com.icap.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.icap.primeNumbers.exception.InvalidLimitException;
import com.icap.service.Constants;
import com.icap.service.PrimeNumberCache;
import com.icap.service.PrimeNumberGeneratorSieveAlgo;
import com.icap.service.PrimeNumberGeneratorTrialDivisionAlgo;

public class PrimeNumberControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private PrimeNumberController primeNumberController;
	
	@Mock
	private PrimeNumberGeneratorTrialDivisionAlgo primeNumberGeneratorByTrialDivision;
	
	@Mock
	private PrimeNumberGeneratorSieveAlgo primeNumberGeneratorBySieve;
	
	@Mock
	private PrimeNumberCache primeNumberCache;
	
	@Before
    public void setup() {
        // Process mock annotations
        MockitoAnnotations.initMocks(this);
 
        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(primeNumberController).build();
 
    }
	
	@Test
	public void testGetPrimeNumbersByTrialDivision() throws Exception {
		when(primeNumberGeneratorByTrialDivision.getPrimeNumbers(10)).thenReturn("2 3 5 7");
		mockMvc.perform(get("/trialDivision")
				.param("limit", "10"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(),
                        Charset.forName("utf8"))))            
				.andExpect(jsonPath("$.content", is("2 3 5 7")));
	}
	
	@Test
	public void testGetPrimeNumbersByTrialDivisionWithNegativelimit() throws Exception {
		when(primeNumberGeneratorByTrialDivision.getPrimeNumbers(-10)).thenThrow(new InvalidLimitException(-10));
		mockMvc.perform(get("/trialDivision")
				.param("limit", "-10"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(),
                        Charset.forName("utf8"))))            
				.andExpect(jsonPath("$.content", is("Invalid limit specified: -10. Limit must be a positive integer value greater than 0 and less than " + Constants.MAX_LIMIT)));
	}
	
	@Test
	public void testGetPrimeNumbersByTrialDivisionWithZerolimit() throws Exception {
		when(primeNumberGeneratorByTrialDivision.getPrimeNumbers(0)).thenThrow(new InvalidLimitException(0));
		mockMvc.perform(get("/trialDivision")
				.param("limit", "0"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(),
                        Charset.forName("utf8"))))            
				.andExpect(jsonPath("$.content", is("Invalid limit specified: 0. Limit must be a positive integer value greater than 0 and less than " + Constants.MAX_LIMIT)));
	}
	
	@Test
	public void testGetPrimeNumbersByTrialDivisionWithTextForlimit() throws Exception {
		mockMvc.perform(get("/trialDivision")
				.param("limit", "a"))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testGetPrimeNumbersByTrialDivisionWithAlimitOfOne() throws Exception {
		when(primeNumberGeneratorByTrialDivision.getPrimeNumbers(1)).thenReturn("No Prime Numbers Found");
		mockMvc.perform(get("/trialDivision")
				.param("limit", "1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(),
                        Charset.forName("utf8"))))            
				.andExpect(jsonPath("$.content", is("No Prime Numbers Found")));
	}
	
	@Test
	public void testGetPrimeNumbersByTrialDivisionWithNoSpecfiedlimit() throws Exception {
		when(primeNumberGeneratorByTrialDivision.getPrimeNumbers(100)).thenReturn("2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97");
		mockMvc.perform(get("/trialDivision"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(),
                        Charset.forName("utf8"))))            
				.andExpect(jsonPath("$.content", is("2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97")));
	}
	
	@Test
	public void testGetPrimeNumbersBySieve() throws Exception {
		when(primeNumberGeneratorBySieve.getPrimeNumbers(10)).thenReturn("2 3 5 7");
		mockMvc.perform(get("/sieve")
				.param("limit", "10"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(),
                        Charset.forName("utf8"))))            
				.andExpect(jsonPath("$.content", is("2 3 5 7")));
	}
	
	@Test
	public void testGetPrimeNumbersBySieveWithNegativelimit() throws Exception {
		when(primeNumberGeneratorBySieve.getPrimeNumbers(-10)).thenThrow(new InvalidLimitException(-10));
		mockMvc.perform(get("/sieve")
				.param("limit", "-10"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(),
                        Charset.forName("utf8"))))            
				.andExpect(jsonPath("$.content", is("Invalid limit specified: -10. Limit must be a positive integer value greater than 0 and less than " + Constants.MAX_LIMIT)));
	}
	
	@Test
	public void testGetPrimeNumbersBySieveWithZerolimit() throws Exception {
		when(primeNumberGeneratorBySieve.getPrimeNumbers(0)).thenThrow(new InvalidLimitException(0));
		mockMvc.perform(get("/sieve")
				.param("limit", "0"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(),
                        Charset.forName("utf8"))))            
				.andExpect(jsonPath("$.content", is("Invalid limit specified: 0. Limit must be a positive integer value greater than 0 and less than " + Constants.MAX_LIMIT)));
	}
	
	@Test
	public void testGetPrimeNumbersBySieveWithTextForlimit() throws Exception {
		mockMvc.perform(get("/sieve")
				.param("limit", "a"))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testGetPrimeNumbersBySieveWithAlimitOfOne() throws Exception {
		when(primeNumberGeneratorBySieve.getPrimeNumbers(1)).thenReturn("No Prime Numbers Found");
		mockMvc.perform(get("/sieve")
				.param("limit", "1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(),
                        Charset.forName("utf8"))))            
				.andExpect(jsonPath("$.content", is("No Prime Numbers Found")));
	}
	
	@Test
	public void testGetPrimeNumbersBySieveWithNoSpecfiedlimit() throws Exception {
		when(primeNumberGeneratorBySieve.getPrimeNumbers(100)).thenReturn("2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97");
		mockMvc.perform(get("/sieve"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(),
                        Charset.forName("utf8"))))            
				.andExpect(jsonPath("$.content", is("2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97")));
	}
	
	@Test
	public void testGetPrimeNumbersFromCacheWithNoSpecfiedlimit() throws Exception {
		when(primeNumberCache.getPrimeNumbers(100)).thenReturn("2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97");
		mockMvc.perform(get("/cache"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(),
                        Charset.forName("utf8"))))            
				.andExpect(jsonPath("$.content", is("2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97")));
	}
	
	@Test
	public void testGetPrimeNumbersFromCacheWithSpecfiedlimit() throws Exception {
		when(primeNumberCache.getPrimeNumbers(1000)).thenReturn("2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83"
				+ " 89 97 101 103 107 109 113 127 131 137 139 149 151 157 163 167 173 179 181 191 193 197 199 211 223 227 229 233 239"
				+ " 241 251 257 263 269 271 277 281 283 293 307 311 313 317 331 337 347 349 353 359 367 373 379 383 389 397 401 409"
				+ " 419 421 431 433 439 443 449 457 461 463 467 479 487 491 499 503 509 521 523 541 547 557 563 569 571 577 587 593 599"
				+ " 601 607 613 617 619 631 641 643 647 653 659 661 673 677 683 691 701 709 719 727 733 739 743 751 757 761 769 773 787"
				+ " 797 809 811 821 823 827 829 839 853 857 859 863 877 881 883 887 907 911 919 929 937 941 947 953 967 971 977 983 991 997");
		mockMvc.perform(get("/cache")
				.param("limit", "1000"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(),
                        Charset.forName("utf8"))))            
				.andExpect(jsonPath("$.content", is("2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83"
				+ " 89 97 101 103 107 109 113 127 131 137 139 149 151 157 163 167 173 179 181 191 193 197 199 211 223 227 229 233 239"
				+ " 241 251 257 263 269 271 277 281 283 293 307 311 313 317 331 337 347 349 353 359 367 373 379 383 389 397 401 409"
				+ " 419 421 431 433 439 443 449 457 461 463 467 479 487 491 499 503 509 521 523 541 547 557 563 569 571 577 587 593 599"
				+ " 601 607 613 617 619 631 641 643 647 653 659 661 673 677 683 691 701 709 719 727 733 739 743 751 757 761 769 773 787"
				+ " 797 809 811 821 823 827 829 839 853 857 859 863 877 881 883 887 907 911 919 929 937 941 947 953 967 971 977 983 991 997")));
	}
	
	@Test
	public void testGetPrimeNumbersFromCacheWithNegativelimit() throws Exception {
		when(primeNumberCache.getPrimeNumbers(-10)).thenThrow(new InvalidLimitException(-10));
		mockMvc.perform(get("/cache")
				.param("limit", "-10"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(),
                        Charset.forName("utf8"))))            
				.andExpect(jsonPath("$.content", is("Invalid limit specified: -10. Limit must be a positive integer value greater than 0 and less than " + Constants.MAX_LIMIT)));
	}
	
	@Test
	public void testGetPrimeNumbersFromCacheWithZerolimit() throws Exception {
		when(primeNumberCache.getPrimeNumbers(0)).thenThrow(new InvalidLimitException(0));
		mockMvc.perform(get("/cache")
				.param("limit", "0"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(),
                        Charset.forName("utf8"))))            
				.andExpect(jsonPath("$.content", is("Invalid limit specified: 0. Limit must be a positive integer value greater than 0 and less than " + Constants.MAX_LIMIT)));
	}
	
	@Test
	public void testGetPrimeNumbersFromCacheWithTextForlimit() throws Exception {
		mockMvc.perform(get("/cache")
				.param("limit", "a"))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testControllerWithInvalidUrl() throws Exception {
		mockMvc.perform(get("/test")
				.param("limit", "1"))
				.andExpect(status().isNotFound());
	}
	
}
