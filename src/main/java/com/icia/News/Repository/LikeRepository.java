package com.icia.News.Repository;

import com.icia.News.DTO.LikeDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LikeRepository {
    @Autowired
    private SqlSessionTemplate sql;

    public boolean addLike(Long articleId, Long memberId) {
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setArticleId(articleId);
        likeDTO.setMemberId(memberId);
        sql.insert("Like.addLike", likeDTO);
        return true;

    }

    public LikeDTO findById(String memberId) {
        return sql.selectOne("Like.findById",memberId);
    }
}
