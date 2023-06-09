package com.icia.News.Controller;

import com.icia.News.DTO.MemberDTO;
import com.icia.News.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    @GetMapping("/member/login")
    public String loginForm(){
        return "/member/memberLogin";
    }
    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        boolean loginResult = memberService.login(memberDTO);
        System.out.println("memberDTO = " + memberDTO);
        if(loginResult){
            session.setAttribute("loginEmail", memberDTO.getMemberEmail());
            return "/member/memberHome";
        }else{
            return "/member/memberLogin";
        }
    }
    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        // 세션에 담긴 값 전체 삭제
//        session.invalidate();
        // 특정 파라미터만 삭제
        session.removeAttribute("loginEmail");
        return "redirect:/";
    }
    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model){
        String loginEmail=(String)session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
        model.addAttribute("member",memberDTO);
        return "/member/memberUpdate";
    }

    @PostMapping("/member/update")
    public String update (@ModelAttribute MemberDTO memberDTO){
        memberService.update(memberDTO);
        return "/member/memberHome";
    }
    @GetMapping("/member/myPage")
    public String myPage(){
        return "/member/memberHome";
    }
    @GetMapping("/member/delete")
    public String delete(@RequestParam("id")Long id){
        memberService.delete(id);
        return "/index";
    }
    @GetMapping("/member/list")
    public String findAll(Model model){
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList", memberDTOList);
        return "/member/memberList";
    }
    @GetMapping("/member")
    public String findById(@RequestParam("id")Long id, Model model){
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member",memberDTO);
        return "/member/memberDetail";
    }

}
