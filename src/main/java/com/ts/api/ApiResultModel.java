package com.ts.api;

import java.io.Serializable;

/**
 * Created by shan on 16/7/18.
 */
public class ApiResultModel<T> implements Serializable {

    private static final long serialVersionUID = -5492061745556425612L;

    private T data;
    private int code;
    private String message;

    public ApiResultModel() {
        this(ErrorCode.SUCCESS, null);
    }

    public ApiResultModel(ErrorCode errorCode) {
        this(errorCode, null);
    }

    public ApiResultModel(T data) {
        this(ErrorCode.SUCCESS, data);
    }


    public ApiResultModel(ErrorCode errorCode, T data) {
        this.code = errorCode.getErrorCode();
        this.message = errorCode.getErrorMsg();
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ApiResultModel{" +
                "status=" + code +
                ", message='" + message +
                ", data=" + data +
                '}';
    }
}
