package com.icia.News.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class CommentDTO {
    private Long id;
    private Long articleId;
    private String commentWriter;
    private String commentContents;
    public Timestamp commentCreatedDate;
}
