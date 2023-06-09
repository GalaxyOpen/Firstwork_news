package com.icia.News.Service;

import com.icia.News.DTO.LikeDTO;
import com.icia.News.Repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;

    public boolean addLike(Long articleId, Long memberId) {
        return likeRepository.addLike(articleId, memberId);
    }
    public LikeDTO findById(String memberId){
        return likeRepository.findById(memberId);
    }

    public boolean removeLike(Long articleId, Long memberId) {
        return likeRepository.removeLike(articleId,memberId);
    }

    public boolean checkIfLiked(Long articleId, Long memberId) {
        LikeDTO likeDTO = likeRepository.findByArticleIdAndMemberId(articleId, memberId);
        return likeDTO != null;
    }
}
