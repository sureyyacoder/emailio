package com.tuncayuzun.emailio.consumer;

public class DummyMailException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DummyMailException(String message) {
		super(message);
	}
	
    public DummyMailException(Throwable cause) {
        super(cause);
    }

    public DummyMailException(String message, Throwable cause) {
        super(message, cause);
    }

}
