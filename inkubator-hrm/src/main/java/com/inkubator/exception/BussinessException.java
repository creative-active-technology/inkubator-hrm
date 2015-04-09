package com.inkubator.exception;

import java.util.ResourceBundle;

import org.springframework.context.i18n.LocaleContextHolder;

/**
 *
 * @author rizkykojek
 */
public class BussinessException extends Exception {

	String errorKeyMessage;
	
	public BussinessException(String errorKeyMessage){
		this.errorKeyMessage = errorKeyMessage;
	}

	public String getErrorKeyMessage() {
		return errorKeyMessage;
	}

	public void setErrorKeyMessage(String errorKeyMessage) {
		this.errorKeyMessage = errorKeyMessage;
	}
	
	@Override
	public String getMessage() {
		ResourceBundle messages = ResourceBundle.getBundle("Messages", LocaleContextHolder.getLocale());
		return messages.getString(errorKeyMessage);
	}
}
