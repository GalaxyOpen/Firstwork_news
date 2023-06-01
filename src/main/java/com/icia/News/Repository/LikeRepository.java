package com.icia.News.Repository;

import com.icia.News.DTO.LikeDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

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

    public boolean removeLike(Long articleId, Long memberId) {
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setArticleId(articleId);
        likeDTO.setMemberId(memberId);
        sql.delete("Like.removeLike",likeDTO);
        return true;
    }

    public LikeDTO findByArticleIdAndMemberId(Long articleId, Long memberId) {
        Map<String, Long> likeOrNot = new HashMap<>();
        likeOrNot.put("articleId", articleId);
        likeOrNot.put("memberId", memberId);
        return sql.selectOne("Like.findByArticleIdAndMemberId", likeOrNot);
    }
}
