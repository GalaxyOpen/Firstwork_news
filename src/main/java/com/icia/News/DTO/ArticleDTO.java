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
    private String articleTitle;
    private String articleWriter;
    private String articleContents;
    private int articleHits;
    private Timestamp articleUploadingTime;
    private int fileAttached;
    private List<MultipartFile> articlePicture;
    private Long reporterId;
    private int good;
    private int hate;
}
