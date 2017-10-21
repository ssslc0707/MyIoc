package com.slc.MyIoc.Application.exception;

import java.util.ArrayList;

public class DiException extends RuntimeException{

	public DiException() {
		super();
	}

	public DiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DiException(String message, Throwable cause) {
		super(message, cause);
	}

	public DiException(String message) {
		super(message);
	}

	public DiException(Throwable cause) {
		super(cause);
	}


	
}
