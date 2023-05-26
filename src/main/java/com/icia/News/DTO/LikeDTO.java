package com.icia.News.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class LikeDTO {
    private Long likeId;
    private Long articleId;
    private Long memberId;
    private Long article_like;

}

