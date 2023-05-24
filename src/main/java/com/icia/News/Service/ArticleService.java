package com.icia.News.Service;

import com.icia.News.DTO.ArticleDTO;
import com.icia.News.DTO.ArticlePictureDTO;
import com.icia.News.Repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    public void save(ArticleDTO articleDTO) throws IOException {
        //파일 유무 진단
        if(articleDTO.getArticlePicture().get(0).isEmpty()){
            System.out.println("파일없음");
            articleDTO.setFileAttached(0);
            articleRepository.save(articleDTO);
        }else{
            System.out.println("파일있음");
            articleDTO.setFileAttached(1);
            ArticleDTO art = articleRepository.save(articleDTO);
            for(MultipartFile articlePicture: articleDTO.getArticlePicture()){
                //원본 사진 이름
                String originalFileName = articlePicture.getOriginalFilename();
                System.out.println(UUID.randomUUID().toString());

                //저장용 이름
                System.out.println(System.currentTimeMillis());
                System.out.println(UUID.randomUUID().toString());
                String storedFileName = System.currentTimeMillis()+"-"+originalFileName;
                System.out.println("storedFileName = " + storedFileName);

                // 저장을 위한 ArticlePictureDTO
                ArticlePictureDTO articlePictureDTO = new ArticlePictureDTO();
                articlePictureDTO.setOriginalFileName(originalFileName);
                articlePictureDTO.setStoredFileName(storedFileName);
                articlePictureDTO.setArticleId(art.getId());

                //저장 경로
                String savePath = "D:\\Firstwork_news\\"+storedFileName;

                //저장 처리
                articlePicture.transferTo(new File(savePath));
                articleRepository.saveFile(articlePictureDTO);
            }
        }
    }

    public List<ArticleDTO> findAll() {
        return articleRepository.findAll();

    }
}
