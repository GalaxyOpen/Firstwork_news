package com.icia.News.Repository;

import com.icia.News.DTO.ArticleDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepository {
    @Autowired
    private SqlSessionTemplate sql;
    public ArticleDTO save(ArticleDTO articleDTO) {
        sql.insert("Article.save", articleDTO);
        return articleDTO;
    }

    public void saveFile(ArticleDTO articleDTO) {

    }
}
