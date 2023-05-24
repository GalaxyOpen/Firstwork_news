package com.icia.News.Controller;

import com.icia.News.DTO.ArticleDTO;
import com.icia.News.DTO.ArticlePictureDTO;
import com.icia.News.DTO.CommentDTO;
import com.icia.News.DTO.PagingDTO;
import com.icia.News.Service.ArticleService;
import com.icia.News.Service.CommentService;
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
    @Autowired
    private CommentService commentService;
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
        System.out.println("articleDTO = " + articleDTO);
        articleService.save(articleDTO);
        return "redirect:/article?id="+articleDTO.getId();
    }
    @GetMapping("/article/list")
    public String findAll(Model model){
        List<ArticleDTO> articleDTOList = articleService.findAll();
        model.addAttribute("articleList", articleDTOList);
        return "/article/articleList";
    }
    @GetMapping("/article/paging")
    public String paging(Model model,
                         @RequestParam(value="page",required = false,defaultValue = "1")int page,
                         @RequestParam(value="q",required = false,defaultValue = "")String q,
                         @RequestParam(value="type",required = false, defaultValue = "articleTitle")String type){
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
    @GetMapping("/article")
    public String findById(@RequestParam("id")Long id, Model model,
                           @RequestParam(value="page",required = false,defaultValue = "1")int page,
                           @RequestParam(value="q", required = false, defaultValue = "")String q,
                           @RequestParam(value="type", required = false, defaultValue = "articleTitle")String type){
        articleService.updateHits(id);

       ArticleDTO articleDTO = articleService.findById(id);
       model.addAttribute("article", articleDTO);
       model.addAttribute("page", page);
       model.addAttribute("q",q);
       model.addAttribute("type",type);


       if(articleDTO.getFileAttached()==1){
           List<ArticlePictureDTO> articlePictureDTO = articleService.findFile(id);
           model.addAttribute("articlePictureList", articlePictureDTO);
           System.out.println("articlePictureDTO = " + articlePictureDTO);
       }
       List<CommentDTO> commentDTOList = commentService.findAll(id);
       if(commentDTOList.size()==0){
           model.addAttribute("commentList",null);
       }else{
           model.addAttribute("commentList",commentDTOList);
       }
       return "/article/articleDetail";
    }
    @GetMapping("/article/update")
    public String updateForm(@RequestParam("id")Long id, Model model){
        ArticleDTO articleDTO = articleService.findById(id);
        model.addAttribute("article", articleDTO);
        return "/article/articleUpdate";
    }
    @PostMapping("/article/update")
    public String update(@ModelAttribute ArticleDTO articleDTO, Model model){
        articleService.update(articleDTO);
        ArticleDTO art = articleService.findById(articleDTO.getId());
        model.addAttribute("article", art);
        return "redirect:/article?id="+articleDTO.getId();
    }
    @GetMapping("/article/delete")
    public String delete(@RequestParam("id") Long id){
        articleService.delete(id);
        return "/index";
    }



}
