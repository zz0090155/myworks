package com.example.myworks.exception;

import com.example.myworks.utils.ResponseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseResult<Void> globalException(){
        return ResponseResult.error(500,"服务器错误，请稍后重试",null);
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseResult<Void> articleNotFound(ArticleNotFoundException e){
        return e.error(null);
    }

    @ExceptionHandler(PermissionDeniedException.class)
    public ResponseResult<Void> permissionDenied(PermissionDeniedException e){
        return e.error(null);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseResult<Void> userNotFound(UserNotFoundException e){
        return e.error(null);
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseResult<Void> userExists(UserExistsException e){
        return e.error(null);
    }

}
