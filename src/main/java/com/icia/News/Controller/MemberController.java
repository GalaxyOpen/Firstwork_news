package com.icia.News.Controller;

import com.icia.News.DTO.MemberDTO;
import com.icia.News.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    // 일반 회원 가입 완료

    @PostMapping("/memberIdCheck")
    public ResponseEntity memberIdCheck(@RequestParam("memberEmail")String memberEmail){
        MemberDTO memberDTO = memberService.findByMemberEmail(memberEmail);
        if(memberDTO == null){
            return new ResponseEntity<>(memberDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(memberDTO, HttpStatus.CONFLICT);
        }
    }

}
