package com.example.myworks.exception;


import com.example.myworks.utils.ResponseResult;

public class UserNotFoundException extends RuntimeException{
    private int code=404;
    private String msg="你还未注册，请先完成注册";

    public <T>ResponseResult<T> error(T data){
        return ResponseResult.error(code,msg,data);
    }

}
