package com.android.dynamic.Exception;

public class MethodInvokeErrorException extends RuntimeException {
    private static final String INVOKE_ERROR = "Method invoke error, please check the api!!";
    private static final long serialVersionUID = 4406048741013758632L;

    public MethodInvokeErrorException() {
        super(INVOKE_ERROR);
    }

    public MethodInvokeErrorException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public MethodInvokeErrorException(String detailMessage) {
        super(detailMessage);
    }

    public MethodInvokeErrorException(Throwable throwable) {
        super(throwable);
    }
}