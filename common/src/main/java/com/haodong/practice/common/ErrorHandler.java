package com.haodong.practice.common;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import retrofit2.HttpException;

/**
 * created by linghaoDo on 2020/6/10
 * description:
 * <p>
 * version:
 */
public class ErrorHandler {
    public static String errorDatq = "{\"errorCode\":420,\"errorMessage\":\"出了点问题，请稍后再试。如有需要，可以联系客服\",\"requestId\":\"9gS9SUOn\"}";

    public static ErrorBody handle(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException error = (HttpException) throwable;
            try {
                String string = error.response().errorBody().string();
                if (isJSONValid(string))
                    return new Gson().fromJson(string, ErrorBody.class);
                else return new Gson().fromJson(errorDatq, ErrorBody.class);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        } else {
            throwable.printStackTrace();
            return null;
        }
    }

    public static boolean isJSONValid(String jsonInString) {
        try {
            Gson gson = new Gson();
            gson.fromJson(jsonInString, Object.class);
            return true;
        } catch (JsonSyntaxException ex) {
            return false;
        }
    }

    public static String errorMessage(Throwable e) {
        ErrorBody errBody = ErrorHandler.handle(e);
        if (errBody != null) {
            int errorCode = errBody.getErrorCode();
//            if ("401".equals(errorCode)) {
//                //token失效了
//
//            } else if ("402".equals(errorCode)) {
//
//            } else if ("403".equals(errorCode)) {
//
//            } else if ("404".equals(errorCode)) {
//
//            } else if ("500".equals(errorCode)) {
//
//            }
            return "出错了:" + errBody.getErrorMessage();
        }
        return "出错了！";
    }
}
