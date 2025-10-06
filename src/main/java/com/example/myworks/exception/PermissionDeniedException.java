package com.example.myworks.exception;

import com.example.myworks.utils.ResponseResult;

public class PermissionDeniedException extends RuntimeException{
    private int code=403;
    private String msg="你无权操作不属于你的文章及评论";
    public <T>ResponseResult<T> error(T data){
        return ResponseResult.error(code,msg,data);
    }
}
