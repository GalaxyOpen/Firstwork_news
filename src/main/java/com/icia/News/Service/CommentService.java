package com.icia.News.Service;

import com.icia.News.DTO.CommentDTO;
import com.icia.News.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<CommentDTO> findAll(Long articleId) {
        return commentRepository.findAll(articleId);
    }
}
