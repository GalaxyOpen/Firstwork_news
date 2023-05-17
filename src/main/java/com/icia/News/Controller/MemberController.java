package com.icia.News.Controller;

import com.icia.News.DTO.MemberDTO;
import com.icia.News.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    public MemberService memberService;

    @GetMapping("/member/save")
    public String saveForm(){
        return "/member/memberSave";
    }
    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO){
        memberService.save(memberDTO);
        return "/member/memberLogin";
    }

}
