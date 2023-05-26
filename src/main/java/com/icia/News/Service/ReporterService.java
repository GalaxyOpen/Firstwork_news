package com.icia.News.Service;

import com.icia.News.DTO.ReporterDTO;
import com.icia.News.DTO.ReporterPictureDTO;
import com.icia.News.Repository.ReporterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ReporterService {
    @Autowired
    public ReporterRepository reporterRepository;
    public void save(ReporterDTO reporterDTO) throws IOException {
        if(reporterDTO.getReporterPicture().get(0).isEmpty()){
            System.out.println("파일 없음");
            reporterDTO.setFileAttached(0);
            reporterRepository.save(reporterDTO);
        }else{
            System.out.println("파일 있음");
            reporterDTO.setFileAttached(1);
            ReporterDTO dto = reporterRepository.save(reporterDTO);

            for(MultipartFile reporterPicture : reporterDTO.getReporterPicture()){

                String originalFilename = reporterPicture.getOriginalFilename();
                System.out.println("originalFilename = " + originalFilename);

                System.out.println(System.currentTimeMillis());
                System.out.println(UUID.randomUUID().toString());
                String storedFileName = System.currentTimeMillis()+"-"+originalFilename;
                System.out.println("storedFileName = " + storedFileName);

                ReporterPictureDTO reporterPictureDTO = new ReporterPictureDTO();
                reporterPictureDTO.setOriginalFileName(originalFilename);
                reporterPictureDTO.setStoredFileName(storedFileName);
                reporterPictureDTO.setReporterId(dto.getId());

                String savePath = "D:\\springframework_img\\"+storedFileName;

                reporterPicture.transferTo(new File(savePath));
                reporterRepository.saveFile(reporterPictureDTO);
            }
        }
    }

    public ReporterDTO findByReporterEmail(String reporterEmail) {
        return reporterRepository.findByReporterEmail(reporterEmail);
    }

    public boolean login(ReporterDTO reporterDTO) {
        ReporterDTO dto = reporterRepository.login(reporterDTO);
        if(dto!=null){
            return true;
        }else{
            return false;
        }
    }

    public void update(ReporterDTO reporterDTO) throws IOException {
        List<ReporterPictureDTO> existingPictures = reporterRepository.getReporterPictures(reporterDTO.getId());
        //새롭게 받은 파일로 교체하는 로직
        if(reporterDTO.getReporterPicture().get(0).isEmpty()) {
            System.out.println("대표사진 없음");
            reporterDTO.setFileAttached(0);
            reporterRepository.update(reporterDTO);
        }else{
            System.out.println("대표사진 교체");
            reporterDTO.setFileAttached(1);
            ReporterDTO report = reporterRepository.update(reporterDTO);

            deleteExistingPictures(existingPictures);

            for(MultipartFile reportPicture : reporterDTO.getReporterPicture()){
                //원본 사진 이름
                String originalFileName = reportPicture.getOriginalFilename();
                System.out.println(UUID.randomUUID().toString());

                //저장용 이름
                System.out.println(System.currentTimeMillis());
                System.out.println(UUID.randomUUID().toString());
                String storedFileName=System.currentTimeMillis()+"-"+originalFileName;
                System.out.println("storedFileName = " + storedFileName);

                //저장을 위한 ReportPictureDTO
                ReporterPictureDTO reporterPictureDTO = new ReporterPictureDTO();
                reporterPictureDTO.setOriginalFileName(originalFileName);
                reporterPictureDTO.setStoredFileName(storedFileName);
                reporterPictureDTO.setReporterId(report.getId());

                //저장 경로
                String savePath = "D:\\Firstwork_news\\"+storedFileName;

                //저장 처리
                reportPicture.transferTo(new File(savePath));
                reporterRepository.saveFile(reporterPictureDTO);
            }
        }
    }
    public void deleteExistingPictures(List<ReporterPictureDTO> existingPictures){
        for(ReporterPictureDTO picture : existingPictures){
            // 기존 파일 삭제
            String filePath = "D:\\Firstwork_news\\"+picture.getStoredFileName();
            File file = new File(filePath);
            file.delete();
            reporterRepository.deleteExistingPicture(picture.getId());
        }
    }

    public void delete(Long id) {
        reporterRepository.delete(id);
    }

    public List<ReporterDTO> findAll() {
        List<ReporterDTO> reporterDTOList = reporterRepository.findAll();
        return reporterDTOList;
    }

    public ReporterDTO findById(Long id) {
        ReporterDTO reporterDTO = reporterRepository.findById(id);
        return reporterDTO;
    }

    public List<ReporterPictureDTO> findFile(Long id) {
        return reporterRepository.findFile(id);
    }
}
