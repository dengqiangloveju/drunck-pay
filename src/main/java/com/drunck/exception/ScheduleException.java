package com.drunck.exception;

public class ScheduleException extends RuntimeException {
	private static final long serialVersionUID = -257914896699092189L;

	public ScheduleException() {
		super();
	}

	public ScheduleException(String message) {
		super(message);
	}

	public ScheduleException(Throwable cause) {
		super(cause);
	}

	public ScheduleException(String message, Throwable cause) {
		super(message, cause);
	}
}
