package com.icia.News.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class ArticlePictureDTO {
    private Long id;
    private String originalFileName;
    private String storedFileName;
    private int fileAttached ;
    private Long articleId;
}
