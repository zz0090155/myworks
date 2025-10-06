package com.example.myworks.service;

import com.example.myworks.dao.CommentResposity;
import com.example.myworks.dao.pojo.Article;
import com.example.myworks.dao.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentResposity commentResposity;

    public void createComment(Comment comment){
        commentResposity.save(comment);
    }

    public void deleteCommentById(Integer id){
        commentResposity.deleteById(id);
    }

    public void updateComment(Comment comment){
        commentResposity.save(comment);
    }

    public List<Comment> getComment(Integer id){
        return commentResposity.getComment(id);
    }

    public Comment getCommentById(Integer id){
        return commentResposity.findById(id).get();
    }
}
