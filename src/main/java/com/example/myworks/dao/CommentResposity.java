package com.example.myworks.dao;

import com.example.myworks.dao.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentResposity extends JpaRepository<Comment,Integer> {
    @Query(value = "select * from comment where article_id=?1",nativeQuery = true)
    List<Comment> getComment(Integer id);
}
