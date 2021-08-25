package com.covengers.springapi.dto;

import lombok.Data;

@Data
public class JsonResult {

    private String result; // "success" or "fail"
    private Object data; // if success, Data Set
    private String message; // if fail, Message Set
    private int status;

    // 생성을 못하게 막는다.
    public JsonResult() {
    }

    public JsonResult(Object data) {
        result = "success";
        this.data = data;
        this.status = 200;
    }

    public JsonResult(String message, int status) {
        result = "fail";
        this.message = message;
        this.status = status;
    }

    public static JsonResult success(Object data) {
        return  new JsonResult(data);
    }
    public static JsonResult fail(String message) {
        return new JsonResult(message);
    }
    // 세팅을 못하게 막는다.
}
