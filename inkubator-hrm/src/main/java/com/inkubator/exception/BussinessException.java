package com.inkubator.exception;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 *
 * @author rizkykojek
 */
public class BussinessException extends Exception {

	String errorKeyMessage;
	Object[] parameters;
	
	public BussinessException(String errorKeyMessage){
		this.errorKeyMessage = errorKeyMessage;
	}
	
	public BussinessException(String errorKeyMessage, Object... parameters){
		this.errorKeyMessage = errorKeyMessage;
		this.parameters = parameters;
	}

	public String getErrorKeyMessage() {
		return errorKeyMessage;
	}

	public void setErrorKeyMessage(String errorKeyMessage) {
		this.errorKeyMessage = errorKeyMessage;
	}
	
	@Override
	public String getMessage() {
		String message = StringUtils.EMPTY;
		ResourceBundle bundle = ResourceBundle.getBundle("messages", LocaleContextHolder.getLocale());
		message = (parameters != null) ? MessageFormat.format(bundle.getString(errorKeyMessage), parameters) : bundle.getString(errorKeyMessage);		
		return message;
	}
}
