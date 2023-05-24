package com.icia.News.Controller;

import com.icia.News.DTO.ArticleDTO;
import com.icia.News.DTO.PagingDTO;
import com.icia.News.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

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
        return "/article/articleSave";
    }
    @PostMapping("/article/save")
    public String save(@ModelAttribute ArticleDTO articleDTO) throws IOException {
        System.out.println("boardDTO = " + articleDTO);
        articleService.save(articleDTO);
        return "redirect:/article?id="+articleDTO.getId();
    }
    @GetMapping("/article/list")
    public String findAll(Model model){
        List<ArticleDTO> articleDTOList = articleService.findAll();
        model.addAttribute("articleList", articleDTOList);
        return "/article/articleList";
    }
    @GetMapping("article/paging")
    public String paging(Model model,
                         @RequestParam(value="page",required = false,defaultValue = "1")int page,
                         @RequestParam(value="q",required = false,defaultValue = "")String q,
                         @RequestParam(value="type",required = false, defaultValue = "boardTitle")String type){
        System.out.println("page = " + page+",q="+q);
        List<ArticleDTO> articleDTOList = null;
        PagingDTO pagingDTO = null;
        if(q.equals("")){
            // 사용자가 요청한 페이지에 해당하는 글 목록 데이터
            articleDTOList = articleService.pagingList(page);

            //하단에 보여줄 목록 데이터( 이전 / 숫자 / 다음)
            pagingDTO = articleService.pagingParam(page);
        }else{
            articleDTOList = articleService.searchList(page, type, q);
            pagingDTO = articleService.pagingSearchParam(page,type,q);
        }
        model.addAttribute("articleList", articleDTOList);
        model.addAttribute("paging", pagingDTO);
        model.addAttribute("q",q);
        model.addAttribute("type", type);
        return "article/articlePaging";
    }



}
