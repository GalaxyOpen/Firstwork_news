package com.icia.News.Controller;

import com.icia.News.DTO.MemberDTO;
import com.icia.News.Service.LikeService;
import com.icia.News.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

        // 중복 좋아요 확인
        boolean alreadyLiked = likeService.checkIfLiked(articleId, memberDTO.getId());
        if(alreadyLiked){
            return ResponseEntity.badRequest().body("이미 좋아요가 추가되었습니다.");
        }
        // 좋아요 추가
        likeService.addLike(articleId,memberDTO.getId());
        return ResponseEntity.ok("좋아요 추가");
    }
    @PostMapping("/goodRemove")
    public ResponseEntity<String> removeLike(@RequestParam("articleId") Long articleId,
                                             HttpSession session) {
        System.out.println("넘어오지않음");
        String memberId = (String)session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.findByMemberEmail(memberId);

        // 좋아요 확인
        boolean alreadyLiked = likeService.checkIfLiked(articleId,memberDTO.getId());
        if(!alreadyLiked){
            return ResponseEntity.badRequest().body("좋아요가 없습니다.");
        }
        // 좋아요 삭제
        likeService.removeLike(articleId, memberDTO.getId());
        return ResponseEntity.ok("좋아요 삭제");
    }
    @GetMapping("/checkLikeStatus")
    @ResponseBody
    public boolean checkLikeStatus(@RequestParam("articleId") Long articleId,
                                   HttpSession session) {
        String memberId = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.findByMemberEmail(memberId);

        return likeService.checkIfLiked(articleId, memberDTO.getId());
    }
}
