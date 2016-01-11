package com.icap.entities;

/**
 * Prime number entity object encapsulating an id and a comma separated list of primes
 * 
 * @author odanmenj
 *
 */
public class PrimeNumbers {
	
	private final long id;
	
	private final String content;
	
	public PrimeNumbers(long id, String content) {
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
	
}
