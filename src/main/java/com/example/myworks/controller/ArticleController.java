package com.example.myworks.controller;

import com.example.myworks.dao.pojo.Article;
import com.example.myworks.dao.pojo.Comment;
import com.example.myworks.exception.ArticleNotFoundException;
import com.example.myworks.exception.PermissionDeniedException;
import com.example.myworks.service.ArticleService;
import com.example.myworks.service.CommentService;
import com.example.myworks.utils.JwtUtils;
import com.example.myworks.utils.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseResult<PagedModel<Article>> getPage(@PageableDefault(size=5,sort="createTime") Pageable pageable
    ,String keyword){
        Page<Article> pages;
        if(keyword==null||keyword.isEmpty()){
            pages= articleService.getPage(pageable);
        }else {
            pages=articleService.getPageByKeyword(keyword,pageable);
        }
        PagedModel<Article> pagedModel = new PagedModel<>(pages);
        return ResponseResult.success(200, "success", pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseResult<Map<String,Object>> getArticleById(@PathVariable("id") Integer id){
        Article article;
        try{
            article=articleService.getArticleById(id);
        } catch (Exception e){
            throw new ArticleNotFoundException();
        }
        Map<String,Object> map=new HashMap<>();
        map.put("article",article);
        List<Comment> comments=commentService.getComment(id);
        map.put("comments",comments);
        return ResponseResult.success(200,"success",map);
    }

    @PostMapping
    public ResponseResult<Void> createArticle(@RequestBody Article article){
        articleService.createArticle(article);
        return ResponseResult.success();
    }

    @PutMapping("/{id}")
    public ResponseResult<Void> updateArticle(@PathVariable("id")Integer id, @RequestBody Article article,HttpServletRequest request){
        Article article1=articleService.getArticleById(id);
        String userName=JwtUtils.getNameFromJwt(request.getHeader("Authorization"));
        if(article1==null){
            throw new ArticleNotFoundException();
        }
        if(userName.equals(article1.getAuthor())){
            article.setId(id);
            articleService.updateArticle(article);
            return ResponseResult.success();
        }else {
            throw new PermissionDeniedException();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseResult<Void> deleteArticle(@PathVariable("id")Integer id,HttpServletRequest request){
        Article article1;
        try {
            article1 = articleService.getArticleById(id);
        }catch (Exception e) {
            throw new ArticleNotFoundException();
        }
        String userName= JwtUtils.getNameFromJwt(request.getHeader("Authorization"));
        if(userName.equals(article1.getAuthor())){
            articleService.deleteArticle(id);
            return ResponseResult.success();
        }else {
            throw new PermissionDeniedException();
        }
    }
}
