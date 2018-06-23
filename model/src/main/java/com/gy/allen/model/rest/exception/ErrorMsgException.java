package com.gy.allen.model.rest.exception;

public class ErrorMsgException extends RuntimeException {
    private int mErrCode;
    public ErrorMsgException(int errCode, String message){
        super(message);
        this.mErrCode = errCode;
    }

    public int getmErrCode() {
        return mErrCode;
    }

    public void setmErrCode(int mErrCode) {
        this.mErrCode = mErrCode;
    }
}
