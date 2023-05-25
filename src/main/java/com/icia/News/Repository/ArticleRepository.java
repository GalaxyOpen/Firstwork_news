package com.icia.News.Repository;

import com.icia.News.DTO.ArticleDTO;
import com.icia.News.DTO.ArticlePictureDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    public List<ArticleDTO> pagingList(Map<String, Integer> pagingParams) {
        return sql.selectList("Article.paging", pagingParams);
    }

    public int articleCount() {
        return sql.selectOne("Article.count");
    }

    public List<ArticleDTO> searchList(Map<String, Object> pagingParams) {
        return sql.selectList("Article.search", pagingParams);
    }

    public int articleSearchCount(Map<String, Object> pagingParams) {
        return sql.selectOne("Article.searchCount", pagingParams);
    }

    public void updateHits(Long id) {
        sql.update("Article.updateHits",id);
    }

    public ArticleDTO findById(Long id) {
        return sql.selectOne("Article.findById",id);
    }

    public List<ArticlePictureDTO> findFile(Long articleId) {
        return sql.selectList("Article.findFile", articleId);
    }

    public ArticleDTO update(ArticleDTO articleDTO) {
        sql.update("Article.update",articleDTO);
        return articleDTO;
    }

    public void delete(Long id) {
        sql.delete("Article.delete",id);
    }
}
