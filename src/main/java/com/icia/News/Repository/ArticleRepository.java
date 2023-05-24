package com.icia.News.Repository;

import com.icia.News.DTO.ArticleDTO;
import com.icia.News.DTO.ArticlePictureDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleRepository {
    @Autowired
    private SqlSessionTemplate sql;
    public ArticleDTO save(ArticleDTO articleDTO) {
        sql.insert("Article.save", articleDTO);
        return articleDTO;
    }

    public void saveFile(ArticlePictureDTO articlePictureDTO) {
        sql.insert("Article.saveFile",articlePictureDTO);
    }

    public List<ArticleDTO> findAll() {
       return sql.selectList("Article.findAll");
    }
}
