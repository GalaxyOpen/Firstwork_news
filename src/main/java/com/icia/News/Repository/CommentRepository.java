package com.icia.News.Repository;

import com.icia.News.DTO.CommentDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository {
    @Autowired
    private SqlSessionTemplate sql;


    public List<CommentDTO> findAll(Long articleId) {
        return sql.selectList("Comment.findAll", articleId);

    }

    public void save(CommentDTO commentDTO) {
        System.out.println("commentDTO = " + commentDTO);
        sql.insert("Comment.save", commentDTO);
    }
}
