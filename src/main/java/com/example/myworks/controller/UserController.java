package com.example.myworks.controller;

import com.example.myworks.dao.pojo.User;
import com.example.myworks.exception.UserExistsException;
import com.example.myworks.exception.UserNotFoundException;
import com.example.myworks.service.UserService;
import com.example.myworks.utils.JwtUtils;
import com.example.myworks.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseResult<String> register(@RequestBody User user){
        if(userService.exists(user)){
            throw new  UserExistsException();
        }
        userService.register(user);
        return ResponseResult.success(200,"success","注册成功");
    }

    @PostMapping ("/login")
    public ResponseResult<String> login(@RequestBody User user){
        Integer integer=userService.login(user.getUserName(),user.getPassword());
        if(integer!=0){
            String token=JwtUtils.createToken(integer,user.getUserName());
            return ResponseResult.success(200,"success",token);
        }else {
            throw  new UserNotFoundException();
        }
    }
}
