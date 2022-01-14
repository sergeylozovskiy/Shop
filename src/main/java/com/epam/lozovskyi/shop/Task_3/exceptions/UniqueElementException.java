package com.epam.lozovskyi.shop.Task_3.exceptions;

public class UniqueElementException extends IllegalArgumentException{
	private String message;

	public UniqueElementException() {
		super();
	}

	public UniqueElementException(String message) {
		super(message);
		this.message = message;
	}

	public UniqueElementException(Throwable cause) {
		super(cause);
	}
}
