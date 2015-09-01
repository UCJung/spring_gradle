package com.spay.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spay.web.config.SPayMessage;

public class SPayException extends RuntimeException {

	private static final long serialVersionUID = -6109789623535384816L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    public boolean isLog = true;
    public ErrorCode errorCode;
    public String message;
    public Object[] args;
    
    public SPayException() {
    }

    public SPayException(boolean isLog) {
        this.isLog = isLog;
    }

    public void exceptionlog() {
        if (isLog == true) {
            logger.error("\nError Code : {}\nError Message : {}", errorCode, message);
        }
    }

    public SPayException(ErrorCode errorCode) {
        super(SPayMessage.getErrorMessage(errorCode));
        this.errorCode = errorCode;
        this.message = SPayMessage.getErrorMessage(errorCode);
        exceptionlog();
    }

    public SPayException(ErrorCode errorCode, boolean isLog) {
        super(SPayMessage.getErrorMessage(errorCode));
        this.isLog = isLog;
        this.errorCode = errorCode;
        this.message = SPayMessage.getErrorMessage(errorCode);
        exceptionlog();
    }

    public SPayException(ErrorCode errorCode, Object... args) {
        super(SPayMessage.getErrorMessage(errorCode));
        this.errorCode = errorCode;
        this.message = SPayMessage.getErrorMessage(errorCode, args);
        this.args = args;
        exceptionlog();
    }

    public SPayException(ErrorCode errorCode, Throwable throwable, Object... args) {
        super(SPayMessage.getErrorMessage(errorCode), throwable);

        this.errorCode = errorCode;
        this.message = SPayMessage.getErrorMessage(errorCode, args);
        this.args = args;
        exceptionlog();
    }

    public SPayException(ErrorCode errorCode, boolean isLog, Object... args) {
        super(SPayMessage.getErrorMessage(errorCode));

        this.errorCode = errorCode;
        this.message = SPayMessage.getErrorMessage(errorCode, args);
        this.isLog = isLog;
        exceptionlog();
    }

    public SPayException(String message) {
        super(message);
        this.message = message;
        logger.error(message);
    }

    public SPayException(Throwable ex) {
        super(ex.getMessage());
        logger.error(ex.getMessage(), ex);
    }

    public SPayException(String message, Throwable ex) {
        super(message);
        logger.error(message, ex);
    }

    @Override
    public String getMessage() {
        return message;
    }    

}
