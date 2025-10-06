package com.example.myworks.service;
import com.example.myworks.dao.pojo.Article;
import com.example.myworks.dao.ArticleResposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    private ArticleResposity articleResposity;

    @Cacheable(cacheNames = "ArticleCache",key = "#pageable.pageNumber")
    public Page<Article> getPage(Pageable pageable){
        return articleResposity.findAll(pageable);
    }

    public Page<Article> getPageByKeyword(String keyword,Pageable pageable){
        return articleResposity.findByKeyword(keyword,pageable);
    }

    public Article getArticleById(Integer id){
        return articleResposity.findById(id).get();
    }

    @CacheEvict(cacheNames = "ArticleCache",allEntries = true)
    public void createArticle(Article article){
        articleResposity.save(article);
    }

    @CacheEvict(cacheNames = "ArticleCache",allEntries = true)
    public void updateArticle(Article article){
        articleResposity.save(article);
    }

    @CacheEvict(cacheNames = "ArticleCache",allEntries = true)
    public void deleteArticle(Integer id){
        articleResposity.deleteById(id);
    }
}
