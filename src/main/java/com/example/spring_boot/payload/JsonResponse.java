package com.example.spring_boot.payload;

public class JsonResponse {
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public JsonResponse(String code, String message, String data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private String code;
    private String message;
    private String data;

}
