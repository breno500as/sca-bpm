package com.puc.sca.bpm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class BpmException extends RuntimeException  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8253814377498347491L;

	private Object[] messages;

	public BpmException(Object... messages) {
		this.messages = messages;
	}

	public Object[] getMessages() {
		return messages;
	}
	
	public void setMessages(Object[] messages) {
		this.messages = messages;
	}
}
