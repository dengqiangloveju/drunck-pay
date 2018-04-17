package com.drunck.exception;

import org.springframework.security.core.AuthenticationException;

public class CaptchaException extends AuthenticationException {

	private static final long serialVersionUID = -4523174724906883457L;

	public CaptchaException(String message) {
		super(message);
	}

	public CaptchaException(String message, Throwable cause) {
		super(message, cause);
	}

}
