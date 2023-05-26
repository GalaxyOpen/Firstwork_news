package com.icia.News.Repository;

import com.icia.News.DTO.ReporterDTO;
import com.icia.News.DTO.ReporterPictureDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public void update(ReporterDTO reporterDTO) {
        sql.update("Reporter.update",reporterDTO);
    }

    public void delete(Long id) {
        sql.delete("Reporter.delete",id);
    }

    public List<ReporterDTO> findAll() {
        return sql.selectList("Reporter.findAll");
    }

    public ReporterDTO findById(Long id) {
        return sql.selectOne("Reporter.findById", id);
    }

    public List<ReporterPictureDTO> findFile(Long id) {
        return sql.selectList("Reporter.findFile",id);
    }
}
