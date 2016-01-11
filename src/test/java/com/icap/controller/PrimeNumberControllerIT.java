package com.icap.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.icap.App;
import com.icap.service.PrimeNumberCache;
import com.icap.service.PrimeNumberGeneratorSieveAlgo;
import com.icap.service.PrimeNumberGeneratorTrialDivisionAlgo;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = App.class)
public class PrimeNumberControllerIT {
	
	@Autowired
    private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private PrimeNumberController primeNumberController;
	
	@Autowired
	private PrimeNumberGeneratorTrialDivisionAlgo primeNumberGeneratorByTrialDivision;
	
	@Autowired
	private PrimeNumberGeneratorSieveAlgo primeNumberGeneratorBySieve;
	
	@Autowired
	private PrimeNumberCache primeNumberCache;
	
	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
	
	@Test
	public void testGetPrimeNumbersByTrialDivision() throws Exception {
		mockMvc.perform(get("/trialDivision")
				.param("limit", "10"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(),
                        Charset.forName("utf8"))))            
				.andExpect(jsonPath("$.content", is("2 3 5 7")));
	}
	
	@Test
	public void testGetPrimeNumbersBySieve() throws Exception {
		mockMvc.perform(get("/sieve")
				.param("limit", "10"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(),
                        Charset.forName("utf8"))))            
				.andExpect(jsonPath("$.content", is("2 3 5 7")));
	}
	
	@Test
	public void testGetPrimeNumbersFromCache() throws Exception {
		mockMvc.perform(get("/cache")
				.param("limit", "10"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(),
                        Charset.forName("utf8"))))            
				.andExpect(jsonPath("$.content", is("2 3 5 7")));
	}

}
