package com.icia.News.Repository;

import com.icia.News.DTO.ReporterDTO;
import com.icia.News.DTO.ReporterPictureDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReporterRepository {
    @Autowired
    private SqlSessionTemplate sql;
    public ReporterDTO save(ReporterDTO reporterDTO){
        sql.insert("Reporter.save", reporterDTO);
        return reporterDTO;
    }

    public void saveFile(ReporterPictureDTO reporterPictureDTO) {
        sql.insert("Reporter.saveFile", reporterPictureDTO);
    }

    public ReporterDTO findByReporterEmail(String reporterEmail) {
       return sql.selectOne("Reporter.findByReporterEmail", reporterEmail);
    }

    public ReporterDTO login(ReporterDTO reporterDTO) {
        return sql.selectOne("Reporter.login", reporterDTO);

    }
}
