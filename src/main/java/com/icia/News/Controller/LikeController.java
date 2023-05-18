package com.icia.News.Controller;

import com.icia.News.Service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LikeController {
    @Autowired
    public LikeService likeService;
}
