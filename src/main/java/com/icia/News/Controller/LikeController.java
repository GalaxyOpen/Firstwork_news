package com.icia.News.Controller;

import com.icia.News.DTO.MemberDTO;
import com.icia.News.Service.LikeService;
import com.icia.News.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LikeController {
    @Autowired
    private LikeService likeService;
    @Autowired
    private MemberService memberService;

    @PostMapping("/goodUp")
    public ResponseEntity<String> addLike(@RequestParam("articleId") Long articleId,
                                          HttpSession session){
        System.out.println("넘어옴");
        String memberId = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.findByMemberEmail(memberId);

        boolean result = likeService.addLike(articleId, memberDTO.getId());
        if(result){
            return ResponseEntity.ok("좋아요 추가");
        }else{
            return ResponseEntity.badRequest().body("이미 좋아요가 추가되었습니다.");
        }
    }
}
