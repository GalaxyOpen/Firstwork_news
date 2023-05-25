package com.icia.News.Service;

import com.icia.News.DTO.ArticleDTO;
import com.icia.News.DTO.ArticlePictureDTO;
import com.icia.News.DTO.PagingDTO;
import com.icia.News.Repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public List<ArticleDTO> pagingList(int page) {
        int pageLimit =10; //한글에 나타날 기사 수
        int pagingStart=(page-1)*pageLimit;

        Map<String, Integer> pagingParams = new HashMap<>();

        pagingParams.put("start", pagingStart);
        pagingParams.put("limit", pageLimit);

        List<ArticleDTO> articleDTOList = articleRepository.pagingList(pagingParams);
        return articleDTOList;
    }

    public PagingDTO pagingParam(int page) {
        // 총 기사 수를 n개라 가정하고
        // 한 페이지에 기사를 10개씩 보여줄 예정
        // 홈페이지 하단에 나오는 숫자 또한 1-10까지 10개

        int pageLimit=10; // 한페이지에 보여질 기사 수
        int blockLimit=10; // 하단에 보여질 펭디지 번호 블록의 수

        //전체 기사 수 조회
        int boardCount=articleRepository.articleCount();

        //전체 기사의 페이지 개수 조회
        int maxPage = (int)(Math.ceil((double)boardCount/pageLimit));

        // 시작할 페이지 값 계산
        int startPage = (((int)(Math.ceil((double)page/blockLimit)))-1)*blockLimit+1;

        // 마지막 페이지 값을 계산
        int endPage = startPage + blockLimit-1;

        // 전체 페이지 수가 계산해본 endPage보다 작을 때는 endPage=maxPage 가 되도록 세팅한다.
        if(endPage>maxPage){
            endPage=maxPage;
        }
        PagingDTO pagingDTO = new PagingDTO();
        pagingDTO.setPage(page);
        pagingDTO.setMaxPage(maxPage);
        pagingDTO.setEndPage(endPage);
        pagingDTO.setStartPage(startPage);
        return pagingDTO;
    }

    public List<ArticleDTO> searchList(int page, String type, String q) {
        int pageLimit = 10; // 한페이지에 보여줄 기사 수
        int pagingStart = (page-1)*pageLimit;
        Map<String, Object> pagingParams = new HashMap<>();
        pagingParams.put("start", pagingStart);
        pagingParams.put("limit", pageLimit);
        pagingParams.put("q",q);
        pagingParams.put("type", type);
        List<ArticleDTO> articleDTOList = articleRepository.searchList(pagingParams);
        return articleDTOList;
    }

    public PagingDTO pagingSearchParam(int page, String type, String q) {
        int pageLimit = 10; // 한 페이지에 보여줄 기사 수
        int blockLimit = 10; // 하단에 나타날 페이지 수
        Map<String, Object> pagingParams = new HashMap<>();
        pagingParams.put("q", q);
        pagingParams.put("type", type);

        // 전체 글 수
        int articleCount = articleRepository.articleSearchCount(pagingParams);

        // 전체 페이지 수
        int maxPage = (int)(Math.ceil((double)articleCount/pageLimit));

        // 시작할 페이지
        int startPage = (((int)(Math.ceil((double)page/blockLimit)))-1)*blockLimit+1;

        // 마지막 페이지
        int endPage = startPage + blockLimit-1;

        // maxPage가 endPage보다 작을 경우 endPage 값을 maxPage 값과 같도록
        if(endPage>maxPage){
            endPage=maxPage;
        }
        PagingDTO pagingDTO=new PagingDTO();
        pagingDTO.setPage(page);
        pagingDTO.setMaxPage(maxPage);
        pagingDTO.setEndPage(endPage);
        pagingDTO.setStartPage(startPage);

        return pagingDTO;
    }

    public void updateHits(Long id) {
        articleRepository.updateHits(id);
    }

    public ArticleDTO findById(Long id) {
        return articleRepository.findById(id);
    }

    public List<ArticlePictureDTO> findFile(Long id) {
        return articleRepository.findFile(id);
    }

    public void update(ArticleDTO articleDTO) throws IOException {
        if (articleDTO.getArticlePicture().get(0).isEmpty()) {
            System.out.println("파일없음");
            articleDTO.setFileAttached(0);
            articleRepository.update(articleDTO);
        } else {
            System.out.println("파일있음");
            articleDTO.setFileAttached(1);
            ArticleDTO art = articleRepository.update(articleDTO);
            for (MultipartFile articlePicture : articleDTO.getArticlePicture()) {
                //원본 사진 이름
                String originalFileName = articlePicture.getOriginalFilename();
                System.out.println(UUID.randomUUID().toString());

                //저장용 이름
                System.out.println(System.currentTimeMillis());
                System.out.println(UUID.randomUUID().toString());
                String storedFileName = System.currentTimeMillis() + "-" + originalFileName;
                System.out.println("storedFileName = " + storedFileName);

                // 저장을 위한 ArticlePictureDTO
                ArticlePictureDTO articlePictureDTO = new ArticlePictureDTO();
                articlePictureDTO.setOriginalFileName(originalFileName);
                articlePictureDTO.setStoredFileName(storedFileName);
                articlePictureDTO.setArticleId(art.getId());

                //저장 경로
                String savePath = "D:\\Firstwork_news\\" + storedFileName;

                //저장 처리
                articlePicture.transferTo(new File(savePath));
                articleRepository.saveFile(articlePictureDTO);

            }
        }
    }
    public void delete(Long id) {
        articleRepository.delete(id);
    }
}
