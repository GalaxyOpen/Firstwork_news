package com.icia.News.Controller;

import com.icia.News.DTO.CommentDTO;
import com.icia.News.DTO.MemberDTO;
import com.icia.News.Service.CommentService;
import com.icia.News.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private MemberService memberService;
    @PostMapping("/comment/save")
    public ResponseEntity save(@ModelAttribute CommentDTO commentDTO, HttpSession session, Model model){
        MemberDTO memberDTO = memberService.findByMemberEmail((String)session.getAttribute("loginEmail"));
        commentDTO.setArticleId(memberDTO.getId());
        commentService.save(commentDTO);
        System.out.println(commentDTO.getArticleId());
        List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getArticleId());
        System.out.println(commentDTOList.size());
        model.addAttribute("commentDTOList",commentDTOList);
        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }
}
