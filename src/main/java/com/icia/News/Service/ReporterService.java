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

    public void update(ReporterDTO reporterDTO) {
            reporterRepository.update(reporterDTO);
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
