package com.puc.sca.bpm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class BpmException extends RuntimeException {

	
	private static final long serialVersionUID = -6438459847167035698L;
	
	
	public BpmException(String msg) {
		 super(msg);
	}

}
