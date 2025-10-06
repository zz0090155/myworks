package com.example.myworks.controller;

import com.example.myworks.dao.pojo.Article;
import com.example.myworks.dao.pojo.Comment;
import com.example.myworks.exception.PermissionDeniedException;
import com.example.myworks.service.CommentService;
import com.example.myworks.utils.JwtUtils;
import com.example.myworks.utils.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseResult<List<Comment>> getComments(Integer id){
        List<Comment> comments=commentService.getComment(id);
        return ResponseResult.success(200,"success",comments);
    }

    @PostMapping
    public ResponseResult<Void> createComments(@RequestBody Comment comment){
        commentService.createComment(comment);
        return ResponseResult.success(200,"success",null);
    }

    @PutMapping("/{id}")
    public ResponseResult<Void> updateComments(@PathVariable("id")Integer id,
                                               @RequestBody Comment comment,
                                               HttpServletRequest request){
        String userName= JwtUtils.getNameFromJwt(request.getHeader("Authorization"));
        Comment comment1=commentService.getCommentById(id);
        if(userName.equals(comment1.getUserName())){
            comment.setId(id);
            commentService.updateComment(comment);
            return ResponseResult.success(200,"success",null);
        }else {
            throw new PermissionDeniedException();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseResult<Void> deleteComments(@PathVariable("id")Integer id,
                                               HttpServletRequest request){
        String userName= JwtUtils.getNameFromJwt(request.getHeader("Authorization"));
        Comment comment=commentService.getCommentById(id);
        if(userName.equals(comment.getUserName())){
            commentService.deleteCommentById(id);
            return ResponseResult.success(200,"success",null);
        }else {
            throw new PermissionDeniedException();
        }
    }
}
