package com.example.myworks.exception;

import com.example.myworks.utils.ResponseResult;

public class UserExistsException extends RuntimeException{

    private int code=409;
    private String msg="该用户已经注册，不可再次注册";

    public <T>ResponseResult<T> error(T data){
        return ResponseResult.error(code,msg,data);
    }
}
