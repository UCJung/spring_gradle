package com.spay.web.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;

import com.spay.exception.ErrorCode;

@Configuration
public class SPayMessage implements InitializingBean {

    @Autowired
    private MessageSourceAccessor message;

    private static MessageSourceAccessor msg;

    public static String getMessage(String code) {
        return msg.getMessage(code);
    }

    public static String getMessage(String code, Object... args) {
        return msg.getMessage(code, args);
    }

    public static String getErrorMessage(String code) {
        try {
            return msg.getMessage(code);
        } catch (Exception e) {
            return code;
        }
    }

    public static String getErrorMessage(ErrorCode errorCode) {
        return msg.getMessage(errorCode.getCode());
    }

    public static String getErrorMessage(String code, Object... args) {
        return msg.getMessage(code, args);
    }

    public static String getErrorMessage(ErrorCode errorCode, Object... args) {
        return msg.getMessage(errorCode.getCode(), args);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        SPayMessage.msg = message;
    }

}
