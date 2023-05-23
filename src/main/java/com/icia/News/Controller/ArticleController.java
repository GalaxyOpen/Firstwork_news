package com.icia.News.Controller;

import com.icia.News.DTO.ArticleDTO;
import com.icia.News.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @GetMapping("/article/save")
    public String saveForm(){
        //신욱님이 써준 줄 추가
//        String email = (String) session.getAttribute("loginEmail");
//        MemberDTO dto = memberService.findByMemberEmail(email);
//        model.addAttribute("boardWriter",dto.getId());
        return "/boardPages/boardSave";
    }
    @PostMapping("/article/save")
    public String save(@ModelAttribute ArticleDTO articleDTO) throws IOException {
        System.out.println("boardDTO = " + articleDTO);
        articleService.save(articleDTO);
        return "redirect:/article?id="+articleDTO.getId();
    }
}
