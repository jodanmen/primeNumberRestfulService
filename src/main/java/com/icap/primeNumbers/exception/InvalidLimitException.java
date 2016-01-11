package com.icap.primeNumbers.exception;

import com.icap.service.Constants;

/**
 * Exception class to handle invalid limits specified for computing prime numbers.
 * 
 * @author odanmenj
 *
 */
public class InvalidLimitException extends RuntimeException {

	private static final long serialVersionUID = -960895284967382574L;
	
	public InvalidLimitException(int limit) {
		super("Invalid limit specified: " + limit + ". Limit must be a positive integer value greater than 0 and less than " + Constants.MAX_LIMIT);
	}
	
	public InvalidLimitException(String message) {
		super(message);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
