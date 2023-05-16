package com.icia.News.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@ToString

public class ArticleDTO {
    private Long id;
    private String ArticleTitle;
    private String ArticleWriter;
    private String ArticleContents;
    private int ArticleHits;
    private Timestamp ArticleUploadingTime;
    private int fileAttached;
    private List<MultipartFile> ArticlePicture;
    private Long reporterId;
    private int like;
    private int hate;
}
