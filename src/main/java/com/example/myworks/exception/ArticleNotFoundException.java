package com.example.myworks.exception;

import com.example.myworks.utils.ResponseResult;
import lombok.Data;

public class ArticleNotFoundException extends RuntimeException{

    private int code=404;
    private String msg="文章不存在，请输入正确的id";

    public <T> ResponseResult<T> error(T data){
        return ResponseResult.error(code,msg,data);
    }
}
