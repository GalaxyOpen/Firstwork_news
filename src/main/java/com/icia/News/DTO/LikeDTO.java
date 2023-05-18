package com.icia.News.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class LikeDTO {
    private int likeId;
    private int articleId;
    private int memberId;
    private int article_like;

}
