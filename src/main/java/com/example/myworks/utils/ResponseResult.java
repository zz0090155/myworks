package com.example.myworks.utils;

import lombok.Data;

@Data
public class ResponseResult<T> {
    private int code;
    private String message;
    private T data;

    public ResponseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseResult() {
    }

    public static <T> ResponseResult<T> success(int code,String message,T data){

        return new ResponseResult<T>(code,message,data);
    }

    public static <T> ResponseResult<T> success(){

        return new ResponseResult<>(200,"success",null);
    }

    public static <T> ResponseResult<T> error(){

        return new ResponseResult<>(500,"error",null);
    }

    public static <T> ResponseResult<T> error(int code,String message,T data){
        return new ResponseResult<>(code,message,data);
    }
}
