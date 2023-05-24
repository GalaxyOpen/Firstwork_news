package com.icia.News.Controller;

import com.icia.News.DTO.ReporterDTO;
import com.icia.News.Service.ReporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class ReporterController {
    @Autowired
    public ReporterService reporterService;

    @GetMapping("/reporter/save")
    public String saveForm(){
        return "/reporter/reporterSave";
    }
    @PostMapping("/reporter/save")
    public String save(@ModelAttribute ReporterDTO reporterDTO) throws IOException {
       System.out.println("reporterDTO = " + reporterDTO); //1
       reporterService.save(reporterDTO);
       return "redirect:/reporter/login";
    }

    @PostMapping("/reporterIdCheck")
    public ResponseEntity reporterIdCheck(@RequestParam("reporterEmail")String reporterEmail){
        ReporterDTO reporterDTO = reporterService.findByReporterEmail(reporterEmail);
        if(reporterDTO==null){
            return new ResponseEntity<>(reporterDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(reporterDTO, HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/reporter/login")
    public String loginForm(){
        return "/reporter/reporterLogin";
    }
    @PostMapping("/reporter/login")
    public String login(@ModelAttribute ReporterDTO reporterDTO, HttpSession session){
        boolean reporterLoginResult = reporterService.login(reporterDTO);
        if(reporterLoginResult){
            session.setAttribute("reporterLoginEmail",reporterDTO.getReporterEmail());
            return "/reporter/reporterHome";
        }else{
            return "/reporter/reporterLogin";
        }
    }

    @GetMapping("/reporter/logout")
    public String logout(HttpSession session) {
        // 세션에 담긴 값 전체 삭제
//        session.invalidate();
        // 특정 파라미터만 삭제
        session.removeAttribute("loginEmail");
        return "redirect:/";
    }


}
