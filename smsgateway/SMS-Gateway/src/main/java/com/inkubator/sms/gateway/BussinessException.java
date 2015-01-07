package com.inkubator.sms.gateway;

/**
 *
 * @author rizkykojek
 */
public class BussinessException extends Exception {

    String errorKeyMessage;

    public BussinessException(String errorKeyMessage) {
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
//        ResourceBundle messages = ResourceBundle.getBundle("messages", LocaleContextHolder.getLocale());
        return errorKeyMessage;
    }
}
