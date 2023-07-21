
package edu.school42.chat.repositories;

import java.lang.RuntimeException;

public class NotSavedSubEntityException extends RuntimeException {
	public	NotSavedSubEntityException(String errMessage) {
		super(errMessage);
	}
}

