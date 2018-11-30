package com.ts.exception;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 7026776142859331595L;


    private String msg;


    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String msg) {
        super();
        this.msg = msg;
    }

    public BusinessException(String msg, Throwable cause) {
        super(cause);
        this.msg = msg;
    }

}
