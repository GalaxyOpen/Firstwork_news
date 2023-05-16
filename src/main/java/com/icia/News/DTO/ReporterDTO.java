package com.icia.News.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString

public class ReporterDTO {
    private Long id;
    private Long ReporterLevel;
    private String ReporterEmail;
    private String ReporterPassword;
    private String ReporterMobile;
    private List<MultipartFile> ReporterPicture;
    private int fileAttached;
    private int like;
    private int hate;
}
