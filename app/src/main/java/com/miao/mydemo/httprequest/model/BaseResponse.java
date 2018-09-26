package com.miao.mydemo.httprequest.model;

import java.io.Serializable;

public class BaseResponse implements Serializable {

    //序列化UID 用于反序列化
    private static final long serialVersionUID = 234513596098152098L;

    //0为请求成功 其他错误
    private int errorCode;
    //错误信息
    private String errorMsg;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
