package com.example.myworks.dao;

import com.example.myworks.dao.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserResposity extends JpaRepository<User,Integer> {

    @Query(value = "select * from user where username=?1 and password=?2",nativeQuery = true)
    User login(String username,String password);

    boolean existsByUserName(String userName);
}
