package com.utils;

/**
 * @ClassName FailException
 * @Author THINK
 * @Date 2019/9/18 10:27
 */

public class FailException extends Exception {
    public FailException() {
        // TODO Auto-generated constructor stub
    }

    public FailException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public FailException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public FailException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public FailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }
}
