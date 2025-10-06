package com.example.myworks.dao;
import com.example.myworks.dao.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleResposity extends JpaRepository<Article,Integer> {

    @Query(value = "select * from article " +
            "where title like concat('%',?1,'%') or content like concat('%',?1,'%')",nativeQuery = true)
    Page<Article> findByKeyword(String keyword, Pageable pageable);
}

