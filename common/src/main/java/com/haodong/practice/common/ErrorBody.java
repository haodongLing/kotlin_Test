package com.haodong.practice.common;

/**
 * created by linghaoDo on 2020/6/10
 * description:
 * <p>
 * version:
 */
public class ErrorBody {


    /**
     * errorCode : 420
     * errorMessage : 出了点问题，请稍后再试。如有需要，可以联系客服
     * requestId : 9gS9SUOn
     */

    private int errorCode;
    private String errorMessage;
    private String requestId;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
