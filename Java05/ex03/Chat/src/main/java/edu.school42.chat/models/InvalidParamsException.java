
package edu.school42.chat.models;

import java.lang.RuntimeException;

public class InvalidParamsException extends RuntimeException {
	public	InvalidParamsException(String errMessage) {
		super(errMessage);
	}
}

