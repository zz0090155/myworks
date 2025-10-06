package com.example.myworks.service;

import com.example.myworks.dao.UserResposity;
import com.example.myworks.dao.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserResposity userResposity;

    public void register(User user){
        userResposity.save(user);
    }

    public Integer login(String userName,String password){
        User user=userResposity.login(userName,password);
        if(user!=null) {
            return user.getId();
        }else {
            return 0;
        }
    }

    public boolean exists(User user){
        return userResposity.existsByUserName(user.getUserName());
    }
}
